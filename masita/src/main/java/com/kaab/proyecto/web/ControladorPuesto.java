/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;

import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.ServicioAdicional;
import com.kaab.proyecto.db.TipoComida;
import com.kaab.proyecto.db.controller.PuestoJpaController;
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;

/**
 * Controlador para un Puesto.
 * @author Ernesto Palacios
 */
@ManagedBean
@ViewScoped
public class ControladorPuesto implements Serializable{
    private Long id;
    private String nombre;
    private String longitud;
    private String latitud;
    private String horario;
    private String[] servicios;
    private String[] tipoComida;
    private PuestoJpaController cpuesto;
    private List<Puesto> listaP;

    /**
     * Regresa el id de un puesto.
     * @return el id de un puesto
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id de un puesto.
     * @param id el nuevo id de un puesto
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de un puesto.
     * @return el nombre de un puesto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de un puesto.
     * @param nombre el nuevo nombre de un puesto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la longitud de un puesto.
     * @return la longitud de un puesto
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * Cambia la longitud de un puesto.
     * @param longitud la nueva longitud de un puesto
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * Obtiene la latitud de un puesto.
     * @return la latitud de un puesto
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * Cambia la latitud de un puesto
     * @param latitud la nueva latitud de un puesto
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * Obtiene el horario de un puesto.
     * @return el horario de un puesto
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Cambia el horario de un puesto
     * @param horario el nuevo horario de un puesto
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * Obtiene los servicios adicionales de un puesto.
     * @return los servicios adicionales de un puesto
     */
    public String[] getServicios() {
        return servicios;
    }

    /**
     * Cambia los servicios adicionales de un puesto.
     * @param servicios los nuevos servicios adicionales de un puesto
     */
    public void setServicios(String[] servicios) {
        this.servicios = servicios;
    }

    /**
     * Obtiene el tipo de comida de un puesto.
     * @return el tipo de comida de un puesto
     */
    public String[] getTipoComida() {
        return tipoComida;
    }

    /**
     * Cambia el tipo de comida de un puesto.
     * @param tipoComida el nuevo tipo de comida de un puesto
     */
    public void setTipoComida(String[] tipoComida) {
        this.tipoComida = tipoComida;
    }
    
    /**
     * Crea una lista con todos los puestos.
     * @return una lista con todos los puestos
     */
    public List<Puesto> crearListaPuesto(){
        listaP = new ArrayList<Puesto>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
        cpuesto = new PuestoJpaController(emf);
        List<Puesto> puestos = cpuesto.findPuestoEntities();
        
        for(Puesto actual : puestos){
            Puesto nvo = new Puesto(actual.getIdPuesto());
            nvo.setIdPuesto(actual.getIdPuesto());
            nvo.setNombre(actual.getNombre());
            nvo.setTipoComidaCollection(actual.getTipoComidaCollection());
            nvo.setHorario(actual.getHorario());
            nvo.setLatitud(actual.getLatitud());
            nvo.setLongitud(actual.getLongitud());
            nvo.setServicioAdicionalLista(actual.getServicioAdicionalLista());
            nvo.setTipoComidaCollection(actual.getTipoComidaCollection());
            listaP.add(nvo);
        }     
        return listaP;
    }
    
