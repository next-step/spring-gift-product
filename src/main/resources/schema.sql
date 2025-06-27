create table product (
    id bigint primary key auto_increment not null,
    name varchar(255) not null,
    price int not null,
    quantity int not null
);