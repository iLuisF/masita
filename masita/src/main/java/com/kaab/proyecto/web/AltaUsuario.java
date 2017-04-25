/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.controller.UsuarioJpaController;
import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.ComentarioJpaController;
import static com.sun.faces.facelets.util.Path.context;
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
import javax.faces.context.FacesContext;

/**
 * Da de alta a un usuario en la base de datos.
 * @author esponjoso
 */
@ManagedBean
@ViewScoped
public class AltaUsuario implements Serializable {
    
    private Usuario usuario = new Usuario();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private final UsuarioJpaController controlador = new UsuarioJpaController(emf);
    private List<Usuario> listaUsuarios = controlador.findUsuarioEntities();       // Lista de todos los usuarios
    private String nombre;
    private String app;
    private String apm;
    private String correo;
    private String nombreUsuario;
    private String contrasenia;
    private Long idUsuario;

    /**
     * Permite obtener el Id de un usuario.
     * @return el Id de un usuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Permite cambiar el valor del Id de un usuario.
     * @param idUsuario, el nuevo valor del atributo idUsuario del usuario. 
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Perimite obtener un usuario.
     * @return un usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Permite cambiar un usuario.
     * @param usuario, el nuevo usuario por el que se cambiará al viejo usuario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Permite obtener el nombre de un usuario.
     * @return el nombre de un usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite cambiar el atributo nombre de un usuario.
     * @param nombre, el nuevo valor del atributo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno de un usuario.
     * @return el apellido paterno de un usuario
     */
    public String getApp() {
        return app;
    }

    /**
     * Cambia el atributo app(apellido paterno) de un usuario.
     * @param app, el nuevo valor del atributo app del usuario.
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * Obtiene el apellido materno de un usuario.
     * @return el apellido materno de un usuario
     */
    public String getApm() {
        return apm;
    }

    /**
     * Cambia el atributo apm(apellido materno) de un usuario.
     * @param apm, el nuevo valor del atributo apm del usuario.
     */
    public void setApm(String apm) {
        this.apm = apm;
    }

    /**
     * Obtiene el correo electrónico de un usuario.
     * @return el correo electrónico de un usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Cambia el atributo correo(correo electrónico) de un usuario.
     * @param correo, el nuevo valor del atributo correo del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el nombre de usuario de un usuario.
     * @return el nombre de usuario de un usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Cambia el atributo nombreUsuario.
     * @param nombreUsuario, el nuevo valor para el atributo nombreUsuario del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña de un usuario.
     * @return la contraseña de un usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Cambia el atributo contrasenia de un usuario.
     * @param contrasenia, el nuevo valor para el atributo contrasenia del usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    /**
     * Da de alta un usuario en la base de datos.
     * @throws java.io.IOException si no se puede hacer la insercion en la B. D.
     */
    public void darAltaUsuario() throws IOException{
        boolean agregado = validarNombreUsuario(usuario) && validarCorreo(usuario);
        
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
            context.getExternalContext().redirect("/masita/ValidarCuentaIH.xhtml");
        }
    }
    
    /**
     * Método validarNombreUsuario que revisará que un usuario ya registrado no 
     * tenga el mismo nombre de otro usuario ya existente.
     * @param u, el Usuario del que se validará el nombre.
     */
    private boolean validarNombreUsuario(Usuario u){
        FacesMessage mensaje;
        boolean condicion = true; 
        for(Usuario x : listaUsuarios){
            if(x.getNombreUsuario().equals(u.getNombreUsuario())  && !Objects.equals(u.getIdUsuario(), x.getIdUsuario())){
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
     * @param u, el Usuario que se validará el correo electrónico.
     */
    private boolean validarCorreo(Usuario u){
        FacesMessage mensaje;
        boolean condicion = true; 
        for(Usuario x : listaUsuarios){
            if(x.getCorreo().equals(u.getCorreo())  && !Objects.equals(u.getIdUsuario(), x.getIdUsuario())){
                mensaje = new FacesMessage("El correo ya ha sido registrado");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                condicion = false;
            }
        }
        return condicion;
    }
    
}
