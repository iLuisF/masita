/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
 *
 * @author esperanzahigareda
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "Puesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puesto.findAll", query = "SELECT p FROM Puesto p")
    , @NamedQuery(name = "Puesto.findByIdPuesto", query = "SELECT p FROM Puesto p WHERE p.idPuesto = :idPuesto")
    , @NamedQuery(name = "Puesto.findByNombre", query = "SELECT p FROM Puesto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Puesto.findByHorario", query = "SELECT p FROM Puesto p WHERE p.horario = :horario")
    , @NamedQuery(name = "Puesto.findByLatitud", query = "SELECT p FROM Puesto p WHERE p.latitud = :latitud")
    , @NamedQuery(name = "Puesto.findByLongitud", query = "SELECT p FROM Puesto p WHERE p.longitud = :longitud")})
public class Puesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPuesto")
    private Long idPuesto;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "horario")
    private String horario;
    @Basic(optional = false)
    @Column(name = "latitud")
    private String latitud;
    @Basic(optional = false)
    @Column(name = "longitud")
    private String longitud;
    @ManyToMany     ///////*************** Aquí
    @JoinTable(
    name="ServicioAdicionalPuesto",
    joinColumns=@JoinColumn(name="idPuesto"),
    inverseJoinColumns=@JoinColumn(name="idServicio"))
    private Collection<ServicioAdicional> servicioAdicionalCollection;///////*******
    @ManyToMany     ///////*************** Aquí
    @JoinTable(
    name="TipoComidaPuesto",
    joinColumns=@JoinColumn(name="idPuesto"),
    inverseJoinColumns=@JoinColumn(name="idSTipoComida"))
    private Collection<TipoComida> tipoComidaCollection;///////*******
    

    public Puesto() {
    }

    public Puesto(Long idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Puesto(Long idPuesto, String nombre, String horario, String latitud, String longitud) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Long idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuesto != null ? idPuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puesto)) {
            return false;
        }
        Puesto other = (Puesto) object;
        if ((this.idPuesto == null && other.idPuesto != null) || (this.idPuesto != null && !this.idPuesto.equals(other.idPuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kaab.proyecto.db.Puesto[ idPuesto=" + idPuesto + " ]";
    }
    
}
