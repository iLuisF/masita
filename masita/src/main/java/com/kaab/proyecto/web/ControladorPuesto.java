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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Controlador para un Puesto.
 * @author Ernesto Palacios
 */
@ManagedBean
@ViewScoped
public class ControladorPuesto implements Serializable {
    /**
     * El id del puesto de comida.
     */
    private Long id;
    /**
     * Nombre del puesto de comida.
     */
    private String nombre;
    /**
     * La longitud del puesto de comida.
     */
    private String longitud;
    /**
     * La latitud del puesto de comida.
     */
    private String latitud;
    /**
     * El horario del puesto de comida.
     */
    private String horario;
    /**
     * Los servicios adicionales del puesto de comida.
     */
    private String[] servicios;
    /**
     * Los tipos de comida que vende el puesto de comida.
     */
    private String[] tipoComida;
    /**
     * Controlador para el puesto de comida.
     */
    private PuestoJpaController cpuesto;
    /**
     * Lista de los puestos de comida.
     */
    private List<Puesto> listaP;
    /**
     * Modelo avanzado para el puesto de comida.
     */
    private MapModel advancedModel;
    /**
     * Coordenadas del puesto de comida.
     */
    private LatLng coordenadas;
    /**
     * Límite norte del mapa.
     */
    static final double NORTE = -99.1778;
    /**
     * Límite sur en el mapa.
     */
    static final double SUR = 19.3225;
    /**
     * Límite este en el mapa.
     */
    static final double ESTE = -99.1824;
    /**
     * Límite oeste en el mapa.
     */
    static final double OESTE = 19.3251;
    /**
     * Indica en la base de datos que es comida para llevar.
     */
    static final long COMIDA_LLEVAR = 3L;
    /**
     * Identificador para el tipo de comida corrida.
     */
    static final long COMIDA_CORRIDA = 1L;
    /**
     * Identificador para el tipo de comida hamburguesas.
     */
    static final long HAMBURGUESAS = 2L;
    /**
     * Identificador para el tipo de comida ensaladas.
     */
    static final long ENSALADAS = 3L;
    /**
     * Identificador para el tipo de comida dulces.
     */
    static final long DULCES = 4L;
    /**
     * Identificador para el tipo de comida tortas.
     */
    static final long TORTAS = 5L;
    /**
     * Identificador para el tipo de comida garnachas.
     */
    static final long GARNACHAS = 6L;
    /**
     * Identificador para el tipo de comida hotdog.
     */
    static final long HOTDOG = 7L;
    /**
     * Identificador para el tipo de comida tacos.
     */
    static final long TACOS = 8L;
    /**
     * Identificador para el tipo de comida sushi.
     */
    static final long SUSHI = 9L;
    /**
     * Número 4.
     */
    static final int CUATRO = 4;
    /**
     * Número 3.
     */
    static final int TRES = 3;
    /**
     * Número 23, para el horario.
     */
    static final int HORA_23 = 23;
    /**
     * Número 59, para el horario.
     */
    static final int MIN_59 = 59;

    /**
     * Regresa el id de un puesto.
     * @return el id de un puesto
     */
    public final Long getId() {
        return id;
    }

    /**
     * Regresa un modelo del mapa.
     * @return un modelo del mapa.
     */
    public final MapModel getAdvancedModel() {
        return advancedModel;
    }

    /**
     * Modifica el modelo del mapa.
     * @param pAdvancedModel el nuevo modelo del mapa.
     */
    public final void setAdvancedModel(final MapModel pAdvancedModel) {
        this.advancedModel = pAdvancedModel;
    }

    /**
     * Cambia el id de un puesto.
     * @param pId el nuevo id de un puesto
     */
    public final void setId(final Long pId) {
        this.id = pId;
    }

