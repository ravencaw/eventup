INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','admin','admin@eventup.com','Sebastian','Pedrosa','1995-02-28');
INSERT INTO user (enabled,password,username,email,nombre,apellidos,fecha_nac) VALUES(true,'$2a$04$N/UFFd6vJpwlYJkOO8j8xu5CsfFdabJKU4k6jfAuwdjWeuRPMlYM.','user','user@eventup.com','Juan','Alvarez','1993-03-23');

INSERT INTO authority (authority) VALUES ('ROLE_ADMIN');
INSERT INTO authority (authority) VALUES ('ROLE_USER');
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,1);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (2,2);