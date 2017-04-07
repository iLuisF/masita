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

/**
 *
 * @author esperanzahigareda
 */
@Entity
@Table(name = "TipoComida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComida.findAll", query = "SELECT t FROM TipoComida t")
    , @NamedQuery(name = "TipoComida.findByIdTipoComida", query = "SELECT t FROM TipoComida t WHERE t.idTipoComida = :idTipoComida")
    , @NamedQuery(name = "TipoComida.findByNombre", query = "SELECT t FROM TipoComida t WHERE t.nombre = :nombre")})
public class TipoComida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoComida")
    private Long idTipoComida;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "tipoComidaCollection")
    private Collection<Puesto> puestoCollection;

    public TipoComida() {
    }

    public TipoComida(Long idTipoComida) {
        this.idTipoComida = idTipoComida;
    }

    public TipoComida(Long idTipoComida, String nombre) {
        this.idTipoComida = idTipoComida;
        this.nombre = nombre;
    }

    public Long getIdTipoComida() {
        return idTipoComida;
    }

    public void setIdTipoComida(Long idTipoComida) {
        this.idTipoComida = idTipoComida;
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
        hash += (idTipoComida != null ? idTipoComida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComida)) {
            return false;
        }
        TipoComida other = (TipoComida) object;
        if ((this.idTipoComida == null && other.idTipoComida != null) || (this.idTipoComida != null && !this.idTipoComida.equals(other.idTipoComida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kaab.proyecto.db.TipoComida[ idTipoComida=" + idTipoComida + " ]";
    }
    
}
