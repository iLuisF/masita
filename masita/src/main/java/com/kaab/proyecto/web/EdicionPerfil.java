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
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Permite editar un perfil de usuario en la base de datos.
 *
 * @author esponjoso
 */
@ManagedBean
@ViewScoped
public class EdicionPerfil implements Serializable {

    /**
     * Usuario del cual se mostrarán los datos del perfil.
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
     * Lista de todos los usuarios.
     */
    private final List<Usuario> listaUsuarios
        = controlador.findUsuarioEntities();
    /**
     * String nombre que representará el nombre del usuario.
     */
    private String nombre;
    /**
     * String app que representará el apellido paterno del usuario.
     */
    private String app;
    /**
     * String apm que representará el apellido materno del usuario.
     */
    private String apm;
    /**
     * String correo que representará el correo del usuario.
     */
    private String correo;
    /**
     * String nombreUsuario que representará el nombre de usuario del usuario.
     */
    private String nombreUsuario;
    /**
     * String contrasenia que representará la contraseña del usuario.
     */
    private String contrasenia;
    /**
     * Id del usuario.
     */
    private Long idUsuario;
    /**
     * Lista de usuarios.
     */
    private List<Usuario> listaUsuario;
    /**
     * String activo que dirá si un Usuario está activo o no.
     */
    private String activo;
    /**
     * Nos dirá si un Usuario es administrador o no.
     */
    private int esAdministrador;
    /**
     * Context.
     */
    private FacesContext context;
    /**
     * Msg.
     */
    private FacesMessage msg;

    /**
     * Obtiene si el usuario está activo.
     *
     * @return si el usuario está activo
     */
    public final String getActivo() {
        return activo;
    }

    /**
     * Cambia si el usuario está activo.
     *
     * @param pActivo el nuevo valor para activar un usuario
     */
    public final void setActivo(final String pActivo) {
        this.activo = pActivo;
    }

    /**
     * Obtiene si el usuario es un administrador.
     *
     * @return si el usuario es un administrador
     */
    public final int getEsAdministrador() {
        return esAdministrador;
    }

    /**
     * Cambia si el usuario es un administrador.
     *
     * @param pEsAdministrador se usará en caso de volver al Usuario un
     * Administrador.
     */
    public final void setEsAdministrador(final int pEsAdministrador) {
        this.esAdministrador = pEsAdministrador;
    }

    /**
     * Obtiene el id de un usuario.
     *
     * @return el id de un usuario
     */
    public final Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Cambia el id de un usuario.
     *
     * @param pIdUsuario el nuevo id de un usuario
     */
    public final void setIdUsuario(final Long pIdUsuario) {
        this.idUsuario = pIdUsuario;
    }

