create database masita;

use masita;

CREATE TABLE UsuarioCiencias (
	id_usuario 					int NOT NULL,
    contrasenia 				varchar(128) NOT NULL,
    nombre 						varchar(64) NOT NULL,
    app 						varchar(64) NOT NULL,
    apm 						varchar(64) NOT NULL,
    activo 						varchar(128) NOT NULL,
    nombre_usuario 				varchar(64) NOT NULL,
    correo 						varchar(128) NOT NULL,
    PRIMARY KEY(id_usuario)
) ENGINE=InnoBD DEFAULT CHARSET=utf8;