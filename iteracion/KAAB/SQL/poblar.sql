-- Tuplas para probar la base de datos

-- Usuarios
INSERT INTO Usuario (correo, contrasenia, nombre, app, apm, activo, nombreUsuario, esAdministrador) VALUES ('mayra@ciencias.unam.mx', 'password1', 'Mayra', 'Aceves', 'Higareda', 1, 'mayrica', 1);
INSERT INTO Usuario (correo, contrasenia, nombre, app, apm, activo, nombreUsuario, esAdministrador) VALUES ('aylin@ciencias.unam.mx', 'password2', 'Aylin', 'Huerta', 'González', 1, 'aylinhg', 1);
INSERT INTO Usuario (correo, contrasenia, nombre, app, apm, activo, nombreUsuario, esAdministrador) VALUES ('javier@ciencias.unam.mx', 'password3', 'Javier', 'Téllez', 'Vieyra', 1, 'pilluelin', 1);

-- Puestos de Comida
INSERT INTO Puesto (nombre, horario, latitud, longitud) VALUES ('Cafetería Ciencias', '07:00-20:00', '19.324515', '-99.179323');
INSERT INTO Puesto (nombre, horario, latitud, longitud) VALUES ('Rico y Saludable', '10:00-18:00', '19.32427', '-99.17915');
INSERT INTO Puesto (nombre, horario, latitud, longitud) VALUES ('Harry', '10:00-18:00', '19.32427', '-99.17905');

-- Comentarios
INSERT INTO Comentario (idPuesto, idUsuario, contenido, fecha, calificacion) VALUES (1, 1, '¡Me encantan los molletes!', CURRENT_TIMESTAMP, 4);
INSERT INTO Comentario (idPuesto, idUsuario, contenido, fecha, calificacion) VALUES (2, 2, '¡Pueden pedir las tortillas que quieran!', CURRENT_TIMESTAMP, 5);
INSERT INTO Comentario (idPuesto, idUsuario, contenido, fecha, calificacion) VALUES (3, 3, 'Me salió una uña en el consomé', CURRENT_TIMESTAMP, 1);

-- Tipo de Comida
INSERT INTO TipoComida (nombre) VALUES ('Comida corrida');

-- Servicios Adicionales
INSERT INTO ServicioAdicional (nombre) VALUES ('Mesas');
INSERT INTO ServicioAdicional (nombre) VALUES ('Baños');

-- Tipo de Comida del Puesto
INSERT INTO TipoComidaPuesto (idTipoComida, idPuesto) VALUES (1,1);
INSERT INTO TipoComidaPuesto (idTipoComida, idPuesto) VALUES (1,2);
INSERT INTO TipoComidaPuesto (idTipoComida, idPuesto) VALUES (1,3);

-- Servicios Adicionales del Puesto
INSERT INTO ServicioAdicionalPuesto (idServicio, idPuesto) VALUES (1,1);