INSERT INTO role (id, name) VALUES (0, 'ROLE_ADMIN')
INSERT INTO role (id, name) VALUES (1, 'ROLE_USER')

INSERT INTO privilege (id, name) VALUES (0, 'READ_PRIVILEGE')
INSERT INTO privilege (id, name) VALUES (1, 'WRITE_PRIVILEGE')

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (0, 0)
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 0)
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 1)

INSERT INTO user (id, firstname, lastname, email, password, enabled, token_expired) VALUES (0, 'user','userName','user@userland.com', '{noop}password', true, false)
INSERT INTO user (id, firstname, lastname, email, password, enabled, token_expired) VALUES (1, 'admin','adminName','admin@userland.com', '{noop}password', true, false)

INSERT INTO users_roles (user_id, role_id) VALUES (0, 0)
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1)