-- invoice type configuration

insert into invoice_type_configuration (id_invoice_type, id_virtual_account_configuration)
values ('registrasi', 'va-bni');

insert into invoice_type_configuration (id_invoice_type, id_virtual_account_configuration)
values ('registrasi', 'va-gopay');

insert into invoice_type_configuration (id_invoice_type, id_virtual_account_configuration)
values ('donasi', 'va-gopay');

insert into invoice_type_configuration (id_invoice_type, id_virtual_account_configuration)
values ('uang-muka', 'va-bni');

-- customer
insert into curstomer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c001', 'CUST-001', 'Customer 001', 'c001@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into curstomer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c002', 'CUST-002', 'Customer 002', 'c002@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into curstomer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c003', 'CUST-003', 'Customer 003', 'c003@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');
8
insert into curstomer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c004', 'CUST-004', 'Customer 004', 'c004@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');