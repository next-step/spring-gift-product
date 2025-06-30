CREATE TABLE product
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price        INT          NOT NULL,
    image_url    VARCHAR(1024) NOT NULL
);