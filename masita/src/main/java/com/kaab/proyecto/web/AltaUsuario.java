/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.controller.UsuarioJpaController;
import com.kaab.proyecto.db.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Da de alta a un usuario en la base de datos.
 * @author esponjoso
 */
@ManagedBean
@ViewScoped
public class AltaUsuario implements Serializable {
    /**
    *Se crea un nuevo Usuario que será el usuario que se registró.
    */
    private Usuario usuario = new Usuario();
    /**
     * Interfaz para hacer consultas en la base de datos.
     */
    private final EntityManagerFactory emf
        = Persistence.createEntityManagerFactory("MiProyectoPU");
    /**
     * Consultas en la base de datos en la entidad Usuario.
     */
    private final UsuarioJpaController controlador
        = new UsuarioJpaController(emf);
    /**
     * Lista de todos los usuarios existentes en la base de datos.
     */
    private final List<Usuario> listaUsuarios
        = controlador.findUsuarioEntities();
    /**
     * String en donde estará el nombre del nuevo usuario.
     */
    private String nombre;
    /**
     * String en donde estará el apellido paterno del nuevo usuario.
     */
    private String app;
    /**
     * String en donde estará el apellido materno del nuevo usuario.
     */
    private String apm;
    /**
     * String en donde estará el correo del nuevo usuario.
     */
    private String correo;
    /**
     * String en donde estará el nombre de usuario del nuevo usuario.
     */
    private String nombreUsuario;
    /**
     * String en donde estará la contraseña del nuevo Usuario.
     */
    private String contrasenia;
    /**
     * El id del nuevo usuario.
     */
    private Long idUsuario;

    /**
     * Permite obtener el Id de un usuario.
     * @return el Id de un usuario
     */
    public final Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Permite cambiar el valor del Id de un usuario.
     * @param pIdUsuario el id del nuevo Usuario.
     */
    public final void setIdUsuario(final Long pIdUsuario) {
        this.idUsuario = pIdUsuario;
    }
    /**
     * Permite obtener un usuario.
     * @return un usuario
     */
    public final Usuario getUsuario() {
        return usuario;
    }

    /**
     * Permite cambiar un usuario.
     * @param pUsuario el usuario recién registrado.
     */
    public final void setUsuario(final Usuario pUsuario) {
        this.usuario = pUsuario;
    }

    /**
     * Permite obtener el nombre de un usuario.
     * @return el nombre de un usuario
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * Permite cambiar el atributo nombre de un usuario.
     * @param pNombre nombre del Usuario recién registrado.
     */
    public final void setNombre(final String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Obtiene el apellido paterno de un usuario.
     * @return el apellido paterno de un usuario
     */
    public final String getApp() {
        return app;
    }

    /**
     * Cambia el atributo app(apellido paterno) de un usuario.
     * @param pApp el apellido paterno del usuario.
     */
    public final void setApp(final String pApp) {
        this.app = pApp;
    }

    /**
     * Obtiene el apellido materno de un usuario.
     * @return el apellido materno de un usuario
     */
    public final String getApm() {
        return apm;
    }

    /**
     * Cambia el atributo apm(apellido materno) de un usuario.
     * @param pApm el apellido materno del Usuario recién registrado.
     */
    public final void setApm(final String pApm) {
        this.apm = pApm;
    }

    /**
     * Obtiene el correo electrónico de un usuario.
     * @return el correo electrónico de un usuario
     */
    public final String getCorreo() {
        return correo;
    }

    /**
     * Cambia el atributo correo(correo electrónico) de un usuario.
     * @param pCorreo el correo del Usuario recién registrado.
     */
    public final void setCorreo(final String pCorreo) {
        this.correo = pCorreo;
    }

    /**
     * Obtiene el nombre de usuario de un usuario.
     * @return el nombre de usuario de un usuario.
     */
    public final String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Cambia el atributo nombreUsuario.
     * @param pNombreUsuario el nombre de usuario del Usuario recién
     * registrado.
     */
    public final void setNombreUsuario(final String pNombreUsuario) {
        this.nombreUsuario = pNombreUsuario;
    }

    /**
     * Obtiene la contraseña de un usuario.
     * @return la contraseña de un usuario.
     */
    public final String getContrasenia() {
        return contrasenia;
    }

    /**
     * Cambia el atributo contrasenia de un usuario.
     * @param pContrasenia la contraseña del Usuario recién registrado.
     */
    public final void setContrasenia(final String pContrasenia) {
        this.contrasenia = pContrasenia;
    }
    /**
     * Da de alta un usuario en la base de datos.
     * @throws java.io.IOException si no se puede hacer la insercion en la B. D.
     */
    public final void darAltaUsuario() throws IOException {
        boolean agregado = validarNombreUsuario(usuario)
            && validarCorreo(usuario);
        if (agregado) {
            usuario.setActivo("1");
            usuario.setNombre(usuario.getNombre());
            usuario.setCorreo(usuario.getCorreo());
            usuario.setApp(usuario.getApp());
            usuario.setApm(usuario.getApm());
            usuario.setNombreUsuario(usuario.getNombreUsuario());
            usuario.setContrasenia(usuario.getContrasenia());
            usuario.setEsAdministrador(0);
            controlador.create(usuario);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(
                "/masita/ValidarCuentaIH.xhtml");
        }
    }
    /**
     * Método validarNombreUsuario que revisará que un usuario ya registrado no
     * tenga el mismo nombre de otro usuario ya existente.
     * @param u usuario al que se le validará el nombre.
     * @return condicion.
     */
    private boolean validarNombreUsuario(final Usuario u) {
        FacesMessage mensaje;
        boolean condicion = true;
        for (Usuario x : listaUsuarios) {
            if (x.getNombreUsuario().equals(u.getNombreUsuario())
                && !Objects.equals(u.getIdUsuario(), x.getIdUsuario())) {
                mensaje = new FacesMessage("El nombre de usuario ya existe");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                condicion = false;
            }
        }
        return condicion;
    }
    /**
     * Método validarCorreo que revisará que un usuario ya registrado no tenga
     * el mismo correo electrónico de otro usuario ya existente.
     * @param u usuario al que se le validará el correo.
     * @return condicion.
     */
    private boolean validarCorreo(final Usuario u) {
        FacesMessage mensaje;
        boolean condicion = true;
        for (Usuario x : listaUsuarios) {
            if (x.getCorreo().equals(u.getCorreo())
                && !Objects.equals(u.getIdUsuario(), x.getIdUsuario())) {
                mensaje = new FacesMessage("El correo ya ha sido registrado");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                condicion = false;
            }
        }
        return condicion;
    }
}
