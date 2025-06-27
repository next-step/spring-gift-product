CREATE TABLE product
(
    productId BIGINT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    price     INTEGER      NOT NULL,
    image_url VARCHAR(500)
);

