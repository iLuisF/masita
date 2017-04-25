package com.kaab.proyecto.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Managed Bean para manejar el cierre de sesión de la aplicación.
 */
@ManagedBean // LEER LA DOCUMENTACION DE ESTA ANOTACION: Permite dar de alta al bean en la aplicacion
@RequestScoped // Solo esta disponible a partir de peticiones al bean
public class CierreSesion {

    private String usuario; // Representa el nombre de usuario.
    private final HttpServletRequest httpServletRequest; // Obtiene informacion de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene informacion de la aplicacion
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista.

    /**
     * Constructor para inicializar los valores de faceContext y
     * httpServletRequest, además de la sesión de usuario.
     */
    public CierreSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sessionUsuario") != null) {
            usuario = httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
        }
    }

    /**
     * Método encargado de cerrar la sesión de la aplicación.
     *
     * @return El nombre de la vista que va a responder.
     */
    public String cierreSesion() {
        httpServletRequest.getSession().removeAttribute("sessionUsuario");
        // httpServletRequest.getSession().destroy();
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Session cerrada correctamente", null);
        faceContext.addMessage(null, message);
        return "PrincipalIH";
    }

    /**
     * Regresa el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario El nombre de usuario a establecer.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Si el usuario inicio sesión, entonces se le mostrara el botón de cerrar
     * sesión.
     * @return 
     */
    public boolean mostrarCierreSesion(){
        if (httpServletRequest.getSession().getAttribute("sessionUsuario") != null) {
            return true;
        }
            return false;
    }

}