INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','admin','admin@eventup.com','Sebastian','Pedrosa','1995-02-28','225311456A');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','user','user@eventup.com','Juan','Alvarez','1993-03-23','23456789P');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac,dni) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','raven','raven@eventup.com','Sebastian','Pedrosa','1995-02-28','25352523Z');

INSERT INTO authority (authority) VALUES ('ROLE_ADMIN');
INSERT INTO authority (authority) VALUES ('ROLE_USER');

INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,1);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (2,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (3,2);

INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas) VALUES ('Concierto Strad', 'Concierto presentacion Mundos Opuestos', null, 'Vuvuzela', 'Madrid', 'Calle Meloinvento 12', null, null, '2020-05-09', '20:15', 7.95, 300);
INSERT INTO eventos(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas) VALUES ('MangaFest', 'Evento cultural sobre el manga y su influencia en la sociedad', null, 'FreakCompany', 'Madrid', 'Calle Nueva 7', null, null, '2020-05-23', '11:15', 11.95, 30000);

INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Pescar', 'Nos iremos a pescar al rio', 'Pepito Antonio Grillo', '200', 'Rio Seco', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Jugar a la comba', 'Haremos deporte saltando a la comba, será divertido', 'Juanito El de los Palotes', '50', 'Parque este', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Jugar al ajedrez', 'Pasaremos una fantástica tarde jugando al ajedrez', 'Pepito Perez', '50', 'Hogar del Jubilado', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Jugar a la pelota', 'Jugaremos a la pelota, volveremos a la infancia', 'Juanito Norte', '45', 'Parque oeste', 1);
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar, evento_id) VALUES('Bailar', 'Haremos deporte bailando, será divertido', 'Pepita Marquez', '200', 'Parque sur', 1);

INSERT INTO valoracion(comentario, opinion, id_blog,id_usuario) VALUES ('Fue lo mejor del día',1,1,1);
INSERT INTO transportes(empresa, capacidad,coor_inicio, coor_final, precio, tipo, hora_salida, hora_llegada, evento_id) VALUES('Renfe', '200', '', '', '35', 'tren', '12:00', '20:00', 1);

INSERT INTO ventas(fecha, hora, total, evento_id) VALUES ('2020-05-05', '20:00', 7.95, 1)
INSERT INTO ventas(fecha, hora, total, evento_id) VALUES ('2020-05-05', '21:00', 7.95, 1)
INSERT INTO ventas(fecha, hora, total, evento_id) VALUES ('2020-05-15', '20:30', 7.95, 1)

INSERT INTO entradas(id_transporte, usuario_id, id_venta, num_asiento, tipo) VALUES(1, 2, 1, 13,'standard')
INSERT INTO entradas(id_transporte, usuario_id, id_venta, num_asiento, tipo) VALUES(1, 3, 2, 15,'standard')
INSERT INTO entradas(id_transporte, usuario_id, id_venta, num_asiento, tipo) VALUES(1, 1, 3, 1,'premium')

INSERT INTO asistencias(id_entrada, fecha, hora) VALUES(1, '2020-05-21', '17:00')
INSERT INTO asistencias(id_entrada, fecha, hora) VALUES(3, '2020-05-21', '17:15')
