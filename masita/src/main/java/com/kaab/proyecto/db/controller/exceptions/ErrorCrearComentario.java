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
public class ErrorCrearComentario extends Exception{
    
    public ErrorCrearComentario(){
        super();
    }
    
    public ErrorCrearComentario(String mensaje){
        super(mensaje);
    }

    public ErrorCrearComentario(String string, String viewId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
