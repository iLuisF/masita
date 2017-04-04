
/**
 *
 * @author Flores González Luis.
 * @version 1.0 - Abril del 2017
 */
public class Puesto {
    
    private String nombrePuesto;
    private String tipoComida;
    private float calificacion;
    private double[] coordenadaUbicacion;
    private String horario;
    private boolean tieneMesas;
    private boolean tieneBanio;

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public double[] getCoordenadaUbicacion() {
        return coordenadaUbicacion;
    }

    public void setCoordenadaUbicacion(double[] coordenadaUbicacion) {
        this.coordenadaUbicacion = coordenadaUbicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isTieneMesas() {
        return tieneMesas;
    }

    public void setTieneMesas(boolean tieneMesas) {
        this.tieneMesas = tieneMesas;
    }

    public boolean isTieneBanio() {
        return tieneBanio;
    }

    public void setTieneBanio(boolean tieneBanio) {
        this.tieneBanio = tieneBanio;
    }
    
    
    
}
