create table products
(
    id        bigint auto_increment primary key,
    name      varchar(255) not null,
    price     int          not null check (price >= 0),
    image_url varchar(1000)
);
