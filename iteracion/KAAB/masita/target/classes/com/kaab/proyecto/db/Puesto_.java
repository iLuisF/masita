package com.kaab.proyecto.db;

import com.kaab.proyecto.db.ServicioAdicional;
import com.kaab.proyecto.db.TipoComida;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-07T06:16:46")
@StaticMetamodel(Puesto.class)
public class Puesto_ { 

    public static volatile SingularAttribute<Puesto, String> latitud;
    public static volatile SingularAttribute<Puesto, String> longitud;
    public static volatile SingularAttribute<Puesto, String> horario;
    public static volatile CollectionAttribute<Puesto, TipoComida> tipoComidaCollection;
    public static volatile SingularAttribute<Puesto, Long> idPuesto;
    public static volatile CollectionAttribute<Puesto, ServicioAdicional> servicioAdicionalCollection;
    public static volatile SingularAttribute<Puesto, String> nombre;

}