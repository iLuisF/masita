/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.UsuarioJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.RowEditEvent;

/**
 * Clase que elimina Usuarios (activa o desactiva)
 * @author esperanzahigareda
 */
@ManagedBean (name="editarUsuario")
@ViewScoped
public class Usuarios implements Serializable {
    
    private Long idUsuario;
    private String nombre;
    private String app;
    private String apm;
    private UsuarioJpaController usuario;
    private List<Usuario> listaU;
    
    private String nombreCompleto;
    
    /**
     * Obtiene la lista de usuarios.
     * @return lista de usuarios
     */
    public List<Usuario> getListaUsuarios(){
        listaU = new ArrayList<Usuario>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        usuario = new UsuarioJpaController(emf);

        List<Usuario> usuarios = usuario.findUsuarioEntities();
        
        for (Usuario u : usuarios) {
            u.setIdUsuario(u.getIdUsuario());
            u.setNombre(u.getNombre());
            u.setActivo(u.getActivo());
            listaU.add(u);
        }
        return listaU;
    }
   
    /**
     * Si se selecciona el simbolo "paloma", se mantiene al usuario activo.
     * @param event
     * @throws Exception 
     */
    public void onRowEdit(RowEditEvent event) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        usuario = new UsuarioJpaController(emf);
        Usuario u = usuario.findUsuario(((Usuario) event.getObject()).getIdUsuario());
        u.setActivo("1");
        usuario.edit(u);
        
        FacesMessage msg = new FacesMessage("Usuario Activado", "Usuario ID: " +Long.toString(((Usuario) event.getObject()).getIdUsuario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Si se selecciona el simbolo "cruz", se mantiene al usuario activo.
     * @param event
     * @throws Exception 
     */
    public void onRowCancel(RowEditEvent event) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        usuario = new UsuarioJpaController(emf);
        Usuario u = usuario.findUsuario(((Usuario) event.getObject()).getIdUsuario());
        u.setActivo("0");
        usuario.edit(u);
        
        FacesMessage msg = new FacesMessage("Usuario Desactivado", "Usuario ID: "+ Long.toString(((Usuario) event.getObject()).getIdUsuario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
