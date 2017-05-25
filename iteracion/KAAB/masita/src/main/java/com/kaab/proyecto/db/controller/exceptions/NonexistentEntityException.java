package com.kaab.proyecto.db.controller.exceptions;
/**
 * @author esponjoso
 */
public class NonexistentEntityException extends Exception {
    /**
     * @param message mensaje
     * @param cause causa
     */
    public NonexistentEntityException(final String message,
            final Throwable cause) {
        super(message, cause);
    }
    /**
     * @param message mensaje
     */
    public NonexistentEntityException(final String message) {
        super(message);
    }
}
