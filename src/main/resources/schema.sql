drop table products if exists;

create table products
(
    id          bigint auto_increment primary key,
    name        varchar(255) not null,
    price       bigint not null,
    imageUrl    varchar(1024)
);