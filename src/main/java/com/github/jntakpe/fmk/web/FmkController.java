package com.github.jntakpe.fmk.web;

import com.github.jntakpe.fmk.service.MessageManager;
import com.github.jntakpe.fmk.web.dto.SimpleMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur gérant les écrans du framework
 *
 * @author jntakpe
 */
@Controller
public class FmkController {

    @Autowired
    private MessageManager messageManager;

    /**
     * Si l'utilisateur est connecté renvoi vers l'écran d'accueil sinon renvoi vers la page de login en ajoutant un message d'erreur
     *
     * @param error cause de l'échec de l'authentification
     * @return vue correspodante à la page de login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout) {
        ModelAndView mv = new ModelAndView("fmk/login");
        if (!StringUtils.isBlank(error)) {
            return mv.addObject(SimpleMessage.createFailureMessage(messageManager.getMessage("authentication.failure")));
        } else if (!StringUtils.isBlank(logout)) {
            return mv.addObject(SimpleMessage.createSuccessMessage(messageManager.getMessage("logout.success")));
        }
        return mv;
    }

    /**
     * Renvoi vers la page d'accueil
     *
     * @return vue correspondante à la page d'accueil
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "fmk/home";
    }
}
