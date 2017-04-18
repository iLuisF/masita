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
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import static com.sun.javafx.logging.PulseLogger.addMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static net.bootsfaces.component.ComponentsEnum.message;
import org.primefaces.event.RowEditEvent;
import sun.security.validator.ValidatorException;

/**
 * Permite conocer todos los comentarios que se encuentran en la base de datos.
 * 
 * @author Ernesto Palacios
 */
@ManagedBean
@ViewScoped
public class CreaPuesto {
    private Long id;
    private String nombre;
    private String horario;
    private String latitud;
    private String longitud;
    private Collection<ServicioAdicional> servicioAdicionalCollection;
    private Collection<TipoComida> tipoComidaCollection;
    private String[] servicios;
    
    
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

    public Collection<ServicioAdicional> getServicioAdicionalCollection() {
        return servicioAdicionalCollection;
    }

    public void setServicioAdicionalCollection(Collection<ServicioAdicional> servicioAdicionalCollection) {
        this.servicioAdicionalCollection = servicioAdicionalCollection;
    }

    public Collection<TipoComida> getTipoComidaCollection() {
        return tipoComidaCollection;
    }

    public void setTipoComidaCollection(Collection<TipoComida> tipoComidaCollection) {
        this.tipoComidaCollection = tipoComidaCollection;
    }
   
    /**
     *
     * @throws com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException
     */
    public void AgregaPuesto(){
        if(this.getId() == null){
            addMessage("Id Invalido");
            System.out.println("entre");
        }
        
           // System.out.println(this.getId()+ " " + this.getNombre() + " " + this.getHorario() + " " + this.getLatitud() + " " + this.getLongitud());
        Puesto actual = new Puesto(this.getId(),this.getNombre(), this.getHorario() , this.getLatitud() , this.getLongitud());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        PuestoJpaController cpuesto = new PuestoJpaController(emf);
        cpuesto.create(actual);
        System.out.println(this.getNombre());
    }
}