package com.github.jntakpe.pp.web;

import com.github.jntakpe.pp.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Publication de la ressource relative à l'authentification d'un utilisateur
 *
 * @author jntakpe
 */
@RestController
@RequestMapping("/user")
public class UserResource {

    @RequestMapping(method = RequestMethod.GET)
    public User getCurrentUser() {
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setLogin(authentication.getName());
        user.setAuthorities(authentication.getAuthorities());
        return user;
    }

}
