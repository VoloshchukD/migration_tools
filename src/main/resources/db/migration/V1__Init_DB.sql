create table if not exists company (
    id bigserial not null,
    name varchar(255),
    primary key (id)
);

create table if not exists employee (
    id bigserial not null,
    name varchar(255),
    primary key (id)
);