package com.kaab.proyecto;


import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Flores González Luis.
 * @version 1.0 - Abril del 2017
 */
@ManagedBean(name = "crearcomentario", eager = true)
@RequestScoped
public class CrearComentario {
    
    private Comentario nuevo;
    
    public String crearComentario(Comentario nuevo){
        this.nuevo = nuevo;
        return "Se creo con exito ->" + nuevo;
    }
    
    public String crearComentario(){
        return "Se creo con exito ->" + nuevo;
    }
    
}
