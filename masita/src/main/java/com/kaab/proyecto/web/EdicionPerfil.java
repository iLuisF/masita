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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 * Permite editar un perfil de usuario en la base de datos
 * @author 
 */
@ManagedBean
@ViewScoped
public class EdicionPerfil implements Serializable {
    
    private Usuario usuario = new Usuario();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private UsuarioJpaController controlador = new UsuarioJpaController(emf);
    private List<Usuario> listaUsuarios = controlador.findUsuarioEntities();       // Lista de todos los usuarios
    private String nombre;
    private String app;
    private String apm;
    private String correo;
    private String nombreUsuario;
    private String contrasenia;
    private Long idUsuario;
    private List<Usuario> listaUsuario;
    private String activo;
    private int esAdministrador;
    private FacesContext context;
    private FacesMessage msg;

    /**
     * Obtiene si el usuario está activo.
     * @return si el usuario está activo
     */
    public String getActivo() {
        return activo;
    }

    /**
     * Cambia si el usuario está activo.
     * @param activo el nuevo valor para activar un usuario
     */
    public void setActivo(String activo) {
        this.activo = activo;
    }

    /**
     * Obtiene si el usuario es un administrador.
     * @return si el usuario es un administrador
     */
    public int getEsAdministrador() {
        return esAdministrador;
    }

    /**
     * Cambia si el usuario es un administrador.
     * @param esAdministrador el valor para saber si el usuario es un administrador
     */
    public void setEsAdministrador(int esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    /**
     * Obtiene el id de un usuario.
     * @return el id de un usuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Cambia el id de un usuario.
     * @param idUsuario el nuevo id de un usuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Obtiene el nombre de un usuario.
     * @return el nombre de un usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de un usuario.
     * @param nombre el nuevo nombre de un usuario
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
     * Cambia el apellido paterno de un usuario.
     * @param app el nuevo apellido paterno de un usuario
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
     * Cambia el apellido materno de un usuario.
     * @param apm el nuevo apellido materno de un usuario
     */
    public void setApm(String apm) {
        this.apm = apm;
    }

    /**
     * Obtiene el correo de un usuario.
     * @return el correo de un usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Cambia el correo de un usuario.
     * @param correo el nuevo correo de un usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el nombre de usuario de un usuario.
     * @return el nombre de usuario de un usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Cambia el nombre de usuario de un usuario.
     * @param nombreUsuario el nuevo nombre de usuario de un usuario
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
     * Cambia la contraseña de un usuario.
     * @param contrasenia la nueva contraseña de un usuario
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene un usuario.
     * @return un usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Cambia un usuario.
     * @param usuario el nuevo usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Obtiene la información de un usuario.
     * @return la información de un usuario
     */
    public Usuario getInformacionUsuario() {
        usuario = controlador.findUsuario(this.idUsuario);
        return usuario;
    }
    
    /**
     * Busca un usuario en la base de datos.
     * @param actual el usuario a buscar
     * @return el usuario que se busca
     */
    private Usuario busca(Usuario actual) {
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
     * @param idUsuario el id del usuario a editar
     */
    public void MandaEditar(Long idUsuario) {
        Usuario nvoUsuario = new Usuario(idUsuario);
        Usuario actual = this.busca(nvoUsuario);
        this.setIdUsuario(idUsuario);
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
     * @throws Exception si no se puede editar un usuario
     */
    public void edita() throws Exception {
        Usuario u = new Usuario(this.getIdUsuario());
        if (!this.getNombre().equals("") && !this.getApp().equals("") && !this.getApm().equals("") && !this.getNombreUsuario().equals("") && !this.getContrasenia().equals("") && validarNombreUsuario(this.getNombreUsuario(), this.getIdUsuario())){
            u.setNombre(this.getNombre());
            u.setApp(this.getApp());
            u.setApm(this.getApm());
            u.setNombreUsuario(this.getNombreUsuario());
            u.setContrasenia(this.getContrasenia());
            u.setActivo(this.getActivo());
            u.setEsAdministrador(this.getEsAdministrador());
            u.setCorreo(this.getCorreo());

            controlador.edit(u);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Editado", null);
            
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al editar el usuario", null);
        }
        context = FacesContext.getCurrentInstance();
        context.addMessage(null, msg); 
        context.getExternalContext().redirect("/masita/EditarPerfilIH.xhtml?idUsuario="+this.getIdUsuario());
        
    }
    
    /**
     * Crea una lista con todos los usuarios.
     * @return una lista con todos los usuarios
     */
    public List<Usuario> crearListaUsuarios() {
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
     * @param u, el Usuario del que se validará el nombre.
     */
    private boolean validarNombreUsuario(String nombreUsuario, Long id){
        boolean condicion = true; 
        for(Usuario x : listaUsuarios){
            if(x.getNombreUsuario().equals(nombreUsuario)  && !Objects.equals(id, x.getIdUsuario())){
                msg = new FacesMessage("El nombre de usuario ya existe");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                condicion = false;
            }
        }
        return condicion;
    }

}
