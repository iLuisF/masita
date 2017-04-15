
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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.RowEditEvent;

/**
 * Permite conocer todos los comentarios que se encuentran en la base de datos.
 * 
 * @author Flores González Luis
 */
@ManagedBean
@ViewScoped
public class LeerComentario implements Serializable{
    
    private Long idUsuario;
    private String contenido;
    private Integer calificacion;
    private Date fecha;
    private Long idComentario;
   
    private ComentarioJpaController ccomentario;
    
    private List<Comentario> listaC;
        
    /**
     * Crea una lista de comentarios desde la base de datos. 
     */
    public List<Comentario> crearListaComentarios(){ 
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
    
}