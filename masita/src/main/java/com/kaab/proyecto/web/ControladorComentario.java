package com.kaab.proyecto.web;

import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.UsuarioJpaController;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.faces.bean.ViewScoped;
import com.kaab.proyecto.db.controller.exceptions.ErrorCrearComentario;
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.RowEditEvent;

/**
 * Controlador que permite insertar, eliminar o editar un comentario.Además
 * cada comentario debe tener contenido(al menos un caracter) para que este 
 * se pueda insertar en la base de datos.Por otro lado, el unico comentario 
 * que puede estar sin contenido, es aquel que contiene la calificación, es 
 * decir, solo un comentario por usuario tendra calificación y no tendra
 * contenido.
 *
 * @author Flores González Luis.
 * @version 2.0 - Mayo del 2017
 */
@ManagedBean
@ViewScoped
public class ControladorComentario {

    private Comentario nuevo;
    private final EntityManagerFactory emf;
    private final ComentarioJpaController controlador;
    private final List<Comentario> comentarios;
    private Integer idPuesto;        
    
    public ControladorComentario(){
        nuevo = new Comentario();//Comentario que se agregara.
        emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        controlador = new ComentarioJpaController(emf);
        comentarios = controlador.findComentarioEntities();
    }
    
    /**
     * Permite crear un comentario.
     * 
     * @throws com.kaab.proyecto.db.controller.exceptions.ErrorCrearComentario
     * @throws java.io.IOException
     */
    public void crearComentario() throws ErrorCrearComentario, IOException{
        if(nuevo.getContenido().length() == 0){
           throw new ErrorCrearComentario("Tu comentario no debe estar vació.");
        }else{
            nuevo.setIdUsuario(new Usuario((long) buscarIdUsuario()));
            nuevo.setIdPuesto(new Puesto((long) this.idPuesto));
            nuevo.setFecha(Calendar.getInstance().getTime());//Fecha actual
            nuevo.setCalificacion(null);
            controlador.create(nuevo);
        }        
        String urlPuesto = "/masita/PerfilPuestoIH.xhtml?idPuesto=" + idPuesto.toString();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect(urlPuesto);    
    }
        
