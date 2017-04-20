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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;

/**
 * Permite conocer todos los puestos que se encuentran en la base de datos.
 * 
 * @author Ernesto Palacios
 */
@ManagedBean
@ViewScoped
public class LeerPuesto implements Serializable{
    
    private String nombre;
    private String longitud;
    private String latitud;
    private String horario;
    private String[] servicios;
    private String[] tipoComida;
    private PuestoJpaController cpuesto;
    private List<Puesto> listaP;

    public String[] getServicios() {
        return servicios;
    }

    public void setServicios(String[] servicios) {
        this.servicios = servicios;
    }

    public String[] getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String[] tipoComida) {
        this.tipoComida = tipoComida;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    
    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public List<Puesto> crearListaPuesto(){
        listaP = new ArrayList<Puesto>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        cpuesto = new PuestoJpaController(emf);
        List<Puesto> puestos = cpuesto.findPuestoEntities();
        
        for(Puesto actual : puestos){
            Puesto nvo = new Puesto(actual.getIdPuesto());
            nvo.setIdPuesto(actual.getIdPuesto());
            nvo.setNombre(actual.getNombre());
            nvo.setTipoComidaCollection(actual.getTipoComidaCollection());
            nvo.setHorario(actual.getHorario());
            nvo.setLatitud(actual.getLatitud());
            nvo.setLongitud(actual.getLongitud());
            nvo.setServicioAdicionalLista(actual.getServicioAdicionalLista());
            nvo.setTipoComidaCollection(actual.getTipoComidaCollection());
            listaP.add(nvo);
        }     
        return listaP;
    }
    private Puesto busca(Puesto actual){
        Puesto aux = null;
        for(Puesto x : listaP){
            if(Objects.equals(actual.getIdPuesto(), x.getIdPuesto())){
                aux = x;
            }
        }
        return aux;
    }
    
    private boolean hayCambios(Puesto aux){
        boolean condicion = false;
        if(!this.getNombre().equals("")){
            aux.setNombre(nombre);
            condicion = true;
        }
        
        if(!this.getLongitud().equals("")){
            aux.setLongitud(this.getLongitud());
            condicion = true;
        }
        
        if(!this.getLatitud().equals("")){
            aux.setLatitud(this.getLatitud());
            condicion = true;
        }
        
        if(!this.getHorario().equals("")){
            aux.setHorario(horario);
            condicion = true;
        } 
        return condicion;
    }
    /**
     * Deja sin modificar un puesto.
     * @param event 
     */
    public void onRowEdit(RowEditEvent event) {
          Puesto nvoPuesto = new Puesto(((Puesto) event.getObject()).getIdPuesto());
          Puesto aux = busca(nvoPuesto);
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
          cpuesto = new PuestoJpaController(emf);
         
    
        if(this.hayCambios(aux)){
            try {
                cpuesto.edit(aux);
            } catch (Exception ex) {
                Logger.getLogger(LeerPuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            FacesMessage msg = new FacesMessage("Puesto editado correctamente", Long.toString(((Puesto) event.getObject()).getIdPuesto()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        this.setNombre("");
        this.setLatitud("");
        this.setLongitud("");
        this.setHorario("");
    }
    
    /**
     * Elimina un puesto de la base de datos
     * @param event
     * @throws NonexistentEntityException 
     */
    public void onRowCancel(RowEditEvent event) throws NonexistentEntityException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
            cpuesto = new PuestoJpaController(emf);
            cpuesto.destroy(((Puesto) event.getObject()).getIdPuesto());
            FacesMessage msg = new FacesMessage("Puesto Eliminado", Long.toString(((Puesto) event.getObject()).getIdPuesto()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context= FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/masita/EliminaPuestoIH.xhtml");
        } catch (IOException ex) {
            ;
        }
    }
    
    
}