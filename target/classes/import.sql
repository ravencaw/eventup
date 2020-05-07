/*TABLA ACTIVIDADES*/
INSERT INTO actividades(id, nombre, descripcion, ponentes, capacidad, lugar,id_evento) VALUES('1','Pescar', 'Nos iremos a pescar al rio', 'Pepito Antonio Grillo', '200', 'Rio Seco','1');
INSERT INTO actividades(id, nombre, descripcion, ponentes, capacidad, lugar,id_evento) VALUES('2','Jugar a la comba', 'Haremos deporte saltando a la comba, será divertido', 'Juanito El de los Palotes', '50', 'Parque este','2');
INSERT INTO actividades(id, nombre, descripcion, ponentes, capacidad, lugar,id_evento) VALUES('3','Jugar al ajedrez', 'Pasaremos una fantástica tarde jugando al ajedrez', 'Pepito Perez', '50', 'Hogar del Jubilado','3');
INSERT INTO actividades(id, nombre, descripcion, ponentes, capacidad, lugar,id_evento) VALUES('4','Jugar a la pelota', 'Jugaremos a la pelota, volveremos a la infancia', 'Juanito Norte', '45', 'Parque oeste','2');
INSERT INTO actividades(id, nombre, descripcion, ponentes, capacidad, lugar,id_evento) VALUES('5','Bailar', 'Haremos deporte bailando, será divertido', 'Pepita Marquez', '200', 'Parque sur','4');

/*TABLA TRANSPORTE*/

INSERT INTO transporte(id,empresa, capacidad,coor_inicio, coor_final, precio, tipo, hora_salida, hora_llegada) VALUES('1','Renfe', '200', '85-15', '56-89', '35', '12:00', '20:00');


/*TABLA VALORACION*/
INSERT INTO valoracion (id, comentario, like, id_blog, id_usuario) VALUES ('1', 'Ha estao de puta madre, casi me la pego', '1', '1', '1');
INSERT INTO valoracion (id, comentario, like, id_blog, id_usuario) VALUES ('2', 'Vaya porquería de evento, un aburrimiento', '2', '2', '2');
