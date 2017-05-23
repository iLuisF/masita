package com.kaab.proyecto.web;

import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.Usuario;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Persistence;
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
import org.primefaces.event.RowEditEvent;

/**
 * Controlador que permite insertar, eliminar o editar un comentario.Además cada
 * comentario debe tener contenido(al menos un caracter) para que este se pueda
 * insertar en la base de datos.Por otro lado, el unico comentario que puede
 * estar sin contenido, es aquel que contiene la calificación, es decir, solo un
 * comentario por usuario tendra calificación y no tendra contenido.
 *
 * @author Flores González Luis.
 * @version 2.0 - Mayo del 2017
 */
@ManagedBean
@ViewScoped
public class ControladorComentario {

    /**
     * Comentario que se insertara en la base de datos.
     */
    private Comentario nuevo;
    /**
     * Interfaz para hacer consultas en la base de datos.
     */
    private final EntityManagerFactory emf;
    /**
     * Consultas en la base de dato de la entidad comentario.
     */
    private final ComentarioJpaController controlador;
    /**
     * Comentarios que se encuentran actualmente en la base de datos.
     */
    private final List<Comentario> comentarios;
    /**
     * Perfil del puesto actual que esta vizualizando el usuario/visitante.
     */
    private Integer idPuesto;
    /**
     * Usuario que inicio sesión.
     */
    private final Integer idUsuario;

    /**
     * Inicializa el controlador para poder hacer las consultas en la base de
     * datos además de obtener los identificadores tanto del puesto como del
     * usuario.
     */
    public ControladorComentario() {
        nuevo = new Comentario();
        emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        controlador = new ComentarioJpaController(emf);
        comentarios = controlador.findComentarioEntities();
        idUsuario = new InicioSesion().getIdUsuario();
    }

    /**
     * Permite crear un comentario.
     *
     * @throws com.kaab.proyecto.db.controller.exceptions.ErrorCrearComentario
     * El comentario no tiene contenido, es decir, esta vació.
     * @throws java.io.IOException No se pudo recuperar la información.
     */
    public final void crearComentario() throws ErrorCrearComentario,
            IOException {
        if (nuevo.getContenido().length() == 0) {
            throw new ErrorCrearComentario("Tu comentario no debe ser vació.");
        } else {
            nuevo.setIdUsuario(new Usuario((long) this.idUsuario));
            nuevo.setIdPuesto(new Puesto((long) this.idPuesto));
            //Fecha en la que se inserta en la base de datos el comentario.
            nuevo.setFecha(Calendar.getInstance().getTime());
            nuevo.setCalificacion(null);
            controlador.create(nuevo);
        }
        String urlPuesto = "/masita/PerfilPuestoIH.xhtml?idPuesto="
                + idPuesto.toString();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect(urlPuesto);
    }

    /**
     * Si el usuario no ha calificado crea una nueva calificación, en otro caso
     * edita la ultima calificación que se dio. Es importante recalcar, que la
     * calificación se guarda como un comentario pero el contenido es nulo. Esto
     * con el motivo de poder identificar que un comentario representa una
     * calificación.
     *
     * @throws java.io.IOException No se pudo recuperar la información.
     * @throws Exception No existe la entidad comentario.
     */
    public final void calificarPuesto() throws IOException,
            Exception {
        if (getIdComentarioCalificacion() == null) { //No hay calificación.
            nuevo.setIdPuesto(new Puesto((long) this.idPuesto));
            nuevo.setIdUsuario(new Usuario((long) this.idUsuario));
            nuevo.setContenido(null);
            nuevo.setFecha(Calendar.getInstance().getTime());
            controlador.create(nuevo);
        } else {
            Integer idComentario = getIdComentarioCalificacion();
            Comentario tmp = controlador.findComentario((long) idComentario);
            tmp.setCalificacion(this.nuevo.getCalificacion());
            controlador.edit(tmp);
        }
        String urlPuesto = "/masita/PerfilPuestoIH.xhtml?idPuesto="
                + idPuesto.toString();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect(urlPuesto);
    }

