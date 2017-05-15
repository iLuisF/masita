package com.kaab.proyecto.db;

import com.kaab.proyecto.db.ServicioAdicional;
import com.kaab.proyecto.db.TipoComida;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-14T22:16:30")
@StaticMetamodel(Puesto.class)
public class Puesto_ { 

    public static volatile SingularAttribute<Puesto, String> latitud;
    public static volatile SingularAttribute<Puesto, String> longitud;
    public static volatile SingularAttribute<Puesto, String> horario;
    public static volatile ListAttribute<Puesto, ServicioAdicional> servicioAdicionalLista;
    public static volatile ListAttribute<Puesto, TipoComida> tipoComidaCollection;
    public static volatile SingularAttribute<Puesto, Long> idPuesto;
    public static volatile SingularAttribute<Puesto, String> nombre;

}