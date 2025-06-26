CREATE TABLE product
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(15) NOT NULL,
    price     INT NOT NULL,
    image_url TEXT
);
