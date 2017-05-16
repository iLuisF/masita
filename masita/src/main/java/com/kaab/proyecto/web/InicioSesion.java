package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.UsuarioJpaController;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * Bean manejado que se utiliza para el manejo de Inicio de Sesión en
 * la aplicación web.
 *
 * @author KAAB
 */
@ManagedBean
@RequestScoped // Solo esta disponible a partir de peticiones al bean
public class InicioSesion {

    /**
     * Indica si el usuario es administrador.
     */
    private int esAdministrador;
    /**
     * El correo del usuario.
     */
    private String correo;
    /**
     * La contraseña del usuario.
     */
    private String contrasenia;
    /**
     * Obtiene informacion de todas las peticiones de usuario.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * Obtiene informacion de la aplicacion.
     */
    private final FacesContext faceContext;
    /**
     * Mensaje para la aplicación.
     */
    private FacesMessage message;
    /**
     * El usuario que inicia sesion.
     */
    private Usuario usuario = new Usuario();
    /**
     * Entity Manager Factory para generar persistencia.
     */
    private final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("MiProyectoPU");
    /**
     * Controlador para el Usuario.
     */
    private final UsuarioJpaController controlador
            = new UsuarioJpaController(emf);

    /**
     * Regresa el usuario.
     * @return el usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Modifica al usuario.
     * @param usuario el nuevo usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Regresa si el usuario es administrador.
     * @return si el usuario es administrador
     */
    public int getEsAdministrador() {
        return esAdministrador;
    }

    /**
     * Modifica si el usuario es administrador.
     * @param esAdministrador el nuevo valor para saber si el usuario es administrador
     */
    public void setEsAdministrador(int esAdministrador) {
        this.esAdministrador = esAdministrador;
    }
    
    /**
     * Constructor para inicializar los valores de faceContext y
     * httpServletRequest.
     */
    public InicioSesion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param correo El nombre de usuario a establecer.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Regresa la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param pContrasenia La contraseña del usuario a establecer.
     */
    public void setContrasenia(final String pContrasenia) {
        this.contrasenia = pContrasenia;
    }

    /**
     * Método encargado de validar el inicio de sesión.
     *
     * @return El nombre de la vista que va a responder.
     */
    public final String inicioSesion() {
        if (usuario.getContrasenia().equals(encontrarContrasenia())) {
            httpServletRequest.getSession().setAttribute("sessionUsuario",
                    usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Acceso Correcto", null);
            faceContext.addMessage(null, message);
            return "PrincipalIH";
        }
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o "
                + "contraseña incorrecto", null);
        faceContext.addMessage(null, message);
        return "PrincipalIH";
    }

    /**
     * Encuentra la contrasenia desde el correo electronico.
     *
     * @return la contrasenia.
     */
    public final String encontrarContrasenia() {
        List<Usuario> temporal = controlador.findUsuarioEntities();
        for (int i = 0; i < temporal.size(); i++) {
            if (temporal.get(i).getCorreo().toLowerCase().equals(
                    this.usuario.getCorreo().toLowerCase())) {
                return temporal.get(i).getContrasenia();
            }
        }
        return null;
    }

   /**
     * Código para cambiar contenido de los botones registrarse por nombre del
     * usuario e iniciar sesión por cerrar sesión.
     *
     * @return El correo del usuario que inicio sesión.
     */
    public final String getCorreoUsuario() {
        //HttpServletRequest httpServletRequest;
        //FacesContext faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)
                faceContext.getExternalContext().getRequest();
        Usuario sesion = (Usuario) httpServletRequest.getSession().getAttribute(
                "sessionUsuario");
        if (sesion == null) { //No se inicio sesión.
            return null;
        } else {
            return sesion.getCorreo();
        }
    }

    /**
     * Indica si hay una sesión iniciada.
     * @return si hay una sesión iniciada.
     */
    public final boolean haySesion() {
     return getCorreoUsuario() == null;
    }

    /**
     * Encuentra el nombre de un usuario a partir de su correo.
     * @return el nombre del usuario.
     */
    public String getNombreUsuario() {                
        UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
        List<Usuario> usuarios = controladorUsuario.findUsuarioEntities();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCorreo().toLowerCase().equals(getCorreoUsuario().toLowerCase())) {
                return controladorUsuario.findUsuario(usuarios.get(i).getIdUsuario()).getNombreUsuario();                
            }
        }
        return null;
    }
    
    /**
     * Encuentra el id de un usuario a partir de su correo.
     *
     * @return
     */
    public Integer getIdUsuario() {
        UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
        List<Usuario> usuarios = controladorUsuario.findUsuarioEntities();
        if (getCorreoUsuario() != null) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getCorreo().toLowerCase().equals(getCorreoUsuario().toLowerCase())) {
                    return usuarios.get(i).getIdUsuario().intValue();
                }
            }
        }    
        return null;
    }
    
    /**
     * Muestra registrarse si no ha iniciado sesión o el nombre del usuario en
     * otro caso.
     * 
     * @return Cadena para mostrarse en el botón.
     */
    public String mostrarBoton(){
        if(!haySesion()){            
            return getNombreUsuario();
        }else{
            return "Registrarse";
        }            
    }
    
    /**
     * Si muestra registrarse entonces dirige a la página de registro, en otro caso
     * dirige a la página de editar perfil.
     * @return 
     */
    public String redireccionBoton(){
        if (!haySesion()) {            
            return "EditarPerfilIH.xhtml?idUsuario=" + getIdUsuario().toString();
        } else {
            return "/masita/RegistrarCuentaIH.xhtml";
        }
    }

    /**
     * Encuentra el nombre de un usuario a partir de su correo.
     *
     * @return
     */
    public int getEsAdministradorUsuario() {
        UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
        List<Usuario> usuarios = controladorUsuario.findUsuarioEntities();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCorreo().toLowerCase().equals(getCorreoUsuario().toLowerCase())) {
                return controladorUsuario.findUsuario(usuarios.get(i).getIdUsuario()).getEsAdministrador();
            }
        }
        return 0;
    }
    
    /**
     * Indica si un usuario que inicia sesión es administrador.
     * @return si un usuario es administrador
     */
    public boolean haySesionAdmin() {
        return !(getCorreoUsuario() == null)
                && (getEsAdministradorUsuario() == 1);
    }

}