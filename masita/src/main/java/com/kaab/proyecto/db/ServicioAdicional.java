/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.*;

/**
 *
 * @author esperanzahigareda
 */
@Entity
@Table(name = "ServicioAdicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioAdicional.findAll", query = "SELECT s FROM ServicioAdicional s")
    , @NamedQuery(name = "ServicioAdicional.findByIdServicio", query = "SELECT s FROM ServicioAdicional s WHERE s.idServicio = :idServicio")
    , @NamedQuery(name = "ServicioAdicional.findByNombre", query = "SELECT s FROM ServicioAdicional s WHERE s.nombre = :nombre")})
public class ServicioAdicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicio")
    private Long idServicio;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "servicioAdicionalLista")
    private Collection<Puesto> puestoCollection;

    public ServicioAdicional() {
    }

    public ServicioAdicional(Long idServicio) {
        this.idServicio = idServicio;
    }

    public ServicioAdicional(Long idServicio, String nombre) {
        this.idServicio = idServicio;
        this.nombre = nombre;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Puesto> getPuestoCollection() {
        return puestoCollection;
    }

    public void setPuestoCollection(Collection<Puesto> puestoCollection) {
        this.puestoCollection = puestoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioAdicional)) {
            return false;
        }
        ServicioAdicional other = (ServicioAdicional) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kaab.proyecto.db.ServicioAdicional[ idServicio=" + idServicio + " ]";
    }
    
}
