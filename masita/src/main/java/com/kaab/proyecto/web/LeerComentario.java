
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.controller.ComentarioJpaController;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Permite conocer todos los comentarios que se encuentran en la base de datos.
 * 
 * @author Flores González Luis
 */
@ManagedBean
@ViewScoped
public class LeerComentario implements Serializable{
   
    Comentario comentario = new Comentario();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    ComentarioJpaController ccomentario = new ComentarioJpaController(emf);
    List<Comentario> comentarios = ccomentario.findComentarioEntities();
        
    /**
     * Crea una lista de comentarios desde la base de datos. 
     */
    public void crearListaComentarios(){               
        for(Comentario actual : comentarios){
            Comentario nuevo = new Comentario();
            nuevo.setIdPuesto(actual.getIdPuesto());
            nuevo.setIdUsuario(actual.getIdUsuario());
            nuevo.setContenido(actual.getContenido());
            nuevo.setFecha(actual.getFecha());
            nuevo.setCalificacion(actual.getCalificacion());
            comentarios.add(nuevo);
        }                         
    }
    
    // Los métodos get and set, son necesarios para que los archivos .xhtml
    // puedan comunicarse con los beans.
    
    /**
     * Regresa una lista de comentarios.
     * @return 
     */
    public List<Comentario> getComentarios(){
        return comentarios;
    }
    
    /**
     * Coloca un comentario.
     * @param nuevo 
     */
    public void setComentario(Comentario nuevo){
        this.comentario = nuevo;
    }
    
}
