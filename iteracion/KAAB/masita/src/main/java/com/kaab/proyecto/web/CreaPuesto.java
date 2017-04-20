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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;

/**
 * Permite crear un puesto para agregarlo a la base de datos
 * 
 * @author Ernesto Palacios
 */
@ManagedBean
@ViewScoped
public class CreaPuesto implements Serializable {
    private Long id;
    private String nombre;
    private String horario;
    private String latitud;
    private String longitud;
    private String[] servicios;
    private String[] tipoComida;
    
    public String[] getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String[] tipoComida) {
        this.tipoComida = tipoComida;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String[] getServicios() {
        return servicios;
    }

    public void setServicios(String[] servicios) {
        this.servicios = servicios;
    }
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

     public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        this.latitud = String.valueOf(latlng.getLat());
        this.longitud = String.valueOf(latlng.getLng());
    }
     
     private boolean verificaCoordenadas(){
        FacesMessage mensaje;
        if(this.getLatitud() != null && this.getLongitud() != null){
                double lon = Double.valueOf(this.getLongitud());
                double lat = Double.valueOf(this.getLatitud());
                return true;
                /*
               if(lat > 19.326 || lat < 19.3226 || lon > -99.177 || lon < -99.1824 ){
                   mensaje = new FacesMessage("Coordenas invalidas : no esta dentro de la Facultad de Ciencias");
                   FacesContext.getCurrentInstance().addMessage(null, mensaje);
                   return false;
                }else{
                   return true;
               }*/
        }else{
               mensaje = new FacesMessage("Coordenas invalidas : ingrese un punto en el mapa");
               FacesContext.getCurrentInstance().addMessage(null, mensaje);
               return false;
        }
    }
     
    private void agregaServiciosAdicionales(Puesto p){
        List<ServicioAdicional> servi = new ArrayList<ServicioAdicional>();
        ServicioAdicional serv_actual = null;
        for (String servicio : this.getServicios()) {
            switch (servicio) {
                case "Mesas":
                    serv_actual = new ServicioAdicional(1l,"Mesas");
                    break;
                case "Baños":
                    serv_actual = new ServicioAdicional(2l,"Baños");
                    break;
                case "Comida para llevar":
                    serv_actual = new ServicioAdicional(3l,"Comida para llevar");
            }
            servi.add(serv_actual);
        }
        p.setServicioAdicionalLista(servi);
    }
    
    private void agregaTiposComida(Puesto p){
        List<TipoComida> tipos = new ArrayList<TipoComida>();
        TipoComida actual = null;
        for(String tipo : this.getTipoComida()){
            switch(tipo){
                case "Comida corrida":
                    actual = new TipoComida(1l,"Comida corrida");
                    break;
                case "Hamburguesas":
                    actual = new TipoComida(2l,"Hamburguesas");
                    break;
                case "Ensaladas":
                    actual = new TipoComida(3l,"Ensaladas");
                    break;
                case "Dulces" :
                    actual = new TipoComida(4l,"Dulces");
                    break;
                case "Tortas":
                    actual = new TipoComida(5l,"Tortas");
                    break;
                case "Garnachas":
                    actual = new TipoComida(6l,"Garnachas");
                    break;
                case "Hotdog":
                    actual = new TipoComida(7l,"Hotdog");
                    break;
                case "Tacos":
                    actual = new TipoComida(8l,"Tacos");
                    break;
                case "Sushi":
                    actual = new TipoComida(9l,"Sushi");
                    break;
            }
            tipos.add(actual);
        }
        p.setTipoComidaCollection(tipos);
    }
   
    /**
     *
     * @param actionEvent
     */
    public void AgregaPuesto(ActionEvent actionEvent){
        boolean condicion = true;
        Puesto actual = new Puesto(this.getId(),this.getNombre(), this.getHorario() , this.getLatitud() , this.getLongitud());
        LeerPuesto aux = new LeerPuesto();
        List<Puesto> puestos = aux.crearListaPuesto();
        FacesMessage mensaje;
        for(Puesto x : puestos){
            if(x.getNombre().equals(actual.getNombre())){
                mensaje = new FacesMessage("nombre del puesto duplicado");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                condicion = false;
                System.out.println("entre " + condicion);
            }
        }
        
        condicion = condicion && this.verificaCoordenadas();
        
        
        if(condicion){
            this.agregaServiciosAdicionales(actual);
            this.agregaTiposComida(actual);
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
                PuestoJpaController cpuesto = new PuestoJpaController(emf);
                cpuesto.create(actual);
                mensaje = new FacesMessage("Puesto Agregado Correctamente");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                FacesContext context= FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/masita/EliminaPuestoIH.xhtml");
            } catch (IOException ex) {
               ;
            }
        }
    }
}