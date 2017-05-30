/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.ServicioAdicional;
import com.kaab.proyecto.db.TipoComida;
import com.kaab.proyecto.db.controller.PuestoJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Muestra la información de un puesto.
 * @author KAAB
 */
@ManagedBean
@ViewScoped
public class InformacionPuesto implements Serializable {

    /**
     * Entity Manager Factory para generar persistencia.
     */
    private final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("MiProyectoPU");
    /**
     * Entity Manager.
     */
    private final EntityManager em = emf.createEntityManager();
    /**
     * Controlador del puesto de comida.
     */
    private final PuestoJpaController puesto = new PuestoJpaController(emf);
    /**
     * Lista de los puestos.
     */
    private List<Puesto> p;
    /**
     * Nombre del puesto.
     */
    private String nombre;
    /**
     * Id del puesto.
     */
    private Long idPuesto;
    /**
     * Calificación del puesto.
     */
    private Double calificacion;
    /**
     * Latitud del puesto.
     */
    private String latitud;
    /**
     * Longitud del puesto.
     */
    private String longitud;
    /**
     * Modelo avanzado para el mapa.
     */
    private MapModel advancedModel;
    /**
     * Marcador en el mapa.
     */
    private Marker marker;
    /**
     * Servicios adicionales del puesto.
     */
    private String[] servicios;
    /**
     * Tipo de comida del puesto.
     */
    private String[] tipoComida;

    /**
     * Regresa una lista con los puestos.
     * @return lista con los puestos
     */
    public final List<Puesto> getListaPuestos() {
        p = new ArrayList<Puesto>();
        List<Puesto> lugares = puesto.findPuestoEntities();
        for (Puesto lugar : lugares) {
            lugar.setNombre(lugar.getNombre());
            lugar.setIdPuesto(lugar.getIdPuesto());
            lugar.setLatitud(lugar.getLatitud());
            lugar.setLongitud(lugar.getLongitud());
            lugar.setTipoComidaCollection(lugar.getTipoComidaCollection());
            lugar.setServicioAdicionalLista(lugar.getServicioAdicionalLista());
            lugar.setTipoComidaCollection(lugar.getTipoComidaCollection());
            p.add(lugar);
        }
        return p;
    }

    /**
     * Regresa el id de un puesto.
     * @return el id de un puesto
     */
    public final Long getIdPuesto() {
        return idPuesto;
    }

    /**
     * Modifica el id de un puesto.
     * @param pIdPuesto el id de un puesto a modificar
     */
    public final void setIdPuesto(final Long pIdPuesto) {
        this.idPuesto = pIdPuesto;
    }

    /**
     * Obtiene la informacion de puesto dado su id.
     * @return la informacion de puesto dado su id
     */
    public final Puesto getInformacionPuesto() {
        Puesto p1 = puesto.findPuesto(this.idPuesto);
        int i = 0;
        this.setTipoComida(new String[p1.getTipoComidaCollection().size()]);
        this.setServicios(new String[p1.getServicioAdicionalLista().size()]);
        for (ServicioAdicional x : p1.getServicioAdicionalLista()) {
            this.getServicios()[i] = x.getNombre();
            i++;
        }
        i = 0;
        for (TipoComida x : p1.getTipoComidaCollection()) {
            this.getTipoComida()[i] = x.getNombre();
            i++;
        }
        advancedModel = new DefaultMapModel();
        LatLng coord1 = new LatLng(Double.parseDouble(p1.getLatitud()),
                Double.parseDouble(p1.getLongitud()));
        advancedModel.addOverlay(new Marker(coord1, p1.getNombre(),
                "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
        return p1;
    }

    /**
     * Obtiene la latitud de un puesto.
     * @return la latitud de un puesto
     */
    public final String getLatitud() {
        return latitud;
    }

    /**
     * Modifica la latitud de un puesto.
     * @param pLatitud la latitud de un puesto a modificar
     */
    public final void setLatitud(final String pLatitud) {
        this.latitud = pLatitud;
    }

    /**
     * Obtiene la longitud de un puesto.
     * @return la longitud de un puesto
     */
    public final String getLongitud() {
        return longitud;
    }

    /**
     * Modifica la longitud de un puesto.
     * @param pLongitud la longitud de un puesto a modificar
     */
    public final void setLongitud(final String pLongitud) {
        this.longitud = pLongitud;
    }

    /**
     * Regresa el modelo para el mapa.
     * @return modelo para el mapa
     */
    public final MapModel getAdvancedModel() {
        return advancedModel;
    }

    /**
     * Modifica el modelo para el mapa.
     * @param pAdvancedModel modelo para el mapa a modificar
     */
    public final void setAdvancedModel(final MapModel pAdvancedModel) {
        this.advancedModel = pAdvancedModel;
    }

    /**
     * Evento al seleccionar en el mapa.
     * @param event el evento en el mapa
     */
    public final void onMarkerSelect(final OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    /**
     * Regresa el marcador en el mapa.
     * @return el marcador en el mapa
     */
    public final Marker getMarker() {
        return marker;
    }

    /**
     * Obtiene los servicios adicionales de un puesto.
     * @return los servicios adicionales de un puesto
     */
    public final String[] getServicios() {
        return servicios;
    }

    /**
     * Modifica los servicios adicionales de un puesto.
     * @param pServicios los nuevos servicios adicionales de un puesto
     */
    public final void setServicios(final String[] pServicios) {
        this.servicios = pServicios;
    }

    /**
     * Obtiene los tipos de comida de un puesto.
     * @return los tipos de comida de un puesto
     */
    public final String[] getTipoComida() {
        return tipoComida;
    }

    /**
     * Modifica los tipos de comida de un puesto.
     * @param pTipoComida los nuevos tipos de comida de un puesto
     */
    public final void setTipoComida(final String[] pTipoComida) {
        this.tipoComida = pTipoComida;
    }

}
