insert into s_roles (id, name)
values ('r001', 'staff');

insert into s_roles (id, name)
values ('r002', 'manager');

insert into s_users (id, username, active, id_role)
values ('u001', 'user001', true, 'r001');

insert into s_users (id, username, active, id_role)
values ('u002', 'user002', true, 'r002');

-- password : teststaff
insert into s_users_passwords (id_user, password)
values ('u001', '$2a$10$8AfV.EkFEPh2OpqInI6r9.FT73nYeKe1bU6Lh.iLqOGnvNxDgXgGS');

-- password : testmanager
insert into s_users_passwords (id_user, password)
values ('u002', '$2a$10$RPB/8RrHOPBbUj0iYRy7hu7K2fMKEFIR5Cqb2oGyeKcRFY/sH0.Mi');

insert into s_permissions (id, label, value)
values ('p001', 'Lihat Data Transaksi', 'VIEW_TRANSAKSI');

insert into s_permissions (id, label, value)
values ('p002', 'Edit Data Transaksi', 'EDIT_TRANSAKSI');

insert into s_roles_permissions (id_role, id_permission)
values ('r001', 'p001');

insert into s_roles_permissions (id_role, id_permission)
values ('r002', 'p001');

insert into s_roles_permissions (id_role, id_permission)
values ('r002', 'p002');

insert into oauth2_registered_client (id, client_id, client_name,
      client_authentication_methods, client_secret, authorization_grant_types,
      redirect_uris, scopes, client_settings, token_settings)
values ('mobileapp', 'mobileapp', 'mobileapp',
        'basic, client_secret_basic', '$2a$10$sofxc4M7xltDyu4XynR7ouFDqGTr5BaTykK59wgsOopbMVjuBXQD6',
        'authorization_code,refresh_token', 'http://example.com,mobileapp:/authcode',
        'openid,message.read,message.write',
        '{"@class":"java.util.HashMap","setting.client.require-user-consent":false,"setting.client.require-proof-key":true}',
        '{"@class":"java.util.HashMap","setting.token.access-token-time-to-live":["java.time.Duration",300.000000000],"setting.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"setting.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"setting.token.reuse-refresh-tokens":true}')