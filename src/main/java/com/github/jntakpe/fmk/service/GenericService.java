package com.github.jntakpe.fmk.service;

import com.github.jntakpe.fmk.domain.GenericDomain;
import com.github.jntakpe.fmk.exception.ConfigurationException;
import com.github.jntakpe.fmk.repository.GenericRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Services usuels
 *
 * @author jntakpe
 */
public abstract class GenericService<T extends GenericDomain<S>, S extends Number> {

    @Autowired
    private GenericRepository<T, S> genericRepository;

    @Autowired
    protected MessageManager messageManager;

    /**
     * Compte le nombre d'entité dans une table
     *
     * @return nombre d'entité
     */
    @Transactional(readOnly = true)
    public long count() {
        return genericRepository.count();
    }

    /**
     * Retrouve une entité à l'aide de son identifiant
     *
     * @param id identifiant de l'entité
     * @return l'entité possédant cette id
     */
    @Transactional(readOnly = true)
    public T findOne(S id) {
        return genericRepository.findOne(id);
    }

    /**
     * Retrouve toutes les entités de la table
     *
     * @return entités de la table
     */
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    /**
     * Indique si l'entité existe en table
     *
     * @param id identifiant de l'entité
     * @return true si exist
     */
    @Transactional(readOnly = true)
    public boolean exists(S id) {
        return genericRepository.exists(id);
    }

    /**
     * Supprime l'entité ayant cet identifiant
     *
     * @param id id de l'entité
     */
    @Transactional
    public void delete(S id) {
        genericRepository.delete(id);
    }

    /**
     * Sauvegarde l'entité. Si elle n'existe pas fait un 'persist' sinon un 'merge'.
     * Seule l'entité renvoyée est 'managed', celle  passée en paramètre est 'detached'.
     * En d'autres termes, toutes les modifications effectuées sur l'entité renvoyée par la fonction seront persistées
     * à la fin de la transaction alors que celles effectuées sur l'objet passé en paramètre ne seront pas persistées.
     *
     * @param entity entité à sauvegarder
     * @return l'entité 'managed'. Attention ce n'est pas le même objet que celui passé en paramètre.
     */
    @Transactional
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    /**
     * Si une contrainte d'unicité existe sur un champ renvoi true si cette dernière est respectée
     *
     * @param fieldName nom du champ surlequel s'applique la contrainte
     * @param id        identifiant de l'entité (null pour une nouvelle entité)
     * @param value     valeur du champ
     * @return true si cette valeur respecte la contrainte d'unicité sinon false
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public boolean isAvaillable(String fieldName, S id, Object value) {
        Class<T> domainClass = getDomainClass();
        Field field = findField(domainClass, fieldName);
        Class<?> fieldClass = field.getType();
        Method method = findUnicityMethod(domainClass, fieldName, fieldClass);
        T entity = (T) ReflectionUtils.invokeMethod(method, genericRepository, fieldClass.cast(value));
        return entity == null || entity.getId().equals(id);
    }

    /**
     * Méthode renvoyant l'entité de la couche domain/model
     *
     * @return ressource utilisée par le contrôlleur
     */
    @SuppressWarnings("unchecked")
    public final Class<T> getDomainClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * Récupère le champ en fonction la classe de l'entité et du nom du champ
     *
     * @param domainClass classe de l'entité
     * @param fieldName   nom du champ
     * @return le champ recherché
     */
    private Field findField(Class<T> domainClass, String fieldName) {
        Field field = ReflectionUtils.findField(domainClass, fieldName);
        if (field == null) {
            String msg = "Champ :" + fieldName + " introuvable dans l'entité : " + domainClass;
            throw new ConfigurationException(msg);
        }
        return field;
    }

    /**
     * Récupère la méthode permettant de vérifier l'unicité d'un champ
     *
     * @param domainClass classe de l'entité
     * @param fieldName   nom du champ
     * @param fieldClass  type du champ
     * @return la méthode à invoquer pour le test d'unicité
     */
    private Method findUnicityMethod(Class<T> domainClass, String fieldName, Class<?> fieldClass) {
        String upperName = StringUtils.capitalize(fieldName);
        Class<? extends GenericRepository> repo = genericRepository.getClass();
        String name = "findBy" + upperName;
        String nameIC = name + "IgnoreCase";
        Method method = ReflectionUtils.findMethod(repo, name, fieldClass);
        if (method == null) {
            method = ReflectionUtils.findMethod(repo, nameIC, fieldClass);
        }
        if (method == null) {
            String msg = "Test d'unicité impossible. Les méthodes " + name + " ou " + nameIC + " sont absentes du " +
                    "repository correspondant à l'entité : " + domainClass;
            throw new ConfigurationException(msg);
        }
        return method;
    }
}