    /**
     * Si el usuario no ha calificado crea una nueva calificación, en otro caso
     * edita la ultima calificación que se dio.
     * Es importante recalcar, que la calificación se guarda como un comentario 
     * pero el contenido es nulo. Esto con el motivo de poder identificar que 
     * un comentario representa una calificación.
     * 
     * @throws java.io.IOException
     */
    public void calificarPuesto() throws IOException {
        if(getIdComentarioCalificacion() == null){ //No hay calificación.
            nuevo.setIdPuesto(new Puesto((long) this.idPuesto));
            nuevo.setIdUsuario(new Usuario((long) this.buscarIdUsuario()));
            nuevo.setContenido(null);
            nuevo.setFecha(Calendar.getInstance().getTime());
            controlador.create(nuevo);
        } else {
            Integer idComentario = getIdComentarioCalificacion();
            Comentario temporal = controlador.findComentario((long) idComentario);
            temporal.setCalificacion(this.nuevo.getCalificacion());
            try {
                controlador.edit(temporal);
            } catch (Exception ex) {
                Logger.getLogger(ControladorComentario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String urlPuesto = "/masita/PerfilPuestoIH.xhtml?idPuesto=" + idPuesto.toString();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect(urlPuesto);
    }

    /**
     * Encuentra el identificador del comentario que contiene la calificación del
     * usuario. En otro caso, significa que el usuario no ha calificado al puesto.
     * Ya que el comentario con la calificación es unico, basta con encontrar 
     * el primero.
     * 
     * @return Identificador del comentario que contiene la calificación.
     */
    private Integer getIdComentarioCalificacion(){
        LeerComentario temporal = new LeerComentario();
        List<Comentario> comentariosTemporal = temporal.getComentarios();
        for (int j = 0; j < comentariosTemporal.size(); j++) {
            if (comentariosTemporal.get(j).getIdUsuario().getIdUsuario().intValue()
                    == buscarIdUsuario()
                    && comentariosTemporal.get(j).getIdPuesto().getIdPuesto().intValue()
                    == idPuesto
                    && comentariosTemporal.get(j).getCalificacion() != null) {
                return comentariosTemporal.get(j).getIdComentario().intValue();
            }
        }
        return null;
    }
            
    /**
     * True si un usuario inicio sesión en el sistema correctamente.
     *
     * @return
     */
    public boolean hayUsuario() {
        return getCorreoUsuario() != null;
    }
        
    /**
     * El correo del usuario que inicio sesión en el sistema.
     *
     * @return El correo del usuario que inicio sesión.
     */
    public String getCorreoUsuario() {
        HttpServletRequest httpServletRequest;
        FacesContext faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        Usuario sesion = (Usuario) httpServletRequest.getSession().getAttribute("sessionUsuario");
        if (sesion == null) {//No se inicio sesión.
            return null;
        } else {
            return sesion.getCorreo();
        }
    }

    /**
     * Encuentra el id de un usuario a partir de su correo.
     *
     * @return
     */
    private Integer buscarIdUsuario() {
        if (getCorreoUsuario() != null) {
            UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
            List<Usuario> usuarios = controladorUsuario.findUsuarioEntities();
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getCorreo().toLowerCase().equals(getCorreoUsuario().toLowerCase())) {
                    return usuarios.get(i).getIdUsuario().intValue();
                }
            }
        }
        return null;
    }
    
        /**
     * Deja sin modificar un comentario.
     *
     * @param event
     */
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Comentario sin cambios", Long.toString(((Comentario) event.getObject()).getIdComentario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Elimina un comentario de la base de datos
     *
     * @param event
     * @throws NonexistentEntityException
     * @throws java.io.IOException
     */
    public void onRowCancel(RowEditEvent event) throws NonexistentEntityException, IOException {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");        
        if (Objects.equals(buscarIdUsuario(), getIdUsuarioComentario((((Comentario) event.getObject()).getIdComentario()).intValue()))) {
            //ccomentario = new ComentarioJpaController(emf);
            controlador.destroy(((Comentario) event.getObject()).getIdComentario());

            FacesMessage msg = new FacesMessage("Comentario Eliminado", Long.toString(((Comentario) event.getObject()).getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/masita/PerfilPuestoIH.xhtml?idPuesto=" + idPuesto);
        } else if (esAdministrador(buscarIdUsuario())) {
            //ccomentario = new ComentarioJpaController(emf);
            controlador.destroy(((Comentario) event.getObject()).getIdComentario());

            FacesMessage msg = new FacesMessage("Comentario Eliminado", Long.toString(((Comentario) event.getObject()).getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/masita/PerfilPuestoIH.xhtml?idPuesto=" + idPuesto);
        } else {
            FacesMessage msg = new FacesMessage("No puedes eliminar comentarios de otros.", Long.toString(((Comentario) event.getObject()).getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    /**
     * Si un administrador inicio sesión, entonces regresa true.
     * 
     * @param idUsuario Usuario que inicio sesión.
     * @return 
     */
    public boolean esAdministrador(Integer idUsuario) {
        UsuarioJpaController controladorUsuario = new UsuarioJpaController(emf);
        if(idUsuario == null){
            return false;
        }
        Usuario u = controladorUsuario.findUsuario((long) idUsuario);
        if (u.getEsAdministrador() == 1) {
            return true;
        }
        return false;
    }
    
    /**
     * Muestra el botón para eliminar/editar un comentario en el listado de 
     * comentarios. Si es un usuario solo se mostrara en sus propios comentarios
     * pero si es administrador se mostrara la opción en todos los comentarios.
     * 
     * 
     * @param idUsuario
     * @return 
     */
    public boolean mostrarEdicion(Integer idUsuario){
        if(Objects.equals(buscarIdUsuario(), idUsuario) || esAdministrador(buscarIdUsuario())){
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene el identificador de algun usuario a partir de un comentario.
     *
     * @return Identificador del usuario.
     */
    private Integer getIdUsuarioComentario(Integer idComentario) {
        HashMap<Integer, Integer> mapa = new HashMap();
        for (int i = 0; i < comentarios.size(); i++) {
            mapa.put(comentarios.get(i).getIdComentario().intValue(),
                    comentarios.get(i).getIdUsuario().getIdUsuario().intValue());
        }
        return mapa.get(idComentario);
    }

    /**
     * Obtiene los comentarios que se mostraran en perfil del puesto actual.
     * @return Lista de comentarios en orden ascendente.
     */
    public List<Comentario> getComentariosPuesto() {
        List<Comentario> temporal = new ArrayList<Comentario>();
        if (idPuesto != null) {
            for (int i = 0; i < comentarios.size(); i++) {
                if (comentarios.get(i).getIdPuesto().getIdPuesto().intValue() == this.idPuesto) {
                    temporal.add(comentarios.get(i));
                }
            }

        }
        return temporal;
    }
    
    // Los métodos get and set, son necesarios para que los archivos .xhtml
    // puedan comunicarse con los beans.    
    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Comentario getNuevo() {
        return nuevo;
    }

    public void setNuevo(Comentario comentario) {
        this.nuevo = comentario;
    }    
}