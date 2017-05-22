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
    @NamedQuery(name = "TipoComida.findAll", query
            = "SELECT t FROM TipoComida t"),
    @NamedQuery(name = "TipoComida.findByIdTipoComida", query
           = "SELECT t FROM TipoComida t WHERE t.idTipoComida = :idTipoComida"),
    @NamedQuery(name = "TipoComida.findByNombre", query
            = "SELECT t FROM TipoComida t WHERE t.nombre = :nombre")})
public class TipoComida implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Long idTipoComida, el id del tipo de comida.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoComida")
    private Long idTipoComida;
    /**
     * String nombre, el nombre del tipo de comida.
     */
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    /**
     * Collection puestoCollection, puestos.
     */
    @ManyToMany(mappedBy = "tipoComidaCollection")
    private Collection<Puesto> puestoCollection;
    /**
     * TipoComida.
     */
    public TipoComida() {
    }
    /**
     * @param pIdTipoComida el id del tipo de comida.
     */
    public TipoComida(final Long pIdTipoComida) {
        this.idTipoComida = pIdTipoComida;
    }
    /**
     * @param pIdTipoComida Id del tipo de comida.
     * @param pNombre nombre del tipo de comida.
     */
    public TipoComida(final Long pIdTipoComida, final String pNombre) {
        this.idTipoComida = pIdTipoComida;
        this.nombre = pNombre;
    }
    /**
     * Regresa el id del tipo de comida.
     * @return idTipoComida el id del tipo de comida
     */
    public Long getIdTipoComida() {
        return idTipoComida;
    }
    /**
     * Modifica el id del tipo de comida.
     * @param pIdTipoComida el nuevo id del tipo de comida.
     */
    public void setIdTipoComida(Long pIdTipoComida) {
        this.idTipoComida = pIdTipoComida;
    }
    /**
     * Regresa el nombre del tipo de comida.
     * @return  nombre el nombre del tipo de comida.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre del tipo de comida.
     * @param nombre el nuevo nomrbe del tipo de comida.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    /**
     * @return hash.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        hash += (idTipoComida != null ? idTipoComida.hashCode() : 0);
        return hash;
    }
    /**
     * Indica si un objeto es igual a otro.
     * @param object el objeto a comparar.
     * @return boolean.
     */
    @Override
    public final boolean equals(final Object object) {
        if (!(object instanceof TipoComida)) {
            return false;
        }
        TipoComida other = (TipoComida) object;
        if ((this.idTipoComida == null && other.idTipoComida != null)
                || (this.idTipoComida != null
                && !this.idTipoComida.equals(other.idTipoComida))) {
            return false;
        }
        return true;
    }
    /**
     * @return String.
     */
    @Override
    public final String toString() {
        return "com.kaab.proyecto.db.TipoComida[ idTipoComida=" + idTipoComida
                + " ]";
    }
}
