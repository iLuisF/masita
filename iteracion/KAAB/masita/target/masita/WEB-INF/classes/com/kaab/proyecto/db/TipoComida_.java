package com.kaab.proyecto.db;

import com.kaab.proyecto.db.Puesto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-07T06:16:46")
@StaticMetamodel(TipoComida.class)
public class TipoComida_ { 

    public static volatile CollectionAttribute<TipoComida, Puesto> puestoCollection;
    public static volatile SingularAttribute<TipoComida, Long> idTipoComida;
    public static volatile SingularAttribute<TipoComida, String> nombre;

}