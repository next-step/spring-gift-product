create table product (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    price bigint not null,
    image_url varchar(1000)
);
