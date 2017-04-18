
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.ComentarioJpaController;
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
public class LeerComentario implements Serializable{
    
    private Long idPuesto;
    private ComentarioJpaController ccomentario;
    private List<Comentario> listaC;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private List<Comentario> comentarios;
    private Double calificacion;
        
    /**
     * Crea una lista de comentarios desde la base de datos. 
     */
    /*public List<Comentario> crearListaComentarios(){ 
        listaC = new ArrayList<Comentario>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        ccomentario = new ComentarioJpaController(emf);
        List<Comentario> comentarios = ccomentario.findComentarioEntities();
        
        for(Comentario actual : comentarios){
            
            actual.setIdComentario(actual.getIdComentario());
            actual.setIdPuesto(actual.getIdPuesto());
            actual.setIdUsuario(actual.getIdUsuario());
            actual.setContenido(actual.getContenido());
            actual.setFecha(actual.getFecha());
            actual.setCalificacion(actual.getCalificacion());
            listaC.add(actual);
        }               
        return listaC;
    }*/
    
    /**
     * Crea una lista de comentarios.
     */
    public void crearListaComentarios(){ 
        listaC = new ArrayList<Comentario>();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comentario> consultaComentario = em.createNamedQuery("seleccionarComentariosPuesto", Comentario.class);
        consultaComentario.setParameter("idPuesto", this.idPuesto);
        
        //ccomentario = new ComentarioJpaController(emf);
        comentarios = consultaComentario.getResultList();
        
        for(Comentario actual : comentarios){
            Comentario nuevo = new Comentario();
            nuevo.setIdComentario(actual.getIdComentario());
            nuevo.setIdPuesto(actual.getIdPuesto());
            nuevo.setIdUsuario(actual.getIdUsuario());
            nuevo.setContenido(actual.getContenido());
            nuevo.setFecha(actual.getFecha());
            nuevo.setCalificacion(actual.getCalificacion());
            listaC.add(nuevo);
        }               
        //return listaC;
    }
    
    /**
     * Deja sin modificar un comentario.
     * @param event 
     */
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Comentario sin cambios", Long.toString(((Comentario) event.getObject()).getIdComentario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Elimina un comentario de la base de datos
     * @param event
     * @throws NonexistentEntityException 
     */
    public void onRowCancel(RowEditEvent event) throws NonexistentEntityException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        ccomentario = new ComentarioJpaController(emf);
        ccomentario.destroy(((Comentario) event.getObject()).getIdComentario());
        
        FacesMessage msg = new FacesMessage("Comentario Eliminado", Long.toString(((Comentario) event.getObject()).getIdComentario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Obtiene el id de un puesto.
     * @return el id de un puesto
     */
    public Long getIdPuesto() {
        return idPuesto;
    }

    /**
     * Modifica el id de un puesto.
     * @param idPuesto el id de un puesto a modificar
     */
    public void setIdPuesto(Long idPuesto) {
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
    
    /* Obtener la calificacion de un puesto*/
    /*public Double getCalificacionPuesto(){ 
        
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Comentario> consultaCalificacion = em.createNamedQuery("calificacionPuesto", Comentario.class);
        consultaCalificacion.setParameter("idPuesto", this.idPuesto);
        
        //ccomentario = new ComentarioJpaController(emf);
        calificacion = (Double) consultaCalificacion.getSingleResult();
        
        for(Comentario actual : comentarios){
            Comentario nuevo = new Comentario();
            nuevo.setIdComentario(actual.getIdComentario());
            nuevo.setIdPuesto(actual.getIdPuesto());
            nuevo.setIdUsuario(actual.getIdUsuario());
            nuevo.setContenido(actual.getContenido());
            nuevo.setFecha(actual.getFecha());
            nuevo.setCalificacion(actual.getCalificacion());
            listaC.add(nuevo);
        }               
        //return listaC;
    }*/
    
}
