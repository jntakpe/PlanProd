package com.github.jntakpe.fmk.web;

import com.github.jntakpe.fmk.domain.GenericDomain;
import com.github.jntakpe.fmk.service.GenericService;
import com.github.jntakpe.fmk.service.MessageManager;
import com.github.jntakpe.fmk.web.dto.ActionResult;
import com.github.jntakpe.fmk.web.dto.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Contrôlleur générique contenant les méthodes communes aux écrans de type liste + popup et liste + détail.
 *
 * @author jntakpe
 */
public abstract class ListController<T extends GenericDomain<S>, S extends Number> {

    @Autowired
    protected GenericService<T, S> genericService;

    @Autowired
    protected MessageManager messageManager;

    /**
     * Type de la liste
     */
    protected enum ListType {
        POPUP, DETAIL
    }

    /**
     * Renvoie le type de la liste
     *
     * @return type de la liste
     */
    public abstract ListType getListType();

    /**
     * Affiche la vue de la liste
     *
     * @return vue de la liste
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayList() {
        return new ModelAndView(getListViewPath());
    }

    /**
     * Récupère le nom de la vue de la liste correspondant à cette entité.
     *
     * @return le chemin de la vue
     */
    protected String getListViewPath() {
        return "lists/" + genericService.getEntityClass().getSimpleName().toLowerCase();
    }

    /**
     * Renvoie toutes les entités. La liste devra être paginée, triée et filtrée par les clients.
     *
     * @return toutes les entités de la table
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<T> findListData() {
        return genericService.findAll();
    }

    /**
     * Suppression d'un enregistrement
     *
     * @param id identifiant de l'enregistrement à supprimer
     * @return le résultat de l'opération
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ActionResult<T> delete(@PathVariable S id) {
        T entity = genericService.findOne(id);
        genericService.delete(id);
        messageManager.info("entity.delete", currentUsername(), entity);
        String guiMsg = messageManager.getMessage("delete.success", entity);
        return new ActionResult.SuccessBuilder<>(entity, Operation.DELETE).message(guiMsg).build();
    }

    /**
     * Renvoi le nom de l'utilisateur courant
     *
     * @return le nom de l'utilsateur courant
     */
    protected String currentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
