INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni, provincia, localidad) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','admin','admin@eventup.com','Sebastian','Pedrosa','1995-02-28','225311456A', 'Malaga', 'Antequera');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni, provincia, localidad) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','user','user@eventup.com','Juan','Alvarez','1993-03-23','23456789P', 'Madrid', 'Madrid');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni, provincia, localidad) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','raven','sebastianpedrosa.iespe@gmail.com','Sebastian','Pedrosa','1995-02-28','25352523Z', 'Sevilla', 'Sevilla');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni, provincia, localidad) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','mari','mariclozano4@gmail.com','Mari Carmen','Lozano','1998-07-04','53703425N', 'Malaga', 'Campillos');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni, provincia, localidad) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','adri','adlian25@gmail.com','Adrian','Casado','1995-02-28','53703428Z', 'Sevilla', 'Sevilla');

INSERT INTO authority (authority) VALUES ('ROLE_ADMIN');
INSERT INTO authority (authority) VALUES ('ROLE_USER');

INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,1);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (2,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (3,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (4,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (5,2);

INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas, usuario_id) VALUES ('Concierto Strad', 'Concierto presentacion Mundos Opuestos', 'evento1.jpg', 'Vuvuzela', 'Madrid', 'Paseo de las Delicias 17', '40.405679', '-3.693419', '2020-05-09', '20:15', 7.95, 300, 3);
INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas, usuario_id) VALUES ('MangaFest', 'Evento cultural sobre el manga y su influencia en la sociedad', 'evento2.jpg', 'FreakCompany', 'Madrid', 'Calle Toledo 42', '40.412201', '-3.707904', '2020-05-23', '11:15', 11.95, 30000, 2);
INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas, usuario_id) VALUES ('Concierto Pablo Alborán', 'Concierto Tabú Benéfico', 'evento3.jpg', 'Sevilla', 'Sevilla', 'Camino de los Descubrimientos', '37.3992907', '-6.0072398', '2020-10-09', '20:15', 40.95, 300, 3);
INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas, usuario_id) VALUES ('Concierto Juan Magan', 'Concierto presentación disco nuevo', 'evento4.jpg', 'Sevilla', 'Sevilla', 'Calle Juan Bautista Muñoz', '37.4101841', '-5.9998791', '2020-05-09', '20:15', 7.95, 300, 4);
INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas, usuario_id) VALUES ('Weekend', 'Festival en Torre del Mar', 'evento5.jpeg', 'Torre del Mar', 'Malaga', 'Calle Faro Nuevo, 5', '36.7320592', '-4.0997098', '2020-07-20', '12:15', 80.95, 300, 4);
INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas, usuario_id) VALUES ('Festival flamenco', 'Festival de cante flamenco', 'evento6.jpg', 'Granada', 'Granada', 'Paseo de los Mártires, s/n', '37.172922', '-3.588128', '2020-05-09', '20:15', 7.95, 300, 5);

INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Pescar', 'Nos iremos a pescar al rio', 'Pepito Antonio Grillo', '200', 'Rio Seco', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Jugar a la comba', 'Haremos deporte saltando a la comba, será divertido', 'Juanito El de los Palotes', '50', 'Parque este', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Jugar al ajedrez', 'Pasaremos una fantástica tarde jugando al ajedrez', 'Pepito Perez', '50', 'Hogar del Jubilado', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Jugar a la pelota', 'Jugaremos a la pelota, volveremos a la infancia', 'Juanito Norte', '45', 'Parque oeste', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Bailar', 'Haremos deporte bailando, será divertido', 'Pepita Marquez', '200', 'Parque sur', 1);

INSERT INTO transportes(empresa, capacidad, lat_inicio, lng_inicio, lat_fin, lng_fin,  precio, tipo, hora_salida, hora_llegada, evento_id) VALUES('Renfe', '200', '37.021823', '-4.559562', '40.405679', '-3.693419', '35', 'tren', '12:00', '20:00', 1);

INSERT INTO ventas(fecha, hora, total, evento_id) VALUES ('2020-05-05', '20:00', 7.95, 1);
INSERT INTO ventas(fecha, hora, total, evento_id) VALUES ('2020-05-05', '21:00', 7.95, 1);
INSERT INTO ventas(fecha, hora, total, evento_id) VALUES ('2020-05-15', '20:30', 7.95, 1);

INSERT INTO entradas(transporte_id, usuario_id, id_venta, num_asiento, tipo) VALUES(1, 2, 1, 13,'standard');
INSERT INTO entradas(transporte_id, usuario_id, id_venta, num_asiento, tipo) VALUES(1, 3, 2, 15,'standard');
INSERT INTO entradas(transporte_id, usuario_id, id_venta, num_asiento, tipo) VALUES(1, 1, 3, 1,'premium');

INSERT INTO asistencias(id_entrada, fecha, hora) VALUES(1, '2020-05-21', '17:00');
INSERT INTO asistencias(id_entrada, fecha, hora) VALUES(2, '2020-05-21', '17:15');
INSERT INTO asistencias(id_entrada, fecha, hora) VALUES(2, '2020-05-21', '17:15');


UPDATE entradas SET id_asistencia = 1 WHERE id = 1;
UPDATE entradas SET id_asistencia = 2 WHERE id = 2;
