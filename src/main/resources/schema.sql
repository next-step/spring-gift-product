-- 기존에 테이블이 있다면 삭제
DROP TABLE IF EXISTS product;

-- products 테이블 생성
CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    image_url VARCHAR(1000)
);