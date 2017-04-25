package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Puesto;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que obtiene los puestos y los muestra en un mapa.
 * @author 
 */
@ManagedBean
@ViewScoped
public class Mapas implements Serializable {

    private MapModel advancedModel;             // Modelo del mapa
    private Marker marker;                      // Marcador en el mapa
    private PuestoJpaController lugarCtrl;      // Controlador para el puesto
    private String nombre;                      // Nombre del puesto
    private double lat;                         // Latitud del puesto
    private double lng;                         // Longitud del puesto
    private int idPuesto;

    /**
     * Inicia un mapa.
     */
    @PostConstruct
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        advancedModel = new DefaultMapModel();
        lugarCtrl = new PuestoJpaController(emf);
        List<Puesto> lugares = lugarCtrl.findPuestoEntities();
        for (Puesto lugar : lugares) {
            Double latitud = Double.parseDouble(lugar.getLatitud());
            Double longitud = Double.parseDouble(lugar.getLongitud());
            String nombre = lugar.getNombre();
            System.out.println(latitud + ", " + longitud + ", " + nombre);
            advancedModel.addOverlay(new Marker(new LatLng(latitud, longitud), nombre));
        }
        
    }
    
    private Puesto busca(String nombre , List<Puesto> lugares){
        Puesto resultado= null;
        for(Puesto i : lugares){
            if(i.getNombre().equals(nombre)){
                resultado = i;
            }
        }
        return resultado;
    }
    
    public void redirige(String nombrePuesto) throws IOException{
         List<Puesto> lugares = lugarCtrl.findPuestoEntities();
        Long idPuesto = this.busca(nombrePuesto, lugares).getIdPuesto();
        FacesContext context= FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("/masita/PerfilPuestoIH.xhtml?idPuesto="+idPuesto);
    }
    /**
     * Obtiene un modelo del mapa.
     * @return un modelo del mapa
     */
    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    /**
     * Establece el marcador.
     * @param event 
     */
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    /**
     * Obtiene un marcador en el mapa.
     * @return un marcador
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * Obtiene el nombre del puesto.
     * @return nombre del puesto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del puesto.
     * @param nombre el nombre del puesto a modificar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la latitud en el mapa.
     * @return la latitud en el mapa
     */
    public double getLatitud() {
        return lat;
    }

    /**
     * Modifica la latitud en el mapa.
     * @param lat la nueva latitud en el mapa
     */
    public void setLatitud(double lat) {
        this.lat = lat;
    }

    /**
     * Obtiene la longitud en el mapa.
     * @return la longitud en el mapa
     */
    public double getLongitud() {
        return lng;
    }

    /**
     * Modifica la longitud en el mapa.
     * @param lng la nueva longitud en el mapa
     */
    public void setLongitud(double lng) {
        this.lng = lng;
    }

    /**
     * Obtiene el id de un puesto.
     * @return el id de un puesto
     */
    public int getIdPuesto() {
        return idPuesto;
    }

    /**
     * Cambia el id de un puesto.
     * @param idPuesto el nuevo id de un puesto
     */
    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }
    
    

}
