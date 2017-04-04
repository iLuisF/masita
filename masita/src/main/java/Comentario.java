
import java.util.Date;


/**
 *
 * @author Flores González Luis.
 * @version 1.0 - Abril del 2017
 */
public class Comentario {
    
    private UsuarioCiencias usuario;
    private String contenido;
    private int calificacion;
    private Date fecha;

    public UsuarioCiencias getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCiencias usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
