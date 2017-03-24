--
-- Base de datos: masita
--
create database masita;

use masita;
--
-- Estructura para la tabla Usuario
--
CREATE TABLE Usuario (
  idUsuario       int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  correo          varchar(128) NOT NULL,
  contrasenia     varchar(128) NOT NULL,
  nombre          varchar(64) NOT NULL,
  app             varchar(64) NOT NULL,
  apm             varchar(64) NOT NULL,
  activo          varchar(128) NOT NULL,
  nombreUsuario   varchar(64) NOT NULL,
  esAdministrador int(1) NOT NULL
);

--
-- Estructura para la tabla Puesto
--
CREATE TABLE Puesto (
  idPuesto      int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre        varchar(64) NOT NULL,
  horario       varchar(16) NOT NULL,
  ubicacion     varchar(64) NOT NULL
);

--
-- Estructura para la tabla Comentario
--
CREATE TABLE Comentario (
  idComentario    int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  idPuesto        int NOT NULL,
  idUsuario       int NOT NULL,
  contenido       varchar(300),
  fecha           date NOT NULL,
  calificacion    int(11)
);

--
-- Estructura para la tabla TipoComida
--
CREATE TABLE TipoComida (
  idTipoComida  int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre        varchar(64) NOT NULL
);

--
-- Estructura para la tabla ServicioAdicional
--
CREATE TABLE ServicioAdicional (
  idServicio    int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre        varchar(64) NOT NULL
);

--
-- Estructura para la tabla TipoComidaPuesto
--
CREATE TABLE TipoComidaPuesto (
  idTipoComida  int NOT NULL,
  idPuesto      int NOT NULL,
  PRIMARY KEY (idTipoComida, idPuesto)
);

--
-- Estructura para la tabla ServicioPuesto
--
CREATE TABLE ServicioAdicionalPuesto (
  idServicio    int NOT NULL,
  idPuesto      int NOT NULL,
  PRIMARY KEY (idServicio, idPuesto)
);

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
