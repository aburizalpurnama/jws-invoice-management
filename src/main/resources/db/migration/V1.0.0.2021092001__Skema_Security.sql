create table s_roles (
    id   varchar(36),
    name varchar(100) not null,
    primary key (id),
    unique (name)
);

create table s_users (
    id       varchar(36),
    username varchar(100) not null,
    active   boolean      not null,
    id_role  varchar(36)  not null,
    primary key (id),
    unique (username),
    foreign key (id_role) references s_roles (id)
);

create table s_users_passwords (
    id_user  varchar(36),
    password varchar(255) not null,
    primary key (id_user),
    foreign key (id_user) references s_users (id)
);

create table s_permissions (
    id    varchar(36),
    label varchar(100) not null,
    value varchar(100) not null,
    primary key (id),
    unique (value)
);

create table s_roles_permissions (
    id_role       varchar(36),
    id_permission varchar(36),
    primary key (id_role, id_permission),
    foreign key (id_role) references s_roles (id),
    foreign key (id_permission) references s_permissions (id)
);

CREATE TABLE `reset_password` (
  `id` varchar(255) NOT NULL,
  `created` datetime(6) NOT NULL,
  `expired` datetime(6) NOT NULL,
  `unique_code` varchar(255) NOT NULL,
  `id_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo30jfts6wuxfcn15onlvpyych` (`id_user`),
  CONSTRAINT `FKo30jfts6wuxfcn15onlvpyych` FOREIGN KEY (`id_user`) REFERENCES `s_users` (`id`)
);

CREATE TABLE oauth2_registered_client (
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(200) DEFAULT NULL,
    client_secret_expires_at timestamp DEFAULT NULL,
    client_name varchar(200) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(1000) NOT NULL,
    redirect_uris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE oauth2_authorization (
    id varchar(100) NOT NULL,
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorization_grant_type varchar(100) NOT NULL,
    attributes varchar(4000) DEFAULT NULL,
    state varchar(500) DEFAULT NULL,
    authorization_code_value blob DEFAULT NULL,
    authorization_code_issued_at timestamp DEFAULT NULL,
    authorization_code_expires_at timestamp DEFAULT NULL,
    authorization_code_metadata varchar(2000) DEFAULT NULL,
    access_token_value blob DEFAULT NULL,
    access_token_issued_at timestamp DEFAULT NULL,
    access_token_expires_at timestamp DEFAULT NULL,
    access_token_metadata varchar(2000) DEFAULT NULL,
    access_token_type varchar(100) DEFAULT NULL,
    access_token_scopes varchar(1000) DEFAULT NULL,
    oidc_id_token_value blob DEFAULT NULL,
    oidc_id_token_issued_at timestamp DEFAULT NULL,
    oidc_id_token_expires_at timestamp DEFAULT NULL,
    oidc_id_token_metadata varchar(2000) DEFAULT NULL,
    refresh_token_value blob DEFAULT NULL,
    refresh_token_issued_at timestamp DEFAULT NULL,
    refresh_token_expires_at timestamp DEFAULT NULL,
    refresh_token_metadata varchar(2000) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE oauth2_authorization_consent (
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorities varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);
