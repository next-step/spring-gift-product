create table products
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) not null ,
    price bigint not null ,
    imageUrl varchar(255) not null
);