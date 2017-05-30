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
 * Clase que elimina Usuarios (activa o desactiva).
 */
@ManagedBean (name = "editarUsuario")
@ViewScoped
public class EliminacionUsuario implements Serializable {

    /**
     * Entity Manager Factory para tener persistencia.
     */
    private final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("MiProyectoPU");
    /**
     * Controlador de un Usuario.
     */
    private final UsuarioJpaController usuario = new UsuarioJpaController(emf);
    /**
     * El id de un usuario.
     */
    private Long idUsuario;
    /**
     * El nombre de un usuario.
     */
    private String nombre;
    /**
     * El apellido materno de un usuario.
     */
    private String app;
    /**
     * El apellido materno de un usuario.
     */
    private String apm;
    /**
     * Indica si un usuario está activo o no.
     */
    private String activo;
    /**
     * Lista de todos los usuarios.
     */
    private List<Usuario> listaU;
    /**
     * Lista de usuarios, filtrados por nombre.
     */
    private List<Usuario> usuariosFiltrados;

    /**
     * Obtiene la lista filtrada de usuarios por nombre.
     * @return lista filtrada de usuarios por nombre
     */
    public final List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    /**
     * Modifica lista filtrada de usuarios por nombre.
     * @param pUsuariosFiltrados nueva lista filtrada de usuarios por nombre
     */
    public final void setUsuariosFiltrados(final List<Usuario>
            pUsuariosFiltrados) {
        this.usuariosFiltrados = pUsuariosFiltrados;
    }

    /**
     * Obtiene la lista de usuarios.
     * @return lista de usuarios
     */
    public final List<Usuario> getListaUsuarios() {
        listaU = new ArrayList<Usuario>();
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
     * Manda el id del Usuario al dar click.
     * @param pIdUsuario el id del usuario.
     */
    public final void mandaAdmin(final Long pIdUsuario) {
        this.idUsuario = pIdUsuario;
    }

    /**
     * Hace administrador a un usuario activo.
     * @throws Exception si no encuentra al usuario.
     */
    public final void hacerAdmin() throws Exception {
        Usuario u = usuario.findUsuario(idUsuario);
        u.setEsAdministrador(1);
        if ("0".equals(u.getActivo())) {
            System.out.println("No puede ser administrador. No es activo.");
        } else {
            u.setActivo("1");
            usuario.edit(u);
            FacesMessage msg = new FacesMessage("Este Usuario es Administrador",
                    "Usuario ID: " + Long.toString((u).getIdUsuario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/masita"
                    + "/EliminarUsuarioIH.xhtml");
        }
    }

    /**
     * Si se selecciona el simbolo "paloma", se mantiene al usuario activo.
     * @param event el evento
     * @throws Exception si no se puede editar el usuario
     */
    public final void onRowEdit(final RowEditEvent event) throws Exception {
        Usuario u = usuario.findUsuario(((Usuario)
                event.getObject()).getIdUsuario());
        u.setActivo("1");
        usuario.edit(u);
        FacesMessage msg = new FacesMessage("Usuario Activado",
                "Usuario ID: " + Long.toString(((Usuario)
                        event.getObject()).getIdUsuario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Si se selecciona el simbolo "cruz", se mantiene al usuario activo.
     * @param event el evento
     * @throws Exception si no se puede editar el usuario
     */
    public final void onRowCancel(final RowEditEvent event) throws Exception {
        Usuario u = usuario.findUsuario(((Usuario)
                event.getObject()).getIdUsuario());
        u.setActivo("0");
        usuario.destroy(u.getIdUsuario());
        FacesMessage msg = new FacesMessage("Usuario Eliminado",
                "Usuario ID: " + Long.toString(((Usuario)
                        event.getObject()).getIdUsuario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
         FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/masita/EliminarUsuarioIH.xhtml");
    }
}