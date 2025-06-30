CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(512) NOT NULL
);

INSERT INTO product (name, price, description, image_url)
VALUES ('테스트 상품', 1000, '설명', 'https://img.url');