create table products(
    id bigint auto_increment primary key,
    name varchar(255),
    price decimal(10, 2),
    image_url varchar(512)
);