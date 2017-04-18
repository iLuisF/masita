/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.TipoComida;
import com.kaab.proyecto.db.controller.PuestoJpaController;
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.RowEditEvent;

/**
 * Permite conocer todos los comentarios que se encuentran en la base de datos.
 * 
 * @author Flores González Luis
 */
@ManagedBean
@ViewScoped
public class LeerPuesto implements Serializable{
    
    private PuestoJpaController cpuesto;
    private List<Puesto> listaP;
    
    public List<Puesto> crearListaPuesto(){
        listaP = new ArrayList<Puesto>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        cpuesto = new PuestoJpaController(emf);
        List<Puesto> puestos = cpuesto.findPuestoEntities();
        
        for(Puesto actual : puestos){
            actual.setIdPuesto(actual.getIdPuesto());
            actual.setNombre(actual.getNombre());
            actual.setTipoComidaCollection(actual.getTipoComidaCollection());
            actual.setHorario(actual.getHorario());
            actual.setLatitud(actual.getLatitud());
            actual.setLongitud(actual.getLongitud());
            listaP.add(actual);
        }
        return listaP;
    }
    /**
     * Deja sin modificar un puesto.
     * @param event 
     */
    public void onRowEdit(RowEditEvent event) {
        Puesto nvoPuesto = new Puesto(((Puesto) event.getObject()).getIdPuesto());
        System.out.println(nvoPuesto);
        FacesMessage msg = new FacesMessage("Puesto editado correctamente", Long.toString(((Puesto) event.getObject()).getIdPuesto()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     * Elimina un puesto de la base de datos
     * @param event
     * @throws NonexistentEntityException 
     */
    public void onRowCancel(RowEditEvent event) throws NonexistentEntityException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        cpuesto = new PuestoJpaController(emf);
        cpuesto.destroy(((Puesto) event.getObject()).getIdPuesto());
        
        FacesMessage msg = new FacesMessage("Puesto Eliminado", Long.toString(((Puesto) event.getObject()).getIdPuesto()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
}