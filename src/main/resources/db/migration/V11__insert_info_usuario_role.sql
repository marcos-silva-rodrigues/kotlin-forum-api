INSERT INTO usuario (nome, email, password) VALUES ('admin', 'admin@email.com', '$2a$12$1KiDJ90Rv0CmbK7EYx.Bk.GigqNPsUoHqE9Au4NFq80AaGeQlkbBG');
INSERT INTO role (nome) VALUES ('ADMIN');
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2, 2);