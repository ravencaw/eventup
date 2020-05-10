INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','admin','admin@eventup.com','Sebastian','Pedrosa','1995-02-28');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','user','user@eventup.com','Juan','Alvarez','1993-03-23');

INSERT INTO authority (authority) VALUES ('ROLE_ADMIN');
INSERT INTO authority (authority) VALUES ('ROLE_USER');

INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,1);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (2,2);

INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar) VALUES('Pescar', 'Nos iremos a pescar al rio', 'Pepito Antonio Grillo', '200', 'Rio Seco');
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar) VALUES('Jugar a la comba', 'Haremos deporte saltando a la comba, será divertido', 'Juanito El de los Palotes', '50', 'Parque este');
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar) VALUES('Jugar al ajedrez', 'Pasaremos una fantástica tarde jugando al ajedrez', 'Pepito Perez', '50', 'Hogar del Jubilado');
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar) VALUES('Jugar a la pelota', 'Jugaremos a la pelota, volveremos a la infancia', 'Juanito Norte', '45', 'Parque oeste');
INSERT INTO actividades(nombre, descripcion, ponentes, capacidad, lugar) VALUES('Bailar', 'Haremos deporte bailando, será divertido', 'Pepita Marquez', '200', 'Parque sur');

INSERT INTO transporte(empresa, capacidad,coor_inicio, coor_final, precio, tipo, hora_salida, hora_llegada) VALUES('Renfe', '200', '', '', '35', 'tren', '12:00', '20:00');

INSERT INTO evento(nombre, descripcion, foto, organizador, ciudad, direccion, latitud, longitud, fecha, hora, precio, cantidad_entradas) VALUES ('Concierto Strad', 'Concierto presentacion Mundos Opuestos', null, 'Vuvuzela', 'Madrid', 'Calle Meloinvento 12', null, null, '2020-05-09', '20:15', 7.95, 300);

INSERT INTO valoraciones(comentario, like, id_blog, id_usuario) VALUES('Me lo pase muy bien', '1', '1', '1'); 