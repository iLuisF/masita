
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.controller.UsuarioJpaController;
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.primefaces.event.RowEditEvent;

/**
 * Permite conocer todos los comentarios que se encuentran en la base de datos.
 * 
 * @author Flores González Luis
 */
@ManagedBean
@ViewScoped
public class LecturaComentarios implements Serializable{
    
    private Comentario comentario = new Comentario();   // Objeto de tipo Comentario
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private ComentarioJpaController ccomentario = new ComentarioJpaController(emf);
    private Integer idPuesto;
    private Double calificacion;    
    private final List<Comentario> comentarios = ccomentario.findComentarioEntities();
    Double calif;   // La calificación general de un puesto
    private Integer idUsuario;
    private Integer idComentario;

    /**
     * Obtiene el id de un comentario.
     * @return el id de un comentario
     */
    public Integer getIdComentario() {
        return idComentario;
    }

    /**
     * Cmbia el id de un comentario.
     * @param idComentario el nuevo id de un comentario
     */
    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }
    
    /**
     * Obtiene el id de un usuario.
     * @return el id de un usuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Cambia el id de un usuario.
     * @param idUsuario el nuevo id de un usuario
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Regresa una lista de comentarios.
     * @return una lista de comentarios
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }
    
    /**
     * Obtiene los comentarios dado el id de un puesto.
     * @return los comentarios dado el id de un puesto
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
    
    /**
     * Regresa la calificación de un puesto.
     * @return la calificación de un puesto
     */
    public String obtenerCalificacionGral() {
        
        String result = "0.0"; 
        List<Comentario> lista = getComentariosPuesto();
        calif = 0.0;
        if (lista.isEmpty())
            return result;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCalificacion() != null)
                calif += lista.get(i).getCalificacion();
        }
        calif = calif/lista.size();
        return String.format("%.2f", calif);
    }
    
    /**
     * Regresa la calificación de un puesto redondeada, para poder usar las
     * estrellas de primefaces.
     * @return la calificación de un puesto redondeada
     */
    public long redondearCalificacionGral() {
        return Math.round(calif);
    }
    
    /**
     * Deja sin modificar un comentario.
     * @param event el evento recibido
     */
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Comentario sin cambios", Long.toString(((Comentario) event.getObject()).getIdComentario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Elimina un comentario de la base de datos
     * @param event el evento recibido
     * @throws NonexistentEntityException si no se puede destruir el comentario
     */
    public void onRowCancel(RowEditEvent event) throws NonexistentEntityException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        ccomentario.destroy(((Comentario) event.getObject()).getIdComentario());
        
        FacesMessage msg = new FacesMessage("Comentario Eliminado", Long.toString(((Comentario) event.getObject()).getIdComentario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Obtiene el id de un puesto.
     * @return el id de un puesto
     */
    public Integer getIdPuesto() {
        return idPuesto;
    }

    /**
     * Modifica el id de un puesto.
     * @param idPuesto el id de un puesto a modificar
     */
    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    /**
     * Obtiene la calificacion de un puesto.
     * @return la calificacion de un puesto
     */
    public Double getCalificacion() {
        return calificacion;
    }

    /**
     * Modifica la calificacion de un puesto.
     * @param calificacion la calificacion de un puesto a modificar
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
    
    /**
     * Elimina un comentario.
     * @throws NonexistentEntityException si no se puede eliminar el comentario
     */
    public void eliminarComentario() throws NonexistentEntityException {
        ccomentario.destroy((long) this.getIdComentario());
        
        FacesMessage msg = new FacesMessage("Comentario Eliminado", Long.toString(this.getIdComentario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      
}