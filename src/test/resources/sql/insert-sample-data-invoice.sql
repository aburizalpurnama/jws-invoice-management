-- Bank

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
values('bca', 'BCA', 'Bank Central Asia', '014', 'ACTIVE', current_timestamp, 'user-test');

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
values('bni', 'BNI', 'Bank Nasional Indonesia', '920', 'ACTIVE', current_timestamp, 'user-test');

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
values('bsi', 'BSI', 'Bank Syariah Indonesia', '020', 'ACTIVE', current_timestamp, 'user-test');

insert into bank (id, code, name, central_bank_code, status_record, created, created_by)
values('cimb', 'CIMB', 'CIMB Niaga', '076', 'ACTIVE', current_timestamp, 'user-test');

-- Bank Account

insert into bank_account (id, id_bank, account_number, account_name, branch_name, status_record, created, created_by)
values ('bca001', 'bca', '123456789001', 'Test account BCA', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'user-test');

insert into bank_account (id, id_bank, account_number, account_name, branch_name, status_record, created, created_by)
values ('bni001', 'bni', '123456789002', 'Test account BNI', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'user-test');

insert into bank_account (id, id_bank, account_number, account_name, branch_name, status_record, created, created_by)
values ('bsi001', 'bsi', '123456789003', 'Test account BSI', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'user-test');

insert into bank_account (id, id_bank, account_number, account_name, branch_name, status_record, created, created_by)
values ('cimb001', 'cimb', '123456789004', 'Test account CIMB', 'Kantor Pusat', 'ACTIVE', current_timestamp, 'user-test');

-- Payment Provider

insert into payment_provider (id, code, name, status_record, created, created_by)
values ('bankbca', 'BCA', 'Bank BCA', 'ACTIVE', current_timestamp, 'user-test');

insert into payment_provider (id, code, name, status_record, created, created_by)
values ('bankbni', 'BNI', 'Bank BNI', 'ACTIVE', current_timestamp, 'user-test');

insert into payment_provider (id, code, name, status_record, created, created_by)
values ('bankbsi', 'BSI', 'Bank BSI', 'ACTIVE', current_timestamp, 'user-test');

insert into payment_provider (id, code, name, status_record, created, created_by)
values ('bankcimb', 'CIMB', 'Bank CIMB', 'ACTIVE', current_timestamp, 'user-test');

--  virtual account configuration

insert into virtual_account_configuration (id, code, name, id_payment_provider, id_bank_account, transaction_fee_flat, transaction_fee_persentage, company_prefix, account_number_length, status_record, created, created_by)
values ('va-gopay', 'VA-GOPAY', 'Pembayaran GoPay', 'bankbca', 'bca001', 0, 0.025, '123', 12, 'ACTIVE', current_timestamp, 'user-test');

insert into virtual_account_configuration (id, code, name, id_payment_provider, id_bank_account, transaction_fee_flat, transaction_fee_persentage, company_prefix, account_number_length, status_record, created, created_by)
values ('va-bni', 'VA-BNI', 'Pembayaran BNI', 'bankbni', 'bni001', 0, 0.025, '123', 12, 'ACTIVE', current_timestamp, 'user-test');

-- invoice type

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
values ('registrasi', 'REG-001', 'Biaya Pendaftaran', 'CLOSED', 'ACTIVE', current_timestamp, 'user-test');

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
values ('donasi', 'DON-001', 'Sumbangan Sukarela', 'OPEN', 'ACTIVE', current_timestamp, 'user-test');

insert into invoice_type (id, code, name, payment_type, status_record, created, created_by)
values ('uang-muka', 'DP-001', 'Down Payment', 'INSTALLMENT', 'ACTIVE', current_timestamp, 'user-test');


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
insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c001', 'CUST-001', 'Customer 001', 'c001@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c002', 'CUST-002', 'Customer 002', 'c002@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c003', 'CUST-003', 'Customer 003', 'c003@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');

insert into customer (id, code, name, email, mobile_phone, status_record, created, created_by, updated, updated_by)
values ('c004', 'CUST-004', 'Customer 004', 'c004@mail.com', '09899929919', 'ACTIVE', '2021-09-08T11:28:38.426199', 'test-user', '2021-09-08T11:48:38.426199', 'test-user');
