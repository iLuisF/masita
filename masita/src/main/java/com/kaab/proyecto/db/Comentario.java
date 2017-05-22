/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que modela un comentario.
 * @author KAAB
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "Comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll",
            query = "SELECT c FROM Comentario c"),
    @NamedQuery(name = "Comentario.findByIdComentario",
            query = "SELECT c FROM Comentario c WHERE c.idComentario "
                    + "= :idComentario"),
    @NamedQuery(name = "Comentario.findByFecha",
            query = "SELECT c FROM Comentario c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Comentario.findByCalificacion",
            query = "SELECT c FROM Comentario c WHERE c.calificacion "
                    + "= :calificacion"),
    @NamedQuery(name = "seleccionarComentariosPuesto",
            query = "SELECT c FROM Comentario c WHERE c.idPuesto = :idPuesto")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * El id del comentario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComentario")
    private Long idComentario;
    /**
     * El contenido del comentario.
     */
    @Lob
    @Column(name = "contenido")
    private String contenido;
    /**
     * La fecha del comentario.
     */
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    /**
     * La calificación asociada al comentario.
     */
    @Column(name = "calificacion")
    private Integer calificacion;
    /**
     * Tabla que hace join con la tabla Puesto en el atributo idPuesto.
     */
    @JoinColumn(name = "idPuesto", referencedColumnName = "idPuesto")
    @ManyToOne(optional = false)
    private Puesto idPuesto;
    /**
     * Tabla que hace join con la tabla Usuario en el atributo idUsuario.
     */
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    /**
     * Constructor por omisión.
     */
    public Comentario() {
    }

    /**
     * Constructor para Comentario.
     * Inicializa un comentario con su id.
     * @param pIdComentario el nuevo id del comentario.
     */
    public Comentario(final Long pIdComentario) {
        this.idComentario = pIdComentario;
    }

    /**
     * Constructor para Comentario.
     * Inicializa un comentario con su id y fecha.
     * @param pIdComentario el id del comentario
     * @param pFecha la fecha de creación del comentario
     */
    public Comentario(final Long pIdComentario, final Date pFecha) {
        this.idComentario = pIdComentario;
        this.fecha = pFecha;
    }

    /**
     * Regresa el id del comentario.
     * @return el id del comentario.
     */
    public Long getIdComentario() {
        return idComentario;
    }

    /**
     * Modifica el id del comentario.
     * @param pIdComentario el nuevo id del comentario.
     */
    public void setIdComentario(final Long pIdComentario) {
        this.idComentario = pIdComentario;
    }

    /**
     * Regresa el contenido de un comentario.
     * @return el contenido de un comentario.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Modifica el contenido de un comentario.
     * @param pContenido el nuevo contenido del comentario.
     */
    public void setContenido(final String pContenido) {
        this.contenido = pContenido;
    }

    /**
     * Regresa la fecha de un comentario.
     * @return la fecha de un comentario.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha de un comentario.
     * @param pFecha le nueva fecha de un comentario.
     */
    public void setFecha(final Date pFecha) {
        this.fecha = pFecha;
    }

    /**
     * Regresa la calificación asociada a un comentario.
     * @return la calificación asociada a un comentario.
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * Modifica la calificación asociada a un comentario.
     * @param pCalificacion la nueva calificación asociada a un comentario.
     */
    public void setCalificacion(final Integer pCalificacion) {
        this.calificacion = pCalificacion;
    }

    /**
     * Regresa el id del puesto asociado al comentario.
     * @return el id del puesto asociado al comentario.
     */
    public Puesto getIdPuesto() {
        return idPuesto;
    }

    /**
     * Modifica el id del puesto asociado al comentario.
     * @param pIdPuesto el nuevo id del puesto asociado al comentario.
     */
    public void setIdPuesto(final Puesto pIdPuesto) {
        this.idPuesto = pIdPuesto;
    }

    /**
     * Regresa el id del usuario asociado al comentario.
     * @return el id del usuario asociado al comentario.
     */
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    /**
     * Modifica el id del usuario asociado al comentario.
     * @param pIdUsuario el nuevo id del usuario asociado al comentario.
     */
    public void setIdUsuario(final Usuario pIdUsuario) {
        this.idUsuario = pIdUsuario;
    }

    @Override
    public final int hashCode() {
        int hash = 0;
        if (idComentario != null) {
            hash += idComentario.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    @Override
    public final boolean equals(final Object object) {
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        return !((this.idComentario == null && other.idComentario != null)
                || (!this.idComentario.equals(other.idComentario)
                && this.idComentario != null));
    }

    @Override
    public final String toString() {
        return "com.kaab.proyecto.db.Comentario[ idComentario="
                + idComentario + " ]";
    }
}