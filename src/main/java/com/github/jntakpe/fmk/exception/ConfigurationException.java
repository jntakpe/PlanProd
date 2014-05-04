package com.github.jntakpe.fmk.exception;

/**
 * Classe d'exception utilisée pour les erreurs de développement/configuration
 *
 * @author jntakpe
 */
public class ConfigurationException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    public ConfigurationException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ConfigurationException(Throwable cause) {
        super(cause);
    }
}
