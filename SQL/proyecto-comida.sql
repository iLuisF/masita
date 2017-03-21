create database masita;

use masita;

CREATE TABLE UsuarioCiencias (
	id_usuario int NOT NULL,
    contrasenia varchar(128) NOT NULL,
    nombre varchar(64) NOT NULL,
    app varchar(64) NOT NULL,
    apm varchar(64) NOT NULL,
    activo varchar(128) NOT NULL,
    nombre_usuario varchar(64) NOT NULL,
    correo varchar(128) NOT NULL,
    PRIMARY KEY(id_usuario)
) ENGINE=InnoBD DEFAULT CHARSET=utf8;

CREATE TABLE Comentario (
	id_comentario int(11) NOT NULL,    
    FOREIGN KEY (id_puesto) REFERENCES Puesto(id_puesto),    
    FOREIGN KEY (id_usuario) REFERENCES UsuarioCiencias(id_usuario),
    contenido varchar(300) NOT NULL,
    calificacion int(11) NOT NULL,
    fecha date NOT NULL,
    PRIMARY KEY(id_comentario)
) ENGINE=InnoBD DEFAULT CHARSET=utf8;

CREATE TABLE Puesto (
	id_puesto int(11) NOT NULL,
    FOREIGN KEY (id_servicio) REFERENCES ServicioAdicional(id_servicio),
    ubicacion varchar(64) NOT NULL,
    horario varchar(16) NOT NULL,
    nombre varchar(64) NOT NULL,
    PRIMARY KEY(id_puesto)
) ENGINE=InnoBD DEFAULT CHARSET=utf8;

CREATE TABLE ServicioAdicional(
	id_servicio int(11) NOT NULL,
    nombre varchar(64) NOT NULL,
    PRIMARY KEY(id_servicio)
) ENGINE=InnoBD DEFAULT CHARSET=utf8;

CREATE TABLE TipoComida(
	id_tipo_comida int(11) NOT NULL,
    nombre varchar(64) NOT NULL,
    PRIMARY KEY(id_tipo_comida)
) ENGINE=InnoBD DEFAULT CHARSET=utf8;