    /**
     * Obtiene el nombre de un puesto.
     * @return el nombre de un puesto
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de un puesto.
     * @param pNombre el nuevo nombre de un puesto
     */
    public final void setNombre(final String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Obtiene la longitud de un puesto.
     * @return la longitud de un puesto
     */
    public final String getLongitud() {
        return longitud;
    }

    /**
     * Cambia la longitud de un puesto.
     * @param pLongitud la nueva longitud de un puesto
     */
    public final void setLongitud(final String pLongitud) {
        this.longitud = pLongitud;
    }

    /**
     * Obtiene la latitud de un puesto.
     * @return la latitud de un puesto
     */
    public final String getLatitud() {
        return latitud;
    }

    /**
     * Cambia la latitud de un puesto.
     * @param pLatitud la nueva latitud de un puesto
     */
    public final void setLatitud(final String pLatitud) {
        this.latitud = pLatitud;
    }

    /**
     * Obtiene el horario de un puesto.
     * @return el horario de un puesto
     */
    public final String getHorario() {
        return horario;
    }

    /**
     * Cambia el horario de un puesto.
     * @param pHorario el nuevo horario de un puesto
     */
    public final void setHorario(final String pHorario) {
        this.horario = pHorario;
    }

    /**
     * Obtiene los servicios adicionales de un puesto.
     * @return los servicios adicionales de un puesto
     */
    public final String[] getServicios() {
        return servicios;
    }

    /**
     * Cambia los servicios adicionales de un puesto.
     * @param pServicios los nuevos servicios adicionales de un puesto
     */
    public final void setServicios(final String[] pServicios) {
        this.servicios = pServicios;
    }

    /**
     * Regresa las coordenadas de un puesto.
     * @return las coordenadas de un puesto
     */
    public final LatLng getCoordenadas() {
        return coordenadas;
    }

    /**
     * Modifica las coordenadas de un puesto.
     * @param pCoordenadas las nuevas coordenadas de un puesto.
     */
    public  final void setCoordenadas(final LatLng pCoordenadas) {
        this.coordenadas = pCoordenadas;
    }

    /**
     * Obtiene el tipo de comida de un puesto.
     * @return el tipo de comida de un puesto
     */
    public final String[] getTipoComida() {
        return tipoComida;
    }

    /**
     * Cambia el tipo de comida de un puesto.
     * @param pTipoComida el nuevo tipo de comida de un puesto
     */
    public final void setTipoComida(final String[] pTipoComida) {
        this.tipoComida = pTipoComida;
    }

    /**
     * Crea una lista con todos los puestos.
     * @return una lista con todos los puestos
     */
    public final List<Puesto> crearListaPuesto() {
        listaP = new ArrayList<Puesto>();
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("MiProyectoPU");
        cpuesto = new PuestoJpaController(emf);
        List<Puesto> puestos = cpuesto.findPuestoEntities();
        for (Puesto actual : puestos) {
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
    public final void onPointSelect(final PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        this.setLatitud(String.valueOf(latlng.getLat()));
        this.setLongitud(String.valueOf(latlng.getLng()));
        advancedModel = new DefaultMapModel();
        advancedModel.addOverlay(new Marker(latlng, "puesto actual",
                "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
    }

    /**
     * Verifica que las coordenadas dadas, estén dentro de los límites de la
     * Facultad de Ciencias.
     * @return si las coordenadas están dentro de los límites
     */
    private boolean verificaCoordenadas() {
        FacesMessage mensaje;
        if (this.getLatitud() != null && this.getLongitud() != null) {
            double lon = Double.valueOf(this.getLongitud());
            double lat = Double.valueOf(this.getLatitud());
            if (lon > NORTE || lat < SUR || lon < ESTE
                    || lat > OESTE) {
                mensaje = new FacesMessage("Coordenas invalidas : no esta "
                        + "dentro de la Facultad de Ciencias");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                return false;
            } else {
                return true;
            }
        } else {
            mensaje = new FacesMessage("Coordenas invalidas : ingrese un "
                    + "punto en el mapa");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            return false;
        }
    }

    /**
     * Agrega un puesto a la base de datos.
     */
    public final void agregaPuesto() {
        Puesto actual = new Puesto(-1L, this.getNombre(), this.getHorario(),
                this.getLatitud(), this.getLongitud());
        FacesMessage mensaje;
        boolean condicion = this.validaNombre() && this.verificaCoordenadas()
                && this.horarioValido(this.getHorario().trim());
        if (condicion) {
            this.agregaServiciosAdicionales(actual);
            this.agregaTiposComida(actual);
            try {
                EntityManagerFactory emf =
                        Persistence.createEntityManagerFactory("MiProyectoPU");
                cpuesto = new PuestoJpaController(emf);
                cpuesto.create(actual);
                mensaje = new FacesMessage("Puesto Agregado Correctamente");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/masita/PerfilPuestoIH"
                        + ".xhtml?idPuesto=" + actual.getIdPuesto());
            } catch (IOException ex) {
               System.out.println("Excepción: Agregar Puesto");
            }
        }
    }

    /**
     * Valida que el nombre del puesto no exista en la base de datos.
     * @return si el nombre del puesto no existe en la base de datos
     */
    private boolean validaNombre() {
        FacesMessage mensaje;
        boolean condicion = true;
        List<Puesto> aux = this.crearListaPuesto();
        for (Puesto x : aux) {
            if (x.getNombre().equals(this.getNombre())
                    && !Objects.equals(this.getId(), x.getIdPuesto())) {
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
    private void agregaServiciosAdicionales(final Puesto p) {
        List<ServicioAdicional> servi = new ArrayList<ServicioAdicional>();
        ServicioAdicional servicioActual = null;
       for (String servicio : this.getServicios()) {
            if (servicio.equals("Mesas")) {
                servicioActual = new ServicioAdicional(1L, "Mesas");
            }
            if (servicio.equals("Baños")) {
                servicioActual = new ServicioAdicional(2L, "Baños");
            }
            if (servicio.equals("Comida para llevar")) {
                servicioActual = new ServicioAdicional(COMIDA_LLEVAR, "Comida "
                        + "para llevar");
            }
            servi.add(servicioActual);
        }
        p.setServicioAdicionalLista(servi);
    }

    /**
     * Agrega los tipos de comida a un puesto.
     * @param p el puesto
     */
    private void agregaTiposComida(final Puesto p) {
        List<TipoComida> tipos = new ArrayList<TipoComida>();
        TipoComida actual = null;
        for (String tipo : this.getTipoComida()) {
            if (tipo.equals("Comida corrida")) {
                actual = new TipoComida(COMIDA_CORRIDA, "Comida corrida");
            }
            if (tipo.equals("Hamburguesas")) {
               actual = new TipoComida(HAMBURGUESAS, "Hamburguesas");
            }
            if (tipo.equals("Ensaladas")) {
                actual = new TipoComida(ENSALADAS, "Ensaladas");
            }
            if (tipo.equals("Dulces")) {
                actual = new TipoComida(DULCES, "Dulces");
            }
            if (tipo.equals("Tortas")) {
                actual = new TipoComida(TORTAS, "Tortas");
            }
            if (tipo.equals("Garnachas")) {
                 actual = new TipoComida(GARNACHAS, "Garnachas");
            }
            if (tipo.equals("Hotdog")) {
                actual = new TipoComida(HOTDOG, "Hotdog");
            }
            if (tipo.equals("Tacos")) {
                actual = new TipoComida(TACOS, "Tacos");
            }
            if (tipo.equals("Sushi")) {
                 actual = new TipoComida(SUSHI, "Sushi");
            }

            tipos.add(actual);
        }
        p.setTipoComidaCollection(tipos);
    }

    /**
     * Manda eliminar un puesto.
     * @param idPuesto el id del puesto a eliminar
     */
    public final void mandaEliminar(final Long idPuesto) {
        this.setId(idPuesto);
    }

    /**
     * Elimina un puesto de la base de datos.
     */
    public final void elimina() {
        try {
            EntityManagerFactory emf
                    = Persistence.createEntityManagerFactory("MiProyectoPU");
            cpuesto = new PuestoJpaController(emf);
            cpuesto.destroy(this.getId());
            FacesMessage msg = new FacesMessage("Puesto Eliminado",
                    Long.toString(this.getId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("/masita/EliminaPuestoIH"
                    + ".xhtml");
        } catch (IOException ex) {
            System.out.println("Excepción: Eliminar Puesto");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPuesto.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * Manda editar un puesto.
     * @param idPuesto el id del puesto a editar
     */
    public final void mandaEditar(final Long idPuesto) {
        Puesto nvoPuesto = new Puesto(idPuesto);
        Puesto actual = this.busca(nvoPuesto);
        this.setId(idPuesto);
        this.setNombre(actual.getNombre());
        this.setHorario(actual.getHorario());
        this.setLatitud(actual.getLatitud());
        this.setLongitud(actual.getLongitud());
        int i = 0;
        this.setServicios(
                new String[actual.getServicioAdicionalLista().size()]);
        this.setTipoComida(new String[actual.getTipoComidaCollection().size()]);
        for (ServicioAdicional x : actual.getServicioAdicionalLista()) {
            this.getServicios()[i] = x.getNombre();
            i++;
        }
        i = 0;
        for (TipoComida x : actual.getTipoComidaCollection()) {
            this.getTipoComida()[i] = x.getNombre();
            i++;
        }
        this.setAdvancedModel(new DefaultMapModel());
        this.setCoordenadas(new LatLng(Double.valueOf(this.getLatitud()),
                Double.valueOf(this.getLongitud())));
        this.getAdvancedModel().addOverlay(new Marker(this.getCoordenadas(),
                "puesto actual", "http://maps.google.com/mapfiles/ms/micons"
                        + "/blue-dot.png"));
    }

    /**
     * Verifica que los campos no estén vacíos.
     * @return si los campos no están vacíos
     */
    private boolean verificaCampos() {
       boolean condicion = true;
       FacesMessage mensaje;
       if (this.getNombre().trim().equals("")) {
           mensaje = new FacesMessage("Nombre vacio se necesita el nombre del "
                   + "puesto para continuar");
           FacesContext.getCurrentInstance().addMessage(null, mensaje);
           condicion = false;
       }
       if (this.getHorario().trim().equals("")) {
           mensaje = new FacesMessage("Horario vacio se necesita el nombre del "
                   + "puesto para continuar");
           FacesContext.getCurrentInstance().addMessage(null, mensaje);
           condicion = false;
       } else {
            condicion = this.horarioValido(this.getHorario());
       }
       return condicion;
    }

    /**
     * Revisa que el horario dado, tenga un formato válido.
     * @param pHorario el horario que se ingresa.
     * @return si el horario es válido
     */
    private boolean horarioValido(final String pHorario) {
        String[] partes = pHorario.split("-");
        String[] parteDer = partes[0].split(":");
        String[] parteIzq = partes[1].split(":");
        FacesMessage mensaje;
        int[] numeros = new int[CUATRO];
        for (int i = 0; i < CUATRO; i++) {
            if (i < 2) {
                numeros[i] = Integer.valueOf(parteIzq[i]);
            } else {
                numeros[i] = Integer.valueOf(parteDer[i - 2]);
            }
        }
        boolean condicion = true;
        if (numeros[0] > HORA_23 || numeros[2] > HORA_23) {
            mensaje = new FacesMessage("Horario invalido: la hora esperada "
                    + "es menor a 24");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            condicion = false;
        } else {
            if (numeros[1] > MIN_59 || numeros[TRES] > MIN_59) {
                mensaje = new FacesMessage("Horario invalido: los minutos "
                        + "esperados son menores a 60");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                condicion = false;
            }
        }
        return condicion;
    }

    /**
     * Edita un puesto en la base de datos.
     */
    public final void edita() {
        boolean condicion = this.verificaCampos() && this.validaNombre()
                && this.verificaCoordenadas();
        FacesMessage mensaje;
        if (condicion) {
            try {
                Puesto actual = new Puesto(this.getId());
                actual.setNombre(this.getNombre());
                actual.setHorario(this.getHorario());
                actual.setLatitud(this.getLatitud());
                actual.setLongitud(this.getLongitud());
                this.agregaServiciosAdicionales(actual);
                this.agregaTiposComida(actual);
                EntityManagerFactory emf =
                        Persistence.createEntityManagerFactory("MiProyectoPU");
                cpuesto = new PuestoJpaController(emf);
                cpuesto.edit(actual);
                mensaje = new FacesMessage("Puesto Editado Correctamente");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/masita/EliminaPuestoIH"
                        + ".xhtml");
            } catch (Exception ex) {
                Logger.getLogger(ControladorPuesto.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Busca el puesto actual en la base de datos.
     * @param actual el puesto a buscar
     * @return el puesto que se busca
     */
    private Puesto busca(final Puesto actual) {
        List<Puesto> actuales = this.crearListaPuesto();
        for (Puesto x : actuales) {
            if (Objects.equals(actual.getIdPuesto(), x.getIdPuesto())) {
               return x;
            }
        }
        return null;
    }
}