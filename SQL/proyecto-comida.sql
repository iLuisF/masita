--
-- Crear base de datos: masita
-- create database masita;
-- ejecutar este script con
-- source proyecto-comida.sql

use masita;

drop table if exists Usuario;
drop table if exists Puesto;
drop table if exists Comentario;
drop table if exists TipoComida;
drop table if exists ServicioAdicional;
drop table if exists TipoComidaPuesto;
drop table if exists ServicioAdicionalPuesto;

--
-- Estructura para la tabla Usuario
--
CREATE TABLE Usuario (
  idUsuario       serial PRIMARY KEY,
  correo          varchar(128) NOT NULL,
  contrasenia     varchar(128) NOT NULL,
  nombre          varchar(64) NOT NULL,
  app             varchar(64) NOT NULL,
  apm             varchar(64) NOT NULL,
  activo          varchar(128) NOT NULL,
  nombreUsuario   varchar(64) NOT NULL,
  esAdministrador int(1) NOT NULL
) ENGINE=InnoDB default charset=utf8;

--
-- Estructura para la tabla Puesto
--
CREATE TABLE Puesto (
  idPuesto      serial PRIMARY KEY,
  nombre        varchar(64) NOT NULL,
  horario       varchar(16) NOT NULL,
  latitud		varchar(20) NOT NULL,
  longitud		varchar(20) NOT NULL
) ENGINE=InnoDB default charset=utf8;

--
-- Estructura para la tabla Comentario
--
CREATE TABLE Comentario (
  idComentario    serial PRIMARY KEY,
  idPuesto        bigint(20) unsigned NOT NULL,
  idUsuario       bigint(20) unsigned NOT NULL,
  contenido       longtext,
  fecha           date NOT NULL,
  calificacion    int(11)
) ENGINE=InnoDB default charset=utf8;

--
-- Estructura para la tabla TipoComida
--
CREATE TABLE TipoComida (
  idTipoComida  serial PRIMARY KEY,
  nombre        varchar(64) NOT NULL
) ENGINE=InnoDB default charset=utf8;

--
-- Estructura para la tabla ServicioAdicional
--
CREATE TABLE ServicioAdicional (
  idServicio    serial PRIMARY KEY,
  nombre        varchar(64) NOT NULL
) ENGINE=InnoDB default charset=utf8;

--
-- Estructura para la tabla TipoComidaPuesto
--
CREATE TABLE TipoComidaPuesto (
  idTipoComida  bigint(20) unsigned NOT NULL,
  idPuesto      bigint(20) unsigned NOT NULL,
  PRIMARY KEY (idTipoComida, idPuesto)
) ENGINE=InnoDB default charset=utf8;

--
-- Estructura para la tabla ServicioPuesto
--
CREATE TABLE ServicioAdicionalPuesto (
  idServicio    bigint(20) unsigned NOT NULL,
  idPuesto      bigint(20) unsigned NOT NULL,
  PRIMARY KEY (idServicio, idPuesto)
) ENGINE=InnoDB default charset=utf8;

-- ---------------------------------------------

--
-- Indices de la tabla Usuario
--
ALTER TABLE Usuario ADD UNIQUE KEY correo (correo);

--
-- Indices de la tabla Comentario
--
ALTER TABLE Comentario ADD CONSTRAINT fk_Comentario_idPuesto FOREIGN KEY (idPuesto) REFERENCES Puesto (idPuesto) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE Comentario ADD CONSTRAINT fk_Comentario_idUsuario FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Indices de la tabla TipoComidaPuesto
--
ALTER TABLE TipoComidaPuesto ADD CONSTRAINT fk_TipoComidaPuesto_idTipoComida FOREIGN KEY (idTipoComida) REFERENCES TipoComida (idTipoComida) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE TipoComidaPuesto ADD CONSTRAINT fk_TipoComidaPuesto_idPuesto FOREIGN KEY (idPuesto) REFERENCES Puesto (idPuesto) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Indices de la tabla ServicioAdicionalPuesto
--
ALTER TABLE ServicioAdicionalPuesto ADD CONSTRAINT fk_ServicioAdicionalPuesto_idServicio FOREIGN KEY (idServicio) REFERENCES ServicioAdicional (idServicio) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE ServicioAdicionalPuesto ADD CONSTRAINT fk_ServicioAdicionalPuesto_idPuesto FOREIGN KEY (idPuesto) REFERENCES Puesto (idPuesto) ON DELETE CASCADE ON UPDATE CASCADE;