    /**
     * Encuentra el identificador del comentario que contiene la calificación
     * del usuario. En otro caso, significa que el usuario no ha calificado al
     * puesto. Ya que el comentario con la calificación es unico, basta con
     * encontrar el primero.
     *
     * @return Identificador del comentario que contiene la calificación.
     */
    private Integer getIdComentarioCalificacion() {
        for (int j = 0; j < comentarios.size(); j++) {
            if (comentarios.get(j).getIdUsuario().getIdUsuario().intValue()
                    == this.idUsuario
                    && comentarios.get(j).getIdPuesto().getIdPuesto().intValue()
                    == idPuesto
                    && comentarios.get(j).getCalificacion() != null) {
                return comentarios.get(j).getIdComentario().intValue();
            }
        }
        return null;
    }

    /**
     * Edita un comentario, pero solo su contenido.
     *
     * @param event Comentario que sera editado.
     * @throws java.lang.Exception en caso de no poder hacer el cast.
     */
    public final void onRowEdit(final RowEditEvent event) throws Exception {
        String mensaje;
        Comentario tmp = controlador.findComentario(((Comentario)
                event.getObject()).getIdComentario());
        boolean esEditado = !tmp.getContenido().equals(((Comentario)
                event.getObject()).getContenido());
        if (esEditado) {
            mensaje = "Comentario editado exitosamente.";
        } else {
            mensaje = "Comentario sin cambios.";
        }
        tmp.setContenido(((Comentario) event.getObject()).getContenido());
        controlador.edit(tmp);
        FacesMessage msg = new FacesMessage(mensaje, Long.toString(((Comentario)
                event.getObject()).getIdComentario()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Elimina un comentario de la base de datos.
     *
     * @param event Comentario que se eliminara de la base de datos.
     * @throws NonexistentEntityException No existe la entidad.
     * @throws java.io.IOException No se pudo recuperar la información.
     */
    public final void onRowCancel(final RowEditEvent event) throws
            NonexistentEntityException, IOException {
        if (Objects.equals(this.idUsuario, getIdUsuarioComentario((((Comentario)
                event.getObject()).getIdComentario()).intValue()))) {
            controlador.destroy(((Comentario) event.getObject()).
                    getIdComentario());
            FacesMessage msg = new FacesMessage("Comentario Eliminado",
                    Long.toString(((Comentario) event.getObject()).
                            getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(
                    "/masita/PerfilPuestoIH.xhtml?idPuesto=" + idPuesto);
        } else if (new InicioSesion().haySesionAdmin()) {
            controlador.destroy(((Comentario) event.getObject()).
                    getIdComentario());
            FacesMessage msg = new FacesMessage("Comentario Eliminado",
                    Long.toString(((Comentario) event.getObject()).
                            getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(
                    "/masita/PerfilPuestoIH.xhtml?idPuesto=" + idPuesto);
        } else {
            FacesMessage msg = new FacesMessage("Solo puedes eliminar"
                    + "comentarios tuyos.", Long.toString(((Comentario)
                            event.getObject()).getIdComentario()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Muestra el botón para eliminar/editar un comentario en el listado de
     * comentarios. Si es un usuario solo se mostrara en sus propios comentarios
     * pero si es administrador se mostrara la opción en todos los comentarios.
     *
     * @param idUsuarioSesion Usuario que inicio sesión.
     * @return True si inicio sesión y se mostrara el botón de edición, false en
     * otro caso y no se muestra el botón.
     */
    public final boolean mostrarEdicion(final Integer idUsuarioSesion) {
        return Objects.equals(this.idUsuario, idUsuarioSesion)
                || (new InicioSesion().haySesionAdmin());
    }

    /**
     * Obtiene el identificador de algun usuario a partir de un comentario.
     *
     * @param idComentario Identificador
     * @return Identificador del usuario.
     */
    private Integer getIdUsuarioComentario(final Integer idComentario) {
        HashMap<Integer, Integer> mapa = new HashMap();
        for (int i = 0; i < comentarios.size(); i++) {
            mapa.put(comentarios.get(i).getIdComentario().intValue(),
                    comentarios.get(i).getIdUsuario().getIdUsuario().
                            intValue());
        }
        return mapa.get(idComentario);
    }

    /**
     * Obtiene los comentarios que se mostraran en perfil del puesto actual.
     * Pero no la calificación.
     *
     * @return Lista de comentarios en orden ascendente.
     */
    public final List<Comentario> getComentariosPuesto() {
        List<Comentario> temporal = new ArrayList<Comentario>();
        if (idPuesto != null) {
            for (int i = 0; i < comentarios.size(); i++) {
                if (comentarios.get(i).getIdPuesto().getIdPuesto().intValue()
                        == this.idPuesto
                        && comentarios.get(i).getContenido() != null) {
                    temporal.add(comentarios.get(i));
                }
            }

        }
        return temporal;
    }

    /**
     * Regresa la calificación que dio al puesto actual, si no califico regresa
     * null.
     *
     * @return Calificación del puesto actual.
     */
    public final Integer getCalificacion() {
        if (idUsuario != null) {
            for (int i = 0; i < comentarios.size(); i++) {
                if (comentarios.get(i).getIdPuesto().getIdPuesto().intValue()
                        == this.idPuesto
                        && comentarios.get(i).getIdUsuario().getIdUsuario().
                                intValue() == idUsuario
                        && comentarios.get(i).getContenido() == null) {
                    return comentarios.get(i).getCalificacion();
                }
            }
        }
        return null;
    }

    /**
     * True si el usuario actual ya califico, false en otro caso.
     *
     * @return True si hay un comentario con calificación del usuario actual.
     */
    public final boolean hayCalificacion() {
        return getCalificacion() != null;
    }

    /**
     * Regresa la calificación de un puesto.
     *
     * @return la calificación de un puesto
     */
    public final String obtenerCalificacionGral() {
        Double calif;
        String result = "0.0";
        List<Comentario> lista = comentarios;
        calif = 0.0;
        Integer numCalif = 0;
        if (lista.isEmpty()) {
            return result;
        }
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCalificacion() != null
                    && lista.get(i).getIdPuesto().getIdPuesto().intValue()
                    == idPuesto
                    && comentarios.get(i).getContenido() == null) {
                calif += lista.get(i).getCalificacion();
            }
            if (comentarios.get(i).getIdPuesto().getIdPuesto().intValue()
                    == this.idPuesto
                    && comentarios.get(i).getContenido() == null) {
                numCalif++;
            }
        }
        if (numCalif != 0) {
            calif = calif / numCalif;
        } else {
            calif = 0.0;
        }
        return String.format("%.2f", calif);
    }

    /**
     * Regresa la calificación de un puesto redondeada, para poder usar las
     * estrellas de primefaces.
     *
     * @return la calificación de un puesto redondeada
     */
    public final long redondearCalificacionGral() {
        Double calif = Double.parseDouble(obtenerCalificacionGral());
        return Math.round(calif);
    }

    /**
     * Regresa el identificador del puesto actual.
     *
     * @return Identificador del puesto actual.
     */
    public final Integer getIdPuesto() {
        return idPuesto;
    }

    /**
     * Asigna el identificador del puesto actual desde la vista.
     *
     * @param idPuestoIH identificador del puesto que se encuentra en la url.
     */
    public final void setIdPuesto(final Integer idPuestoIH) {
        this.idPuesto = idPuestoIH;
    }

    /**
     * Regresa el comentario actual.
     *
     * @return comentario que se ingreso desde la vista.
     */
    public final Comentario getNuevo() {
        return nuevo;
    }

    /**
     * Asigna el nuevo comentario desde la vista.
     *
     * @param comentario Comentario que se asigna desde la vista.
     */
    public final void setNuevo(final Comentario comentario) {
        this.nuevo = comentario;
    }
}
