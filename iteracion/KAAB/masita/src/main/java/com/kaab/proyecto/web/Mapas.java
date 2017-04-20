/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.controller.PuestoJpaController;
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
 *
 * @author miguel
 */
@ManagedBean
@ViewScoped
public class Mapas implements Serializable {

    private MapModel advancedModel;

    private Marker marker;

    private PuestoJpaController lugarCtrl;

    private String nombre;

    private double lat;

    private double lng;

    @PostConstruct
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        advancedModel = new DefaultMapModel();
        lugarCtrl = new PuestoJpaController(emf);
//        Lat:19.324499386445776, Lng:-99.17937085032463
//        Lat:19.324328359583355, Lng:-99.17934268712997

        //Shared coordinates
        List<Puesto> lugares = new LeerPuesto().crearListaPuesto();
        for (Puesto lugar : lugares) {
            Double latitud = Double.parseDouble(lugar.getLatitud());
            Double longitud = Double.parseDouble(lugar.getLongitud());
            String nombre = lugar.getNombre();
            System.out.println(latitud + ", " + longitud + ", " + nombre);
            advancedModel.addOverlay(new Marker(new LatLng(latitud, longitud), nombre));
        }
    }

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        System.out.println(event.toString());
        marker = (Marker) event.getOverlay();
        System.out.println(marker.getTitle());
    }

    public Marker getMarker() {
        return marker;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return lat;
    }

    public void setLatitud(double lat) {
        this.lat = lat;
    }

    public double getLongitud() {
        return lng;
    }

    public void setLongitud(double lng) {
        this.lng = lng;
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), nombre);
        Puesto l = new Puesto();
        l.setNombre(nombre);
        l.setLatitud(Double.toString(lat));
        l.setLongitud(Double.toString(lng));
        lugarCtrl.create(l);
        advancedModel.addOverlay(marker);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }

}
