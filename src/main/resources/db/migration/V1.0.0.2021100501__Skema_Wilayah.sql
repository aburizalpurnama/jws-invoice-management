create table provinsi(
    id varchar(36),
    code varchar (50) not null,
    name varchar(255) not null,
    primary key(id),
    unique(code)
);

create table kota_kabupaten(
    id varchar(36),
    id_provinsi varchar(36) not null,
    code varchar (50) not null,
    name varchar(255) not null,
    primary key(id),
    unique(code),
    foreign key(id_provinsi) references provinsi(id)
);

create table kecamatan(
    id varchar(36),
    id_kota_kab varchar(36) not null,
    code varchar (50) not null,
    name varchar(255) not null,
    primary key(id),
    unique(code),
    foreign key(id_kota_kab) references kota_kabupaten(id)
);

create table kelurahan(
    id varchar(36),
    id_kecamatan varchar(36) not null,
    code varchar (50) not null,
    name varchar(255) not null,
    primary key(id),
    unique(code),
    foreign key(id_kecamatan) references kecamatan(id)
);
