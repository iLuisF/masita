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
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author 
 */
@ManagedBean(name = "infoPuestos")
@ViewScoped
public class Puestos implements Serializable {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
    private EntityManager em = emf.createEntityManager();
    private String nombre;
    private Long idPuesto;
    private List<ServicioAdicional> servicioAdicionalLista;
    private PuestoJpaController puesto;
    private List<Puesto> p;
    private Double calificacion;
    private String latitud;
    private String longitud;
    private MapModel advancedModel;
    private Marker marker;
    
    /**
     * Regresa una lista con los puestos.
     * @return lista con los puestos
     */
    public List<Puesto> getListaPuestos() {
        p = new ArrayList<Puesto>();
        
        puesto = new PuestoJpaController(emf);
        List<Puesto> lugares = puesto.findPuestoEntities();
        for (Puesto lugar : lugares) {
            lugar.setNombre(lugar.getNombre());
            lugar.setIdPuesto(lugar.getIdPuesto());
            lugar.setLatitud(lugar.getLatitud());
            lugar.setLongitud(lugar.getLongitud());
            //
            //lugar.setServicioAdicionalLista(lugar.getServicioAdicionalLista());
            //
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
        advancedModel = new DefaultMapModel();
        LatLng coord1 = new LatLng(Double.parseDouble(p1.getLatitud()), Double.parseDouble(p1.getLongitud()));
        advancedModel.addOverlay(new Marker(coord1, p1.getNombre(), "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
        return p1;
    }
    
    /**
     * Obtiene la calificacion de un puesto.
     * @return la calificacion de un puesto
     */
    public double getCalificacion() {
        Query q = em.createQuery("SELECT AVG(c.calificacion) FROM Comentario c WHERE c.idPuesto = "+ this.idPuesto);
        Double actual = (Double) q.getSingleResult();
        calificacion = actual;
        return calificacion;
    }

    /**
     * Modifica la calificacion de un puesto.
     * @param calificacion la calificacion de un puesto a modificar
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Obtiene una lista de servicios adicionales de un puesto.
     * @return lista de servicios adicionales de un puesto
     */
    public List<ServicioAdicional> getServicioAdicionalLista() {
        return servicioAdicionalLista;
    }

    /**
     * Modifica la lista de servicios adicionales de un puesto.
     * @param servicioAdicionalLista la lista de servicios a modificar
     */
    public void setServicioAdicionalLista(List<ServicioAdicional> servicioAdicionalLista) {
        this.servicioAdicionalLista = servicioAdicionalLista;
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
    
}
