CREATE DATABASE Proyecto_Comida;
GO
USE Proyecto_Comida
GO
CREATE SCHEMA Masita;
GO
CREATE TABLE Proyecto_Comida.Masita.Puesto(
	nombrePuesto 		varchar(255) primary key,
	tipoComida 			varchar(255),
	calificacion 		tinyint,
	coordenadaX 		numeric (10,8),
	coordanadaY			numeric(10,8),
	horario				varchar(50),
	tiene_mesas 		bit not null, -- 0 para no , 1 para si	
	tiene_banios		bit not null -- 0 para no , 1 para si
);

ALTER TABLE Masita.Puesto add CONSTRAINT ck_horario CHECK (horario LIKE '[0-9][0-9][:][0-9][0-9][-][0-9][0-9][:][0-9][0-9]');
ALTER TABLE Masita.Puesto add CONSTRAINT ck_calificacion CHECK (calificacion > 0 AND calificacion < 5);