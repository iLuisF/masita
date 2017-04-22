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
 * Bean manejado que se utiliza para el manejo de Inicio de Sesi�n en
 * la aplicaci�n web.
 *
 * @author miguel
 */
@ManagedBean 
@RequestScoped // S�lo est� disponible a partir de peticiones al bean
public class InicioSesion {

    private String correo;
    private String contrasenia;
    private HttpServletRequest httpServletRequest; // Obtiene informaci�n de todas las peticiones de usuario.
    private  FacesContext faceContext; // Obtiene informaci�n de la aplicaci�n
    private FacesMessage message;
    private Usuario usuario = new Usuario();//Este es el bueno.
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private final UsuarioJpaController controlador = new UsuarioJpaController(emf);

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
     * Regresa la contrase�a del usuario.
     *
     * @return La contrase�a del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contrase�a del usuario.
     *
     * @param contrasenia La contrase�a del usuario a establecer.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * M�todo encargado de validar el inicio de sesi�n.
     *
     * @return El nombre de la vista que va a responder.
     */
    public String inicioSesion() {
        
        if (usuario.getContrasenia().equals(encontrarContrasenia())) {
            httpServletRequest.getSession().setAttribute("sessionUsuario", usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
            faceContext.addMessage(null, message);            
            return "PrincipalIH";//¿Por esto sale el error?Perfil
        }
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
        faceContext.addMessage(null, message);
        return "PrincipalIH";//¿Por esto sale el error?inicio sesion
    }

    /**
     * Encuentra la contrase�a desde el correo electronico.
     *
     * @return
     */
    public String encontrarContrasenia() {
        List<Usuario> temporal = controlador.findUsuarioEntities();
        for (int i = 0; i < temporal.size(); i++) {
            if (temporal.get(i).getCorreo().toLowerCase().equals(this.usuario.getCorreo().toLowerCase())) {
                return temporal.get(i).getContrasenia();
            }
        }
        return null;
    }
    
    
    //Código para cambiar contenido de los botones registrarse por nombre del
    //usuario e iniciar sesión por cerrar sesión.
    
        /**
     * 
     *
     * @return El correo del usuario que inicio sesión.
     */
    public String getCorreoUsuario() {
        //HttpServletRequest httpServletRequest;
        //FacesContext faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        Usuario sesion = (Usuario) httpServletRequest.getSession().getAttribute("sessionUsuario");
        if (sesion == null) {//No se inicio sesión.
            return null;
        } else {
            return sesion.getCorreo();
        }
    }
    
    public boolean haySesion(){
     return getCorreoUsuario() == null;
    }    
    
    /**
     * Encuentra el id de un usuario a partir de su correo.
     * @return 
     */
    public String getNombreUsuario(){                
        UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
        List<Usuario> usuarios = controladorUsuario.findUsuarioEntities();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getCorreo().toLowerCase().equals(getCorreoUsuario().toLowerCase())){
                return controladorUsuario.findUsuario(usuarios.get(i).getIdUsuario()).getNombreUsuario();                
            }
        }
        return null;
    }
    
    /**
     * Muestra registrarse o el nombre del usuario.
     * @return 
     */
    public String mostrarBoton(){
        if(!haySesion()){
            return getNombreUsuario();
        }else{
            return "Registrarse";
        }
            
    }
        
}