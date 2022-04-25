insert into bank(id, bank_code, bank_name) values (1, 'MYBANK', 'My Bank');

-- insert admin
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (1, 'admin@demo.com', 'admin', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'Admin', '0987654321', '123456789');
insert into user_role(id, role_name, user_id) values(1, 'ROLE_ADMIN', 1);

-- insert user
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (2, 'user@demo.com', 'user', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'User Demo', '0852369741', '852369741');
insert into user_role(id, role_name, user_id) values(2, 'ROLE_USER', 2);