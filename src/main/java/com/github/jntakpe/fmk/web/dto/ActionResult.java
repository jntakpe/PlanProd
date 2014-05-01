package com.github.jntakpe.fmk.web.dto;

import com.github.jntakpe.fmk.domain.GenericDomain;

/**
 * Réponse renvoyée par l'application aux clients lorsqu'une opération a été effectuée.
 * Contient les informations nécessaires à l'IHM pour afficher le message d'adéquat et en cas d'erreur revenir à l'état précédent
 * Implémente le pattern builder.
 *
 * @author jntakpe
 */
public final class ActionResult<T extends GenericDomain> {

    /**
     * Indique si l'opération a réussi
     */
    private final boolean success;

    /**
     * Entité sur laquelle l'opération a été effectuée
     */
    private final T entity;

    /**
     * Type d'opération effectuée
     */
    private final Operation operation;

    /**
     * Message a afficher à l'IHM
     */
    private final String message;

    /**
     * Données suplémentaires
     */
    private final Object data;

    private ActionResult(Builder<T> builder) {
        this.success = builder.success;
        this.entity = builder.entity;
        this.operation = builder.operation;
        this.message = builder.message;
        this.data = builder.data;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getEntity() {
        return entity;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    /**
     * Inner class abstraite permettant de créer l'instance de {@link ActionResult}.
     * Peut-être étendue par {@link com.github.jntakpe.fmk.web.dto.ActionResult.SuccessBuilder} en cas d'opération réussie ou par
     * {@link com.github.jntakpe.fmk.web.dto.ActionResult.ErrorBuilder} en cas d'échec de l'opération.
     *
     * @param <T> type de l'entité
     */
    public abstract static class Builder<T extends GenericDomain> {

        protected boolean success;

        protected T entity;

        protected Operation operation;

        protected String message;

        protected Object data;

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(Object data) {
            this.data = data;
            return this;
        }

        public ActionResult<T> build() {
            return new ActionResult<>(this);
        }
    }

    /**
     * Construit un {@link com.github.jntakpe.fmk.web.dto.ActionResult.Builder} en cas de succès
     *
     * @param <T> type de l'entité
     */
    public static class SuccessBuilder<T extends GenericDomain> extends Builder<T> {

        public SuccessBuilder(T entity, Operation operation) {
            success = true;
            this.entity = entity;
            this.operation = operation;
        }

    }

    /**
     * Construit un {@link com.github.jntakpe.fmk.web.dto.ActionResult.Builder} en cas d'échec
     *
     * @param <T> type de l'entité
     */
    public static class ErrorBuilder<T extends GenericDomain> extends Builder<T> {

        public ErrorBuilder(T entity, Operation operation) {
            success = false;
            this.entity = entity;
            this.operation = operation;
        }

    }
}
