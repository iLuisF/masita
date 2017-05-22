/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db.controller.exceptions;

/**
 *
 * @author luis
 */
public class ErrorCrearComentario extends Exception {
    /**
     * Error.
     */
    public ErrorCrearComentario() {
        super();
    }
    /**
     * @param mensaje string
     */
    public ErrorCrearComentario(final String mensaje) {
        super(mensaje);
    }
    /**
     * @param string string
     * @param viewId string
     */
    public ErrorCrearComentario(final String string, final String viewId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}