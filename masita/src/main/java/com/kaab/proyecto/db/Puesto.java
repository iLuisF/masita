/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que modela un Puesto de Comida.
 * @author KAAB
 */
@Entity
@Table(name = "Puesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puesto.findAll", query = "SELECT p FROM Puesto p"),
    @NamedQuery(name = "Puesto.findByIdPuesto",
            query = "SELECT p FROM Puesto p WHERE p.idPuesto = :idPuesto"),
    @NamedQuery(name = "Puesto.findByNombre",
            query = "SELECT p FROM Puesto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Puesto.findByHorario",
            query = "SELECT p FROM Puesto p WHERE p.horario = :horario"),
    @NamedQuery(name = "Puesto.findByLatitud",
            query = "SELECT p FROM Puesto p WHERE p.latitud = :latitud"),
    @NamedQuery(name = "Puesto.findByLongitud",
            query = "SELECT p FROM Puesto p WHERE p.longitud = :longitud")})
public class Puesto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id del puesto de comida.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPuesto")
    private Long idPuesto;
    /**
     * Nombre del puesto de comida.
     */
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    /**
     * El horario de un puesto de comida.
     */
    @Basic(optional = false)
    @Column(name = "horario")
    private String horario;
    /**
     * La latitud de un puesto de comida.
     */
    @Basic(optional = false)
    @Column(name = "latitud")
    private String latitud;
    /**
     * La longitud de un puesto de comida.
     */
    @Basic(optional = false)
    @Column(name = "longitud")
    private String longitud;
    /**
     * Los servicios adicionales de un puesto de comida.
     */
    @ManyToMany
    @JoinTable(
    name = "ServicioAdicionalPuesto",
    joinColumns = @JoinColumn(name = "idPuesto"),
    inverseJoinColumns = @JoinColumn(name = "idServicio"))
    private List<ServicioAdicional> servicioAdicionalLista =
            new ArrayList<ServicioAdicional>();
    /**
     * Un puesto tiene varios tipos de comida.
     */
    @ManyToMany
    @JoinTable(
    name = "TipoComidaPuesto",
    joinColumns = @JoinColumn(name = "idPuesto"),
    inverseJoinColumns = @JoinColumn(name = "idTipoComida"))
    private List<TipoComida> tipoComidaCollection;

    /**
     * Constructor por omisión.
     */
    public Puesto() {
    }

    /**
     * Constructor.
     * Inicializa un puesto a partir de su id.
     * @param pIdPuesto el id del puesto.
     */
    public Puesto(final Long pIdPuesto) {
        this.idPuesto = pIdPuesto;
    }

    /**
     * Constructor.
     * @param pIdPuesto el id del puesto.
     * @param pNombre el nombre del puesto.
     * @param pHorario el horario del puesto.
     * @param pLatitud la latitud del puesto.
     * @param pLongitud la longitud del puesto.
     */
    public Puesto(final Long pIdPuesto, final String pNombre,
            final String pHorario, final String pLatitud,
            final String pLongitud) {
        this.idPuesto = pIdPuesto;
        this.nombre = pNombre;
        this.horario = pHorario;
        this.latitud = pLatitud;
        this.longitud = pLongitud;
    }

    /**
     * Regresa el id del puesto de comida.
     * @return el id del puesto de comida.
     */
    public Long getIdPuesto() {
        return idPuesto;
    }

    /**
     * Modifica el id del puesto de comida.
     * @param pIdPuesto el nuevo id del puesto de comida.
     */
    public void setIdPuesto(final Long pIdPuesto) {
        this.idPuesto = pIdPuesto;
    }

    /**
     * Regresa el nombre del puesto de comida.
     * @return el nombre del puesto de comida.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del puesto de comida.
     * @param pNombre el nuevo nombre del puesto de comida.
     */
    public void setNombre(final String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Regresa el horario del puesto de comida.
     * @return el horario del puesto de comida.
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Modifica el horario del puesto de comida.
     * @param pHorario el nuevo horario del puesto de comida.
     */
    public void setHorario(final String pHorario) {
        this.horario = pHorario;
    }

    /**
     * Regresa la latitud del puesto de comida.
     * @return la latitud del puesto de comida.
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * Modifica la latitud del puesto de comida.
     * @param pLatitud la nueva latitud del puesto de comida.
     */
    public void setLatitud(final String pLatitud) {
        this.latitud = pLatitud;
    }

    /**
     * Regresa la longitud del puesto de comida.
     * @return la longitud del puesto de comida.
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * Modifica la longitud del puesto de comida.
     * @param pLongitud la nueva longitud del puesto de comida.
     */
    public void setLongitud(final String pLongitud) {
        this.longitud = pLongitud;
    }
    
    /**
     * Regresa la lista con los tipos de comida de un puesto.
     * @return la lista con los tipos de comida de un puesto.
     */
    public List<TipoComida> getTipoComidaCollection() {
        return tipoComidaCollection;
    }

    /**
     * Modifica la lista con los tipos de comida de un puesto.
     * @param pTipoComidaCol la nueva lista de los tipos de comida de un
     * puesto.
     */
    public void setTipoComidaCollection(final List<TipoComida> pTipoComidaCol) {
        this.tipoComidaCollection = pTipoComidaCol;
    }

    /**
     * Regresa la lista con los servicios adicionales de un puesto de comida.
     * @return la lista con los servicios adicionales de un puesto de comida.
     */
    public List<ServicioAdicional> getServicioAdicionalLista() {
        return servicioAdicionalLista;
    }

    /**
     * Modifica la lista de los servicios adicionales de un puesto de comida.
     * @param pServicioAdicionalLista la nueva lista con los servicios
     * adicionales de un puesto de comida.
     */
    public void setServicioAdicionalLista(final List<ServicioAdicional>
            pServicioAdicionalLista) {
        this.servicioAdicionalLista = pServicioAdicionalLista;
    }

    @Override
    public final int hashCode() {
        int hash = 0;
        if (idPuesto != null) {
            hash += idPuesto.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    @Override
    public final boolean equals(final Object object) {
        if (!(object instanceof Puesto)) {
            return false;
        }
        Puesto other = (Puesto) object;
        return !((this.idPuesto == null && other.idPuesto != null)
                || (this.idPuesto != null
                && !this.idPuesto.equals(other.idPuesto)));
    }

    @Override
    public final String toString() {
        return "com.kaab.proyecto.db.Puesto[ idPuesto=" + idPuesto + " ]";
    }
}
