CREATE TABLE product
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY, -- '상품 ID'
    name      VARCHAR(255) NOT NULL,             -- '상품명'
    price     INT          NOT NULL,             -- '가격'
    image_url VARCHAR(255) NOT NULL              -- '이미지 URL'
);