    /**
     * Selecciona un punto en el mapa, dado el evento.
     * @param event el evento al dar click en el mapa
     */
    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        this.setLatitud(String.valueOf(latlng.getLat()));
        this.setLongitud(String.valueOf(latlng.getLng()));
    }
    
    /**
     * Verifica que las coordenadas dadas, estén dentro de los límites de la
     * Facultad de Ciencias.
     * @return si las coordenadas están dentro de los límites
     */
    private boolean verificaCoordenadas(){
        FacesMessage mensaje;
        if(this.getLatitud() != null && this.getLongitud() != null){
                double lon = Double.valueOf(this.getLongitud());
                double lat = Double.valueOf(this.getLatitud());
                if(lon > -99.1778 || lat < 19.3225 || lon < -99.1824 || lat > 19.3251){
                    mensaje = new FacesMessage("Coordenas invalidas : no esta dentro de la Facultad de Ciencias");
                   FacesContext.getCurrentInstance().addMessage(null, mensaje);
                   return false;
                }else{
                    return true;
                }
               
        }else{
               mensaje = new FacesMessage("Coordenas invalidas : ingrese un punto en el mapa");
               FacesContext.getCurrentInstance().addMessage(null, mensaje);
               return false;
        }
    }
    
    /**
     * Agrega un puesto a la base de datos.
     */
    public void AgregaPuesto(){
        Puesto actual = new Puesto(-1l,this.getNombre(), this.getHorario() , this.getLatitud() , this.getLongitud());
        FacesMessage mensaje;
        boolean condicion = this.ValidaNombre() && this.verificaCoordenadas();
        
        if(condicion){
            this.agregaServiciosAdicionales(actual);
            this.agregaTiposComida(actual);
            try {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
                cpuesto = new PuestoJpaController(emf);
                cpuesto.create(actual);
                mensaje = new FacesMessage("Puesto Agregado Correctamente");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                FacesContext context= FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/masita/EliminaPuestoIH.xhtml");
            } catch (IOException ex) {
               ;
            }
        }
    }
    
    /**
     * Valida que el nombre del puesto no exista en la base de datos.
     * @return si el nombre del puesto no existe en la base de datos
     */
    private boolean ValidaNombre(){
        FacesMessage mensaje;
        boolean condicion = true; 
        List<Puesto> aux = this.crearListaPuesto();
        for(Puesto x : aux){
            if(x.getNombre().equals(this.getNombre())  && !Objects.equals(this.getId(), x.getIdPuesto())){
                mensaje = new FacesMessage("nombre del puesto duplicado");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                condicion = false;
            }
        }
        return condicion;
    }
    
    /**
     * Agrega los servicios adicionales a un puesto.
     * @param p el puesto
     */
    private void agregaServiciosAdicionales(Puesto p){
        List<ServicioAdicional> servi = new ArrayList<ServicioAdicional>();
        ServicioAdicional serv_actual = null;
        for (String servicio : this.getServicios()) {
            if (servicio.equals("Mesas")) {
                serv_actual = new ServicioAdicional(1l,"Mesas");
            }
            if (servicio.equals("Baños")) {
                serv_actual = new ServicioAdicional(2l,"Baños");
            }
            if (servicio.equals("Comida para llevar")) {
                serv_actual = new ServicioAdicional(3l,"Comida para llevar");
            }
            System.out.println("el nombre es :" + serv_actual.getNombre());
            servi.add(serv_actual);
        }
        p.setServicioAdicionalLista(servi);
    }
    
    /**
     * Agrega los tipos de comida a un puesto
     * @param p el puesto
     */
    private void agregaTiposComida(Puesto p){
        List<TipoComida> tipos = new ArrayList<TipoComida>();
        TipoComida actual = null;
        for(String tipo : this.getTipoComida()){
            if(tipo.equals("Comida corrida")){
                actual = new TipoComida(1l,"Comida corrida");
            }
            if(tipo.equals("Hamburguesas")){
               actual = new TipoComida(2l,"Hamburguesas");
            }
            if(tipo.equals("Ensaladas")){
                actual = new TipoComida(3l,"Ensaladas");
            }
            if(tipo.equals("Dulces")){
                actual = new TipoComida(4l,"Dulces");
            }
            if(tipo.equals("Tortas")){
                actual = new TipoComida(5l,"Tortas");
            }
            if(tipo.equals("Garnachas")){
                 actual = new TipoComida(6l,"Garnachas");
            }
            if(tipo.equals("Hotdog")){
                actual = new TipoComida(7l,"Hotdog");
            }
            if(tipo.equals("Tacos")){
                actual = new TipoComida(8l,"Tacos");
            }
            if(tipo.equals("Sushi")){
                 actual = new TipoComida(9l,"Sushi");
            }

            tipos.add(actual);
        }
        p.setTipoComidaCollection(tipos);
    }
    
    /**
     * Manda eliminar un puesto.
     * @param idPuesto el id del puesto a eliminar
     */
    public void MandaEliminar(Long idPuesto){
        this.setId(idPuesto);
    }
    
    /**
     * Elimina un puesto de la base de datos.
     */
    public void elimina(){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
            cpuesto = new PuestoJpaController(emf);
            cpuesto.destroy(this.getId());
            FacesMessage msg = new FacesMessage("Puesto Eliminado", Long.toString(this.getId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context= FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/masita/EliminaPuestoIH.xhtml");
        } catch (IOException ex) {
            ;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Manda editar un puesto.
     * @param idPuesto el id del puesto a editar
     */
    public void MandaEditar(Long idPuesto){
        Puesto nvoPuesto = new Puesto (idPuesto);
        Puesto actual = this.busca(nvoPuesto);
        this.setId(idPuesto);
        this.setNombre(actual.getNombre());
        this.setHorario(actual.getHorario());
        this.setLatitud(actual.getLatitud());
        this.setLongitud(actual.getLongitud());
        int i = 0;
        this.setServicios(new String[actual.getServicioAdicionalLista().size()]);
        this.setTipoComida(new String[actual.getTipoComidaCollection().size()]);
        for(ServicioAdicional x : actual.getServicioAdicionalLista()){
            this.getServicios()[i] = x.getNombre();
            i++;
        }
        i = 0;
        for(TipoComida x : actual.getTipoComidaCollection()){
            this.getTipoComida()[i] = x.getNombre();
            i++;
        }
    }
    
    /**
     * Verifica que los campos no estén vacíos.
     * @return si los campos no están vacíos
     */
    private boolean VerificaCampos(){
       boolean condicion = true;
       FacesMessage mensaje;
       if(this.getNombre().trim().equals("")){
           mensaje = new FacesMessage("Nombre vacio se necesita el nombre del puesto para continuar");
           FacesContext.getCurrentInstance().addMessage(null, mensaje);
           condicion = false;
       }
       
       if(this.getHorario().trim().equals("")){
           mensaje = new FacesMessage("Horario vacio se necesita el nombre del puesto para continuar");
           FacesContext.getCurrentInstance().addMessage(null, mensaje);
           condicion = false;
       }
       return condicion;
    }
    
    /**
     * Edita un puesto en la base de datos.
     */
    public void edita(){
        
        boolean condicion = this.VerificaCampos() && this.ValidaNombre() && this.verificaCoordenadas();
        FacesMessage mensaje;
        if(condicion){
            System.out.println(this.getNombre());
            try {
                Puesto actual = new Puesto(this.getId());
                actual.setNombre(this.getNombre());
                actual.setHorario(this.getHorario());
                actual.setLatitud(this.getLatitud());
                actual.setLongitud(this.getLongitud());
                this.agregaServiciosAdicionales(actual);
                this.agregaTiposComida(actual);
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiProyectoPU");
                cpuesto = new PuestoJpaController(emf);
                cpuesto.edit(actual);
                mensaje = new FacesMessage("Puesto Editado Correctamente");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                FacesContext context= FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/masita/EliminaPuestoIH.xhtml");
            } catch (Exception ex) {
                Logger.getLogger(ControladorPuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Busca el puesto actual en la base de datos.
     * @param actual el puesto a buscar
     * @return el puesto que se busca
     */
    private Puesto busca(Puesto actual){
        List<Puesto> actuales = this.crearListaPuesto(); 
        for(Puesto x : actuales){
            if(Objects.equals(actual.getIdPuesto(), x.getIdPuesto())){
               return x;
            }
        }
        return null;
    }
    
}