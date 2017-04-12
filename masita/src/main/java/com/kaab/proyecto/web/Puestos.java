/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.TipoComida;
import com.kaab.proyecto.db.controller.PuestoJpaController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 
 */
@ManagedBean
@ViewScoped
public class Puestos {
    
    private String nombre;
    //private Collection<TipoComida> tipoComidaCollection;

    private PuestoJpaController persona;

    public List<Puesto> getListaPuestos() {

        List<Puesto> p = new ArrayList<Puesto>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");

        persona = new PuestoJpaController(emf);

        List<Puesto> lugares = persona.findPuestoEntities();
        for (Puesto lugar : lugares) {
            Puesto pp = new Puesto();
            pp.setNombre(lugar.getNombre());
            //pp.setTipoComidaCollection(lugar.getTipoComidaCollection());
            p.add(pp);
        }
        return p;
    }
}
