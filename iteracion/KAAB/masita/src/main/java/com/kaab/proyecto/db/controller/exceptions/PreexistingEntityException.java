package com.kaab.proyecto.db.controller.exceptions;
/**
 * @author esponjoso
 */
public class PreexistingEntityException extends Exception {
    /**
     * @param message mensaje
     * @param cause causa
     */
    public PreexistingEntityException(final String message,
            final Throwable cause) {
        super(message, cause);
    }
    /**
     * @param message mensaje.
     */
    public PreexistingEntityException(final String message) {
        super(message);
    }
}
