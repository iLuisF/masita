package com.kaab.proyecto.db;

import com.kaab.proyecto.db.Puesto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-14T22:16:30")
@StaticMetamodel(ServicioAdicional.class)
public class ServicioAdicional_ { 

    public static volatile CollectionAttribute<ServicioAdicional, Puesto> puestoCollection;
    public static volatile SingularAttribute<ServicioAdicional, Long> idServicio;
    public static volatile SingularAttribute<ServicioAdicional, String> nombre;

}