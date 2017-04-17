package com.kaab.proyecto.web;

import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.Usuario;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.faces.bean.ViewScoped;
import com.kaab.proyecto.db.controller.exceptions.ErrorCrearComentario;

/**
 * Permite insertar un comentario en la base de datos. Además la
 * calificación, ya que esta pertenece al comentario.
 *
 * @author Flores González Luis.
 * @version 1.0 - Abril del 2017
 */
@ManagedBean
@ViewScoped
public class CrearComentario {

    private Comentario nuevo = new Comentario();//Comentario que se agregara.
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private final ComentarioJpaController controlador = new ComentarioJpaController(emf);
    private Integer idPuesto;
    //Se toma un valor constante, ya que no esta el caso de uso implementado.
    private Integer idUsuario = 1; 

    /**
     * Crea un comentario en caso de que no haya uno, si hay comentario solo con
     * calificación entonces solo agrega el contenido al comentario.
     * 
     * @throws com.kaab.proyecto.db.controller.exceptions.ErrorCrearComentario Si
     * ya hay contenido en el comentario sin importar si tiene calificación o no.
     */
    public void crearComentario() throws ErrorCrearComentario {
        nuevo.setIdUsuario(new Usuario((long) this.idUsuario));
        hayComentario();
        if (hayCalificacion()) {//Entonces editamos comentario.
            Integer idComentario = encontrarIdComentario();
            Comentario temporal = controlador.findComentario((long) idComentario);
            temporal.setContenido(this.nuevo.getContenido());
            try {
                controlador.edit(temporal);
            } catch (Exception ex) {
                Logger.getLogger(CrearComentario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {//Creamos un comentario.
            nuevo.setIdPuesto(new Puesto((long) this.idPuesto));
            nuevo.setFecha(Calendar.getInstance().getTime());//Fecha actual
            nuevo.setCalificacion(null);
            controlador.create(nuevo);
        }
    }

    /**
     * Si un usuario ya tiene contenido en el comentario lanza una excepción.
     *
     * @throws com.kaab.proyecto.db.controller.exceptions.ErrorCrearComentario
     */
    private void hayComentario() throws ErrorCrearComentario {
        if (hayContenido()) {
            throw new ErrorCrearComentario("Solo puedes crear un comentario."
                    + "Debes eliminar tu comentario actual y volver a crear"
                    + " uno.");
        }
    }

    /**
     * Si el usuario no ha comentado entonces solo crea la calificación sin
     * contenido en el comentario. Si el usuario ya comento entonces, entonces
     * edita la calificación a la nueva que se da.
     */
    public void calificarPuesto() {
        if (!hayContenido()) {//No ha comentado
            nuevo.setIdPuesto(new Puesto((long) this.idPuesto));
            nuevo.setIdUsuario(new Usuario((long) this.idUsuario)); 
            nuevo.setContenido(null);
            nuevo.setFecha(Calendar.getInstance().getTime());
            controlador.create(nuevo);
        } else {//Ya comento.            
            Integer idComentario = encontrarIdComentario();
            Comentario temporal = controlador.findComentario((long) idComentario);
            temporal.setCalificacion(this.nuevo.getCalificacion());
            try {
                controlador.edit(temporal);
            } catch (Exception ex) {
                Logger.getLogger(CrearComentario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Encuentra el id del comentario que hizo el usuario anteriormente en el
     * puesto actual.
     *
     * @return id del comentario.
     */
    private Integer encontrarIdComentario() {
        LeerComentario temporal = new LeerComentario();
        List<Comentario> coments = temporal.getComentarios();
        for (int j = 0; j < coments.size(); j++) {
            if (coments.get(j).getIdUsuario().getIdUsuario().intValue()
                    == this.idUsuario
                    && coments.get(j).getIdPuesto().getIdPuesto().intValue()
                    == this.idPuesto) {
                return coments.get(j).getIdComentario().intValue();
            }
        }
        return -100;
    }

    /**
     * Si el usuario que quiere comentar ya tiene al menos un contenido en el 
     * comentario entonces regresa true, en otro caso false.
     *
     * @return
     */
    private boolean hayContenido() {
        LeerComentario temporal = new LeerComentario();
        List<Comentario> coments = temporal.getComentarios();
        for (int j = 0; j < coments.size(); j++) {
            if (coments.get(j).getIdUsuario().getIdUsuario().intValue()
                    == this.idUsuario
                    && coments.get(j).getIdPuesto().getIdPuesto().intValue()
                    == this.idPuesto
                    && coments.get(j).getContenido() != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determina si el usuario ya tiene una calificación en su comentario.
     * @return True si hay calificación, false en otro caso.
     */
    private boolean hayCalificacion() {
        LeerComentario temporal = new LeerComentario();
        List<Comentario> coments = temporal.getComentarios();
        for (int j = 0; j < coments.size(); j++) {
            if (coments.get(j).getIdUsuario().getIdUsuario().intValue()
                    == this.idUsuario
                    && coments.get(j).getIdPuesto().getIdPuesto().intValue()
                    == this.idPuesto
                    && coments.get(j).getCalificacion() != null) {
                return true;
            }
        }
        return false;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
