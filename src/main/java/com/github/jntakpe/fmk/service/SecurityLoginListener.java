package com.github.jntakpe.fmk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Classe chargée de logger les évennements relatifs aux connexions et déconnexions des utilisateurs
 *
 * @author jntakpe
 */
@Component
public class SecurityLoginListener implements ApplicationListener<AbstractAuthenticationEvent> {

    @Autowired
    private MessageManager messageManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        if (isSuccessEvent(event)) {
            messageManager.info("user.connect", event.getAuthentication().getName());
        } else if (isFailureEvent(event)) {
            messageManager.warn("authentication.failure", ((AbstractAuthenticationFailureEvent) event).getException().getMessage());
        }
    }

    private boolean isSuccessEvent(AbstractAuthenticationEvent event) {
        return event instanceof InteractiveAuthenticationSuccessEvent || event instanceof AuthenticationSuccessEvent;
    }

    private boolean isFailureEvent(AbstractAuthenticationEvent event) {
        return event instanceof AbstractAuthenticationFailureEvent;
    }
}
