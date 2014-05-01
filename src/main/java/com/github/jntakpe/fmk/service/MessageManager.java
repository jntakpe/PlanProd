package com.github.jntakpe.fmk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Encapsulation du logger
 *
 * @author jntakpe
 */
@Component
public class MessageManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageManager.class);

    @Autowired
    private MessageSource messageSource;

    /**
     * Récupère un message d'erreur dans les bundles de l'application et remplace les variables du message
     *
     * @param codeMessage code du message
     * @param args        variables du message
     * @return le message final avec les variables remplacées
     */
    public String getMessage(String codeMessage, Object... args) {
        return messageSource.getMessage(codeMessage, args, Locale.FRANCE);
    }

    /**
     * Ecrit dans la log un message avec le niveau trace
     *
     * @param codeMessage code du message
     * @param args        variables du message
     */
    public void trace(String codeMessage, Object... args) {
        LOGGER.trace(getMessage(codeMessage, args));
    }

    /**
     * Ecrit dans la log un message avec le niveau debug
     *
     * @param codeMessage code du message
     * @param args        variables du message
     */
    public void debug(String codeMessage, Object... args) {
        LOGGER.debug(getMessage(codeMessage, args));
    }

    /**
     * Ecrit dans la log un message avec le niveau info
     *
     * @param codeMessage code du message
     * @param args        variables du message
     */
    public void info(String codeMessage, Object... args) {
        LOGGER.info(getMessage(codeMessage, args));
    }

    /**
     * Ecrit dans la log un message avec le niveau warn
     *
     * @param codeMessage code du message
     * @param args        variables du message
     */
    public void warn(String codeMessage, Object... args) {
        LOGGER.warn(getMessage(codeMessage, args));
    }

    /**
     * Ecrit dans la log un message avec le niveau error
     *
     * @param codeMessage code du message
     * @param args        variables du message
     */
    public void error(String codeMessage, Object... args) {
        LOGGER.error(getMessage(codeMessage, args));
    }

}
