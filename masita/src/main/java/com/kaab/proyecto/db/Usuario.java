/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author esperanzahigareda
 */
@Entity
@Table(name = "Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query
            = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByCorreo", query
            = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByContrasenia", query
            = "SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia"),
    @NamedQuery(name = "Usuario.findByNombre", query
            = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApp", query
            = "SELECT u FROM Usuario u WHERE u.app = :app"),
    @NamedQuery(name = "Usuario.findByApm", query
            = "SELECT u FROM Usuario u WHERE u.apm = :apm"),
    @NamedQuery(name = "Usuario.findByActivo", query
            = "SELECT u FROM Usuario u WHERE u.activo = :activo"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query
            = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByEsAdministrador", query
       = "SELECT u FROM Usuario u WHERE u.esAdministrador = :esAdministrador")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Long idUsuario, el id del Usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Long idUsuario;
    /**
     * String correo, el correo electrónico del Usuario.
     */
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    /**
     * String contrasenia, la contraseña del Usuario.
     */
    @Basic(optional = false)
    @Column(name = "contrasenia")
    private String contrasenia;
    /**
     * String nombre, el nombre del Usuario.
     */
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    /**
     * String app, el apellido paterno del Usuario.
     */
    @Basic(optional = false)
    @Column(name = "app")
    private String app;
    /**
     * String apm, el apellido materno del Usuario.
     */
    @Basic(optional = false)
    @Column(name = "apm")
    private String apm;
    /**
     * String activo, indica si un Usuario está activo o no.
     */
    @Basic(optional = false)
    @Column(name = "activo")
    private String activo;
    /**
     * String nombreUsuario, el nombre de usuario del Usuario.
     */
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    /**
     * Int esAdministrador, indica si un usuario es Administrador o no.
     */
    @Basic(optional = false)
    @Column(name = "esAdministrador")
    private int esAdministrador;
    /**
     * Usuario.
     */
    public Usuario() {
    }
    /**
     * Usuario.
     * @param pIdUsuario el id del usuario.
     */
    public Usuario(final Long pIdUsuario) {
        this.idUsuario = pIdUsuario;
    }
    /**
     * @param pIdUsuario el id del usuario
     * @param pCorreo el correo electrónico del usuario
     * @param pContrasenia la contraseña del usuario
     * @param pNombre el nombre del usuario
     * @param pApp el apellido paterno del usuario
     * @param pApm el apellido materno del usuario
     * @param pActivo indica si un usuario es activo o no
     * @param pNombreUsuario el nombre de usuario del usuario
     * @param pEsAdministrador inidica si un usuario es administrador o no
     */
    public Usuario(final Long pIdUsuario, final String pCorreo,
           final String pContrasenia, final String pNombre, final String pApp,
           final String pApm, final String pActivo, final String pNombreUsuario,
           final int pEsAdministrador) {
        this.idUsuario = pIdUsuario;
        this.correo = pCorreo;
        this.contrasenia = pContrasenia;
        this.nombre = pNombre;
        this.app = pApp;
        this.apm = pApm;
        this.activo = pActivo;
        this.nombreUsuario = pNombreUsuario;
        this.esAdministrador = pEsAdministrador;
    }
    /**
     * Regresa el id del Usuario.
     * @return idUsuario el id del Usuario.
     */
    public Long getIdUsuario() {
        return idUsuario;
    }
    /**
     * Modifica el id del Usuario.
     * @param idUsuario el nuevo id del Usuario.
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Regresa el correo electrónico del Usuario.
     * @return correo el correo electrónico del Usuario.
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Modifica el correo electrónico del Usuario.
     * @param correo el correo electrónico del Usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Regresa la contraseña del Usuario.
     * @return contrasenia la contraseña del Usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }
    /**
     * Modifica la contraseña del Usuario.
     * @param contrasenia la nueva contraseña del Usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    /**
     * Regresa el nombre del Usuario
     * @return nombre el nombre del Usuario.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre del Usuario.
     * @param nombre el nuevo nombre del Usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Regresa el apellido paterno del Usuario.
     * @return app el apellido paterno del Usuario.
     */
    public String getApp() {
        return app;
    }
    /**
     * Modifica el apellido paterno del Usuario.
     * @param app el nuevo apellido paterno del Usuario.
     */
    public void setApp(String app) {
        this.app = app;
    }
    /**
     * Regresa el apellido materno del Usuario.
     * @return apm el apellido materno del Usuario
     */
    public String getApm() {
        return apm;
    }
    /**Modifica el apellido materno del Usuario.
     * @param apm el nuevo apellido materno del Usuario.
     */
    public void setApm(String apm) {
        this.apm = apm;
    }
    /**
     * Regresa si un Usuario es activo o no.
     * @return activo el valor del atributo activo.
     */
    public String getActivo() {
        return activo;
    }
    /**
     * Modifica el atributo activo del Usuario.
     * @param activo el nuevo valor del atributo activo.
     */
    public void setActivo(String activo) {
        this.activo = activo;
    }
    /**
     * Regresa el nombre de usuario del Usuario.
     * @return nombreUsuario el nombre de usuario del Usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    /**
     * Modifica el nombre de usuario del Usuario.
     * @param nombreUsuario el nuevo nombre de usuario del Usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    /**
     * Regresa si un Usuario es administrador o no
     * @return esAdministrador el valor del atributo esAdministrador.
     */
    public int getEsAdministrador() {
        return esAdministrador;
    }
    /**
     * Modifica el atributo esAdministrador de un Usuario.
     * @param esAdministrador el nuevo valor del atributo esAdministrador.
     */
    public void setEsAdministrador(int esAdministrador) {
        this.esAdministrador = esAdministrador;
    }
    /**
     * @return hash.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }
    /**
     * Indica si un objeto es igual a otro.
     * @param object el objeto a comparar.
     * @return boolean.
     */
    @Override
    public final boolean equals(final Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null)
                || (this.idUsuario != null
                && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }
    /**
     * @return String.
     */
    @Override
    public final String toString() {
        return "com.kaab.proyecto.db.Usuario[ idUsuario=" + idUsuario + " ]";
    }
}
