package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.controller.PuestoJpaController;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que obtiene los puestos y los muestra en un mapa.
 * @author KAAB
 */
@ManagedBean
@ViewScoped
public class Mapas implements Serializable {

    /**
     * Modelo del mapa.
     */
    private MapModel advancedModel;
    /**
     * Marcador en el mapa.
     */
    private Marker marker;
    /**
     * Controlador para el puesto.
     */
    private PuestoJpaController lugarCtrl;
    /**
     * Nombre del puesto.
     */
    private String nombre;
    /**
     * Latitud del puesto.
     */
    private double lat;
    /**
     * Longitud del puesto.
     */
    private double lng;
    /**
     * El id del puesto de comida.
     */
    private int idPuesto;
    /**
     * Para la persistencia con la base de datos.
     */
    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MiProyectoPU");
    /**
     * Controlador para Comentario.
     */
    private final ComentarioJpaController controlador =
            new ComentarioJpaController(emf);

    /**
     * La calificación del puesto.
     */
    private String calificacion;

    /**
     * Inicia un mapa.
     */
    @PostConstruct
    public final void init() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("MiProyectoPU");
        advancedModel = new DefaultMapModel();
        lugarCtrl = new PuestoJpaController(emf);
        List<Puesto> lugares = lugarCtrl.findPuestoEntities();
        for (Puesto lugar : lugares) {
            Double latitud = Double.parseDouble(lugar.getLatitud());
            Double longitud = Double.parseDouble(lugar.getLongitud());
            String nombrePuesto = lugar.getNombre();
            System.out.println(latitud + ", " + longitud + ", "
                    + nombrePuesto);
            System.out.println("ID PUESTO: " + lugar.getIdPuesto());
            advancedModel.addOverlay(new Marker(new LatLng(latitud, longitud),
                    nombrePuesto));
        }
    }

    /**
     * Busca un Puesto por su nombre entre todos los puestos.
     * @param pNombre El nombre del puesto.
     * @param lugares la lista de todos los puestos.
     * @return el Puesto de comida.
     */
    private Puesto busca(final String pNombre, final List<Puesto> lugares) {
        Puesto resultado = null;
        for (Puesto i : lugares) {
            if (i.getNombre().equals(pNombre)) {
                resultado = i;
            }
        }
        return resultado;
    }

    /**
     * Redirige al perfil del puesto de comida.
     * @param nombrePuesto el nombre del puesto de comida.
     * @throws IOException si no encuentra el puesto de comida.
     */
    public final void redirige(final String nombrePuesto) throws IOException {
        List<Puesto> lugares = lugarCtrl.findPuestoEntities();
        Long pIdPuesto = this.busca(nombrePuesto, lugares).getIdPuesto();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("/masita"
                + "/PerfilPuestoIH.xhtml?idPuesto=" + pIdPuesto);
    }

    /**
     * Obtiene un modelo del mapa.
     * @return un modelo del mapa
     */
    public final MapModel getAdvancedModel() {
        return advancedModel;
    }

    /**
     * Establece el marcador.
     * @param event el evento.
     */
    public final void onMarkerSelect(final OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    /**
     * Obtiene un marcador en el mapa.
     * @return un marcador.
     */
    public final Marker getMarker() {
        return marker;
    }

    /**
     * Obtiene el nombre del puesto.
     * @return nombre del puesto.
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del puesto.
     * @param pNombre el nombre del puesto a modificar
     */
    public final void setNombre(final String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Obtiene la latitud en el mapa.
     * @return la latitud en el mapa
     */
    public final double getLatitud() {
        return lat;
    }

    /**
     * Modifica la latitud en el mapa.
     * @param pLat la nueva latitud en el mapa
     */
    public final void setLatitud(final double pLat) {
        this.lat = pLat;
    }

    /**
     * Obtiene la longitud en el mapa.
     * @return la longitud en el mapa
     */
    public final double getLongitud() {
        return lng;
    }

    /**
     * Modifica la longitud en el mapa.
     * @param pLng la nueva longitud en el mapa
     */
    public final void setLongitud(final double pLng) {
        this.lng = pLng;
    }

    /**
     * Obtiene el id de un puesto.
     * @return el id de un puesto
     */
    public final int getIdPuesto() {
        return idPuesto;
    }

    /**
     * Cambia el id de un puesto.
     * @param pIdPuesto el nuevo id de un puesto
     */
    public final void setIdPuesto(final int pIdPuesto) {
        this.idPuesto = pIdPuesto;
    }

    /**
     * Busca la calificaión de un puesto por su nombre.
     * @param nombrePuesto el nombre del puesto.
     * @return la calificación del puesto.
     */
    public final String buscarCalificacion(final String nombrePuesto) {
        List<Puesto> lugares = lugarCtrl.findPuestoEntities();
        Long pIdPuesto = this.busca(nombrePuesto, lugares).getIdPuesto();
        calificacion = obtenerCalificacionGral(pIdPuesto.intValue());
        return calificacion;
    }

    /**
     * Regresa la calificación de un puesto.
     *
     * @param idPuesto el id del puesto a obtener la calificación.
     * @return la calificación de un puesto
     */
    public final String obtenerCalificacionGral(Integer idPuesto) {
        Double calif = controlador.getPromedio(idPuesto);
        if (calif == null) {
            calif = 0.00;
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
        Double calif = Double.parseDouble(calificacion);
        return Math.round(calif);
    }
}
