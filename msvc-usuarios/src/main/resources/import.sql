INSERT INTO `usuarios` (username,password,nombre,apellido,email,enabled) VALUES('damian','123','damian','adamo','damiang66@gmail.com',1);
INSERT INTO `usuarios` (username,password,nombre,apellido,email,enabled)  VALUES('admin','123','camila','adamo','camila@gmail.com',1);

INSERT INTO `roles` (nombre) VALUES('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES('ROLE_ADMIN');
INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(1,1);
INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(2,2);
INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(2,1);
