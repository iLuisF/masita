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
@Table(name = "ServicioAdicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioAdicional.findAll", query
        = "SELECT s FROM ServicioAdicional s"),
    @NamedQuery(name = "ServicioAdicional.findByIdServicio", query
        = "SELECT s FROM ServicioAdicional s WHERE s.idServicio = :idServicio"),
    @NamedQuery(name = "ServicioAdicional.findByNombre", query
        = "SELECT s FROM ServicioAdicional s WHERE s.nombre = :nombre")})
public class ServicioAdicional implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicio")
    private Long idServicio;
    /**
     * String nombre, nombre del servicio adicional.
     */
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    /**
     * Collection puestoCollection, puestos.
     */
    @ManyToMany(mappedBy = "servicioAdicionalLista")
    private Collection<Puesto> puestoCollection;
    /**
     * ServicioAdicional.
     */
    public ServicioAdicional() {
    }
    /**
     * @param pIdServicio el id del Servicio.
     */
    public ServicioAdicional(final Long pIdServicio) {
        this.idServicio = pIdServicio;
    }
    /**
     * @param pIdServicio el id del servicio.
     * @param pNombre el nombre del servicio.
     */
    public ServicioAdicional(final Long pIdServicio, final String pNombre) {
        this.idServicio = pIdServicio;
        this.nombre = pNombre;
    }
    /**
     * Regresa el id del Servicio adicional.
     * @return idServicio el id del servicio.
     */
    public Long getIdServicio() {
        return idServicio;
    }
    /**
     * Modifica el id del Servicio adicional.
     * @param pIdServicio el nuevo id del servicio
     */
    public void setIdServicio(Long pIdServicio) {
        this.idServicio = pIdServicio;
    }
    /**
     * Regresa el nombre del Servicio adicional.
     * @return nombre del Servicio adicional.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre del Servicio adicional.
     * @param pNombre nuevo nombre del Servicio adicional.
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }
    /**
    * @return puestoCollection.
     */
    @XmlTransient
    public final Collection<Puesto> getPuestoCollection() {
        return puestoCollection;
    }
    /**
     * @param pPuestoCollection puestos.
     */
    public final void setPuestoCollection(
            final Collection<Puesto> pPuestoCollection) {
        this.puestoCollection = pPuestoCollection;
    }

    @Override
    public final int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    /**
     * Nos indica si un objecto es igual a otro.
     */
    public final boolean equals(final Object object) {
        if (!(object instanceof ServicioAdicional)) {
            return false;
        }
        ServicioAdicional other = (ServicioAdicional) object;
        if ((this.idServicio == null && other.idServicio != null)
                || (this.idServicio != null
                && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "com.kaab.proyecto.db.ServicioAdicional[ idServicio="
                + idServicio + " ]";
    }
}
