INSERT INTO usuarios (username,password,nombre,apellido,email,enabled) VALUES('damian','$2a$10$0zPDGpery7qFyrZbfcnHGONn.6L0M/Y7m3og3.FX/ImYd9eHIyWfO','damian','adamo','damiang66@gmail.com',true);
INSERT INTO usuarios (username,password,nombre,apellido,email,enabled)  VALUES('admin','$2a$10$igOu2LKFqFDIAvjZg6YZZezMpYj6WlZmeA6BLsqq3rZZKEm4WngPS','camila','adamo','camila@gmail.com',true);

INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);
