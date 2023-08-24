create table medicos(
    id INTEGER PRIMARY KEY,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(50) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero integer,
    uf char(2) not null,
    cidade varchar(20) not null,
);