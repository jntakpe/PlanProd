package com.github.jntakpe.pp.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Contrôleur gérant les écrans du framework
 *
 * @author jntakpe
 */
@Controller
public class FmkController {


    /**
     * Si l'utilisateur est connecté renvoi vers l'écran d'accueil sinon renvoi vers la page de login en ajoutant un
     * message d'erreur
     *
     * @param request requête http
     * @param error   cause de l'échec de l'authentification
     * @return vue à afficher
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, @RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("fmk/login");
        return StringUtils.isBlank(error) ? mv.addObject("ERROR") : mv;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "fmk/home";
    }
}
