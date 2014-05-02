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
public abstract class GenericController<T extends GenericDomain<S>, S extends Number> {

    @Autowired
    protected MessageManager messageManager;

    @Autowired
    private GenericService<T, S> genericService;

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
        return "lists/" + genericService.getDomainClass().getSimpleName().toLowerCase();
    }

    /**
     * Renvoie toutes les entités. La liste devra être paginée, triée et filtrée par les clients.
     *
     * @return toutes les entités de la table
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<T> findListData() {
        return genericService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ActionResult<T> delete(@PathVariable S id) {
        T entity = genericService.findOne(id);
        genericService.delete(id);
        messageManager.info("entity.delete", currentUsername(), entity);
        String guiMsg = messageManager.getMessage("delete.success");
        return new ActionResult.SuccessBuilder<>(entity, Operation.DELETE).message(guiMsg).build();
    }

    private String currentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
