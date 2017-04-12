
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.Usuario;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.persistence.Persistence;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.faces.bean.ViewScoped;

/**
 * Permite insertar un comentario en la base de datos.
 * 
 * @author Flores González Luis.
 * @version 1.0 - Abril del 2017
 */
@ManagedBean
@ViewScoped
public class CrearComentario {

    Comentario comentario = new Comentario();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    ComentarioJpaController ccomentario = new ComentarioJpaController(emf);
    @Temporal(TemporalType.DATE)
    private final Date fecha = Calendar.getInstance().getTime();
    
    // Los métodos get and set, son necesarios para que los archivos .xhtml
    // puedan comunicarse con los beans.    
    
    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }    
    
    /**
     * Agrega un comentario a la base de datos, desde el bean.
     * Por el momento no se tiene el id de cada puesto ni el id de cada usuario,
     * falta la implementación correspondiente a esto.
     */
    public void agregarComentario(){
        comentario.setIdPuesto(new Puesto((long) 1));
        comentario.setIdUsuario(new Usuario((long) 1));
        comentario.setContenido(comentario.getContenido());
        comentario.setFecha(fecha);
        comentario.setCalificacion(null);
        ccomentario.create(comentario);
    }
    
}