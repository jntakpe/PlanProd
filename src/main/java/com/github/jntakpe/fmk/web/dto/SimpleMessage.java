package com.github.jntakpe.fmk.web.dto;

/**
 * Message renvoyé par le framework à la vue
 *
 * @author jntakpe
 */
public class SimpleMessage {

    /**
     * Indique si l'opération a réussi
     */
    protected boolean success;

    /**
     * Message a afficher à l'IHM
     */
    protected String message;

    /**
     * Construit une instance de {@link com.github.jntakpe.fmk.web.dto.SimpleMessage}
     *
     * @param success résultat de l'opération
     * @param message message à afficher à l'IHM
     */
    protected SimpleMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Renvoi un message de réussite
     *
     * @param message message à envoyer à la vue
     * @return instance de {@link com.github.jntakpe.fmk.web.dto.SimpleMessage} de réussite
     */
    public static SimpleMessage createSuccessMessage(String message) {
        return new SimpleMessage(true, message);
    }

    /**
     * Renvoi un message d'échec
     *
     * @param message message d'échec à envoyer à la vue
     * @return instance de {@link com.github.jntakpe.fmk.web.dto.SimpleMessage} d'échec
     */
    public static SimpleMessage createFailureMessage(String message) {
        return new SimpleMessage(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleMessage that = (SimpleMessage) o;

        if (success != that.success) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
