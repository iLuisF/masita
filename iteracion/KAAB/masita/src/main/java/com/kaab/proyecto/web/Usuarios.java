/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.UsuarioJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author esperanzahigareda
 */
@ManagedBean
@ViewScoped
public class Usuarios {
    
    private String nombre;
    private UsuarioJpaController usuario;
    
    public List<Usuario> getListaUsuarios(){
        List<Usuario> p = new ArrayList<Usuario>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");

        usuario = new UsuarioJpaController(emf);

        List<Usuario> usuarios = usuario.findUsuarioEntities();
        for (Usuario u : usuarios) {
            u.setNombre(u.getNombre());
            
            //pp.setTipoComidaCollection(lugar.getTipoComidaCollection());
            p.add(u);
        }
        return p;
    }
    
    
}
