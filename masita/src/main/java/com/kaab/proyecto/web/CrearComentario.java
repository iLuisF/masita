
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.Usuario;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private Comentario comentario = new Comentario();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private ComentarioJpaController ccomentario = new ComentarioJpaController(emf);
    @Temporal(TemporalType.DATE)
    private final Date fecha = Calendar.getInstance().getTime();    
    private Integer calificacion;
    
    // Los métodos get and set, son necesarios para que los archivos .xhtml
    // puedan comunicarse con los beans.    
    
    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }    

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
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
    
    public void nuevaCalificacion(){        
        //EntityManager entityManager = emf.createEntityManager();
        //String seleccion = "SELECT a.idComentario FROM Comentario a WHERE a.idUsuario = 1";
        //Query query = entityManager.createQuery(seleccion);
        //List<String> list = query.getResultList();//idComentario del usuario actual.        
        //Integer idUsuario = Integer.parseInt(list.get(0));
        if(ccomentario.findComentario((long) 36).getIdUsuario() == null){//Cambiar
            agregarCalificacion();
        }else{
            agregarCalificacion2();
        }        
    }
    
    /**
     * Agrega solo la calificación, este solo se usa en caso de que el usuario
     * no haya comentado antes. Es decir, solo se mostrara la calificación. 
     * Si se usa cuando el usuario creo un comentario, entonces borrara todo y 
     * solo se mostrara la calificación.
     */
    private void agregarCalificacion(){
        comentario.setIdPuesto(new Puesto((long) 1)); //Cambiar
        comentario.setIdUsuario(new Usuario((long) 1)); //Cambiar
        comentario.setContenido(null);
        comentario.setFecha(fecha);
        comentario.setCalificacion(getCalificacion());
        ccomentario.create(comentario);
    }
    
    /**
     * Agrega la calificación cuando el usuario ya creo un contenido como
     * comentario, es decir, solo se agregara la calificación sin borrar los otros
     * datos.
     * */
    private void agregarCalificacion2(){    
        comentario = ccomentario.findComentario((long)36);
        comentario.setCalificacion(getCalificacion());
        try {
            ccomentario.edit(comentario);
        } catch (Exception ex) {
            Logger.getLogger(CrearComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
}