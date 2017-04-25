/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Comentario;
import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.ServicioAdicional;
import com.kaab.proyecto.db.TipoComida;
import com.kaab.proyecto.db.controller.ComentarioJpaController;
import com.kaab.proyecto.db.controller.PuestoJpaController;
import com.kaab.proyecto.db.controller.ServicioAdicionalJpaController;
import com.kaab.proyecto.db.controller.TipoComidaJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
 * @author 
 */
@ManagedBean
@ViewScoped
public class InformacionPuesto implements Serializable {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private final EntityManager em = emf.createEntityManager();
    private final PuestoJpaController puesto = new PuestoJpaController(emf);
    private List<Puesto> p;
    private String nombre;
    private Long idPuesto;
    private Double calificacion;
    private String latitud;
    private String longitud;
    private MapModel advancedModel;
    private Marker marker;
    private String[] servicios;
    private String[] tipoComida;
    
    /**
     * Regresa una lista con los puestos.
     * @return lista con los puestos
     */
    public List<Puesto> getListaPuestos() {
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
     * Obtiene la informacion de puesto dado su id.
     * @return la informacion de puesto dado su id
     */
    public Puesto getInformacionPuesto() {
        Puesto p1 = puesto.findPuesto(this.idPuesto);
        int i = 0;
        this.setTipoComida(new String[p1.getTipoComidaCollection().size()]);
        this.setServicios(new String[p1.getServicioAdicionalLista().size()]);
        for(ServicioAdicional x : p1.getServicioAdicionalLista()){
            this.getServicios()[i] = x.getNombre();
            i++;
        }
        i = 0;
        for (TipoComida x : p1.getTipoComidaCollection()) {
            this.getTipoComida()[i] = x.getNombre();
            i++;
        }
        advancedModel = new DefaultMapModel();
        LatLng coord1 = new LatLng(Double.parseDouble(p1.getLatitud()), Double.parseDouble(p1.getLongitud()));
        advancedModel.addOverlay(new Marker(coord1, p1.getNombre(), "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
        return p1;
    }

    /**
     * Obtiene la latitud de un puesto.
     * @return la latitud de un puesto
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * Modifica la latitud de un puesto.
     * @param latitud la latitud de un puesto a modificar
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * Obtiene la longitud de un puesto.
     * @return la longitud de un puesto
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * Modifica la longitud de un puesto.
     * @param longitud la longitud de un puesto a modificar
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * Regresa el modelo para el mapa.
     * @return modelo para el mapa
     */
    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    /**
     * Modifica el modelo para el mapa.
     * @param advancedModel modelo para el mapa a modificar
     */
    public void setAdvancedModel(MapModel advancedModel) {
        this.advancedModel = advancedModel;
    }
    
    /**
     * Evento al seleccionar en el mapa.
     * @param event el evento en el mapa
     */
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
      
    /** 
     * Regresa el marcador en el mapa.
     * @return el marcador en el mapa
     */
    public Marker getMarker() {
        return marker;
    }
    
    /**
     * Obtiene los servicios adicionales de un puesto.
     * @return los servicios adicionales de un puesto
     */
    public String[] getServicios() {
        return servicios;
    }

    /**
     * Modifica los servicios adicionales de un puesto.
     * @param servicios los nuevos servicios adicionales de un puesto
     */
    public void setServicios(String[] servicios) {
        this.servicios = servicios;
    }

    /**
     * Obtiene los tipos de comida de un puesto.
     * @return los tipos de comida de un puesto
     */
    public String[] getTipoComida() {
        return tipoComida;
    }

    /**
     * Modifica los tipos de comida de un puesto.
     * @param tipoComida los nuevos tipos de comida de un puesto
     */
    public void setTipoComida(String[] tipoComida) {
        this.tipoComida = tipoComida;
    }
    
}
