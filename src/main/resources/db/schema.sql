CREATE TABLE products (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL,
    image_url VARCHAR(255),
    PRIMARY KEY(id)
);