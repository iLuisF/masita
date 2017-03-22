
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de datos: `Masita`
--
create database masita;

use masita;

-- --------------------------------------------------------

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
) ; ENGINE=InnoBD DEFAULT CHARSET=utf8;


--
-- Estructura de tabla para la tabla `Comentario`
--

CREATE TABLE `Comentario` (
  `idComentario` int(11) NOT NULL,
  `idPuesto` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `contenido` varchar(300) NOT NULL,
  `fecha` date NOT NULL,
  `calificacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Puesto`
--

CREATE TABLE `Puesto` (
  `idPuesto` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `tipoComida` int(11) NOT NULL,
  `horario` varchar(16) NOT NULL,
  `ubicacion` varchar(64) NOT NULL,
  `idServicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ServicioAdicional`
--

CREATE TABLE `ServicioAdicional` (
  `idServicio` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TipoComida`
--

CREATE TABLE `TipoComida` (
  `idTipoComida` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Comentario`
--
ALTER TABLE `Comentario`
  ADD PRIMARY KEY (`idComentario`);

--
-- Indices de la tabla `Puesto`
--
ALTER TABLE `Puesto`
  ADD PRIMARY KEY (`idPuesto`);

--
-- Indices de la tabla `ServicioAdicional`
--
ALTER TABLE `ServicioAdicional`
  ADD PRIMARY KEY (`idServicio`);

--
-- Indices de la tabla `TipoComida`
--
ALTER TABLE `TipoComida`
  ADD PRIMARY KEY (`idTipoComida`);

--
-- Indices de la tabla `UsuarioCiencias`
--
ALTER TABLE `UsuarioCiencias`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Comentario`
--
ALTER TABLE `Comentario`
  MODIFY `idComentario` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Puesto`
--
ALTER TABLE `Puesto`
  MODIFY `idPuesto` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ServicioAdicional`
--
ALTER TABLE `ServicioAdicional`
  MODIFY `idServicio` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `TipoComida`
--
ALTER TABLE `TipoComida`
  MODIFY `idTipoComida` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `UsuarioCiencias`
--
ALTER TABLE `UsuarioCiencias`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT;