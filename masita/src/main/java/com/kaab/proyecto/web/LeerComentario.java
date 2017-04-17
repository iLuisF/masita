package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
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
 * @author Flores González Luis.
 */
@ManagedBean
@ViewScoped
public class LeerComentario implements Serializable {

    private Comentario comentario = new Comentario();
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private ComentarioJpaController ccomentario = new ComentarioJpaController(emf);
    private final List<Comentario> comentarios = ccomentario.findComentarioEntities();
    //Se toma un valor constante, ya que no esta el caso de uso implementado.
    private Integer idUsuario = 1;

    /**
     * Crea una lista de comentarios desde la base de datos.
     */
    public void crearListaComentarios() {
        List<Comentario> nueva = new ArrayList<>();
        for (Comentario actual : comentarios) {
            Comentario nuevo = new Comentario();
            nuevo.setIdPuesto(actual.getIdPuesto());
            nuevo.setIdUsuario(actual.getIdUsuario());
            nuevo.setContenido(actual.getContenido());
            nuevo.setFecha(actual.getFecha());
            nuevo.setCalificacion(actual.getCalificacion());
            nueva.add(nuevo);
        }
        this.comentarios.clear();
        //this.comentarios.
    }

    // Los métodos get and set, son necesarios para que los archivos .xhtml
    // puedan comunicarse con los beans.
    
    /**
     * Regresa una lista de comentarios.
     *
     * @return
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Coloca un comentario.
     *
     * @param nuevo
     */
    public void setComentario(Comentario nuevo) {
        this.comentario = nuevo;
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
     */
    public void onRowCancel(RowEditEvent event) throws NonexistentEntityException {
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");        
        if (buscarIdComentario(idUsuario) == (((Comentario) event.getObject()).getIdComentario()).intValue()) {
            ccomentario = new ComentarioJpaController(emf);
            ccomentario.destroy(((Comentario) event.getObject()).getIdComentario());

            FacesMessage msg = new FacesMessage("Comentario Eliminado", Long.toString(((Comentario) event.getObject()).getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("No puedes eliminar comentarios de otros.", Long.toString(((Comentario) event.getObject()).getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Busca el id del comentario.
     *
     * @return Identificador del usuario.
     */
    private Integer buscarIdComentario(Integer idUsuario) {
        for (int j = 0; j < comentarios.size(); j++) {
            if (comentarios.get(j).getIdUsuario().getIdUsuario().intValue()
                    == idUsuario) {
                return comentarios.get(j).getIdComentario().intValue();
            }
        }
        return -100;
    }

}
