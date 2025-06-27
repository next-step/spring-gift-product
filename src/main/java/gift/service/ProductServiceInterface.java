package gift.service;

import gift.entity.Product;

interface ProductServiceInterface {

  //상품 조회
  Product getProduct(long productId);

  //상품 추가
  void createProduct(Product product);

  //상품 수정
  void updateProduct(long productId, Product product);

  //상품 삭제
  void deleteProduct(long productId);

  //상품 유무 확인
  boolean containsProduct(long productId);

}
