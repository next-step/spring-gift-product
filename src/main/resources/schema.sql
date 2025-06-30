create table products(
    id bigint auto_increment,
    name varchar(255) not null,
    price decimal(10,2) not null,
    imgUrl varchar(512) not null
);