    /**
     * Obtiene el nombre de un usuario.
     *
     * @return el nombre de un usuario
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de un usuario.
     *
     * @param pNombre el nuevo nombre de un usuario
     */
    public final void setNombre(final String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Obtiene el apellido paterno de un usuario.
     *
     * @return el apellido paterno de un usuario
     */
    public final String getApp() {
        return app;
    }

    /**
     * Cambia el apellido paterno de un usuario.
     *
     * @param pApp el nuevo apellido paterno de un usuario
     */
    public final void setApp(final String pApp) {
        this.app = pApp;
    }

    /**
     * Obtiene el apellido materno de un usuario.
     *
     * @return el apellido materno de un usuario
     */
    public final String getApm() {
        return apm;
    }

    /**
     * Cambia el apellido materno de un usuario.
     *
     * @param pApm el nuevo apellido materno de un usuario
     */
    public final void setApm(final String pApm) {
        this.apm = pApm;
    }

    /**
     * Obtiene el correo de un usuario.
     *
     * @return el correo de un usuario
     */
    public final String getCorreo() {
        return correo;
    }

    /**
     * Cambia el correo de un usuario.
     *
     * @param pCorreo el nuevo correo de un usuario
     */
    public final void setCorreo(final String pCorreo) {
        this.correo = pCorreo;
    }

    /**
     * Obtiene el nombre de usuario de un usuario.
     *
     * @return el nombre de usuario de un usuario
     */
    public final String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Cambia el nombre de usuario de un usuario.
     *
     * @param pNombreUsuario el nuevo nombre de usuario de un usuario
     */
    public final void setNombreUsuario(final String pNombreUsuario) {
        this.nombreUsuario = pNombreUsuario;
    }

    /**
     * Obtiene la contraseña de un usuario.
     *
     * @return la contraseña de un usuario
     */
    public final String getContrasenia() {
        return contrasenia;
    }

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param pContrasenia la nueva contraseña de un usuario.
     */
    public final void setContrasenia(final String pContrasenia) {
        this.contrasenia = pContrasenia;
    }

    /**
     * Obtiene un usuario.
     *
     * @return un usuario
     */
    public final Usuario getUsuario() {
        return usuario;
    }

    /**
     * Cambia un usuario.
     *
     * @param pUsuario el nuevo usuario
     */
    public final void setUsuario(final Usuario pUsuario) {
        this.usuario = pUsuario;
    }

    /**
     * Obtiene la información de un usuario.
     *
     * @return la información de un usuario
     */
    public final Usuario getInformacionUsuario() {
        usuario = controlador.findUsuario(this.idUsuario);
        return usuario;
    }

    /**
     * Busca un usuario en la base de datos.
     *
     * @param actual el usuario a buscar
     * @return el usuario que se busca
     */
    private Usuario busca(final Usuario actual) {
        List<Usuario> actuales = this.crearListaUsuarios();
        for (Usuario x : actuales) {
            if (Objects.equals(actual.getIdUsuario(), x.getIdUsuario())) {
                return x;
            }
        }
        return null;
    }

    /**
     * Manda editar un usuario.
     *
     * @param pIdUsuario el id del usuario a editar
     */
    public final void mandaEditar(final Long pIdUsuario) {
        Usuario nvoUsuario = new Usuario(pIdUsuario);
        Usuario actual = this.busca(nvoUsuario);
        this.setIdUsuario(pIdUsuario);
        this.setNombre(actual.getNombre());
        this.setApp(actual.getApp());
        this.setApm(actual.getApm());
        this.setCorreo(actual.getCorreo());
        this.setContrasenia(actual.getContrasenia());
        this.setNombreUsuario(actual.getNombreUsuario());
        this.setActivo(actual.getActivo());
        this.setEsAdministrador(actual.getEsAdministrador());
    }

    /**
     * Edita un usuario.
     *
     * @throws Exception si no se puede editar un usuario
     */
    public final void edita() throws Exception {
        Usuario u = new Usuario(this.getIdUsuario());
        if (!this.getNombre().equals("") && !this.getApp().equals("")
                && !this.getApm().equals("")
                && !this.getNombreUsuario().equals("")
                && !this.getContrasenia().equals("")
                && validarNombreUsuario(this.getNombreUsuario(),
                        this.getIdUsuario())) {
            u.setNombre(this.getNombre());
            u.setApp(this.getApp());
            u.setApm(this.getApm());
            u.setNombreUsuario(this.getNombreUsuario());
            u.setContrasenia(this.getContrasenia());
            u.setActivo(this.getActivo());
            u.setEsAdministrador(this.getEsAdministrador());
            u.setCorreo(this.getCorreo());

            controlador.edit(u);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Usuario Editado", null);

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Error al editar el usuario", null);
        }
        context = FacesContext.getCurrentInstance();
        context.addMessage(null, msg);
        context.getExternalContext().redirect(
            "/masita/EditarPerfilIH.xhtml?idUsuario=" + this.getIdUsuario());

    }

    /**
     * Crea una lista con todos los usuarios.
     *
     * @return una lista con todos los usuarios
     */
    public final List<Usuario> crearListaUsuarios() {
        listaUsuario = new ArrayList<Usuario>();

        for (Usuario actual : listaUsuarios) {
            Usuario nvo = new Usuario(actual.getIdUsuario());
            nvo.setIdUsuario(actual.getIdUsuario());
            nvo.setNombre(actual.getNombre());
            nvo.setApp(actual.getApp());
            nvo.setApm(actual.getApm());
            nvo.setCorreo(actual.getCorreo());
            nvo.setActivo(actual.getActivo());
            nvo.setContrasenia(actual.getContrasenia());
            nvo.setNombreUsuario(actual.getNombreUsuario());
            nvo.setEsAdministrador(actual.getEsAdministrador());
            listaUsuario.add(nvo);
        }
        return listaUsuario;
    }

    /**
     * Revisa que un usuario ya registrado no tenga el mismo nombre de otro
     * usuario ya existente.
     *
     * @param nombreUsuarioVal nombre del usuario a validar.
     * @param id el id del usuario.
     * @return condicion.
     */
    private boolean validarNombreUsuario(final String nombreUsuarioVal,
        final Long id) {
        boolean condicion = true;
        for (Usuario x : listaUsuarios) {
            if (x.getNombreUsuario().equals(nombreUsuarioVal)
                    && !Objects.equals(id, x.getIdUsuario())) {
                msg = new FacesMessage("El nombre de usuario ya existe");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                condicion = false;
            }
        }
        return condicion;
    }
    /**
     *Establece el id del usuario a desactivar.
     * @param pIdUsuario 
     */
    public final void mandaEliminar(final Long pIdUsuario) {
       this.setIdUsuario(pIdUsuario);
    }
    /**
     * Convierte el atributo "Activo" de un usuario en 0.
     *
     */
    public final void desactiva() throws Exception {
        Usuario u = controlador.findUsuario(this.getIdUsuario());
        controlador.destroy(u.getIdUsuario());
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("/masita/PrincipalIH.xhtml");
        //CierreSesion cs = new CierreSesion();
        //cs.setUsuario(String.valueOf(this.getIdUsuario()));
        //cs.cierreSesion();
    }

}
