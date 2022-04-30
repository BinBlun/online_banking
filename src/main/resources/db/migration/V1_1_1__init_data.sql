insert into bank(id, bank_code, bank_money, bank_name) values (1, 'MYBANK', 1000000000, 'My Bank');

-- insert admin
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (1, 'admin@demo.com', 'admin', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'Admin', '0987654321', '123456789');
insert into user_role(id, role_name, user_id) values(1, 'ROLE_ADMIN', 1);

-- insert user
insert into user(id, email, username, password, encrypted_password, full_name, phone_number, ssn)
values (2, 'user@demo.com', 'user', '123', '$2a$12$GhSRSIPa7.0BYqXDLS3u3OWTJeGChigXK2EEMPdIkawx7okI.cvmK', 'User Demo', '0852369741', '852369741');
insert into user_role(id, role_name, user_id) values(2, 'ROLE_USER', 2);
insert into account(account_id, account_number, bank_id, current_balance, user_id) values(1, '159736842', 1, 0, 2);
insert into card(id, account_number, user_id) values(1, '159736842', 2);

insert into loans_package(loans_id, duration, interest_rate) values (1, 24, 0.02);
insert into loans_package(loans_id, duration, interest_rate) values (2, 12, 0.01);
insert into loans_package(loans_id, duration, interest_rate) values (3, 6, 0.0015);

insert into saving_package(id, duration, interestRate) values (1, 24, 0.02);
insert into saving_package(id, duration, interestRate) values (2, 12, 0.01);
insert into saving_package(id, duration, interestRate) values (3, 6, 0.0015)