CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    image_url VARCHAR(255)
);
INSERT INTO product (name, price, image_url)
VALUES
  ('초콜릿', 10000, 'https://mblogthumb-phinf.pstatic.net/MjAxODAyMTJfMTgy/MDAxNTE4NDI0NDc3NzQw.9wmTb4Sqx0n7RNG_kC0HSn2m5QuMMha3TOp-gjkm3Sgg.YGzsc2Y4kIJip9_TvzAqV78amT4MN7211v1zyQ4P4hUg.JPEG.letsrun2014/yaytg316061.jpg?type=w800'),
  ('사탕', 15000, 'https://image.idus.com/image/files/487d4064de8d4a7ebe3464ee39ef1e2c.jpg'),
  ('과자', 12000, 'https://i.namu.wiki/i/SkNgraT_xMbXwr48anSyTdndvgYf7M-uxlYZnXv0zG9zaEfuFm0ycnsa7sPmOZBo_rboakmFaFcu8sM4Ajzl6A.webp');

