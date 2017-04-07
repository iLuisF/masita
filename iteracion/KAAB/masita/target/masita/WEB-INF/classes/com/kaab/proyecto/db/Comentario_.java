package com.kaab.proyecto.db;

import com.kaab.proyecto.db.Puesto;
import com.kaab.proyecto.db.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-07T06:16:46")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, Date> fecha;
    public static volatile SingularAttribute<Comentario, String> contenido;
    public static volatile SingularAttribute<Comentario, Integer> calificacion;
    public static volatile SingularAttribute<Comentario, Puesto> idPuesto;
    public static volatile SingularAttribute<Comentario, Usuario> idUsuario;
    public static volatile SingularAttribute<Comentario, Long> idComentario;

}