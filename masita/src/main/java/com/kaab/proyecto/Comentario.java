package com.kaab.proyecto;


import java.util.Date;


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
@ManagedBean(name = "comentario", eager = true)
@RequestScoped
public class Comentario {
    
    private UsuarioCiencias usuario;
    private String contenido;
    private int calificacion;
    private Date fecha;

    public UsuarioCiencias getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCiencias usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public String toString(){
        return contenido;
    }
    
}
