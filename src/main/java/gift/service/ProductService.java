package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepositoryInterface;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

  private final ProductRepositoryInterface productRepository;

  public ProductService(ProductRepositoryInterface productRespository) {
    this.productRepository = productRespository;
  }

  //단일 상품 조회
  public Product getProduct(long productId) {
    return productRepository.findById(productId).orElseThrow();
  }

  //상품 생성
  public void createProduct(Product product) {
    productRepository.add(product);
  }

  //상품 수정
  public void updateProduct(Product product) {
    productRepository.update(product);
  }

  //상품 삭제
  public void deleteProduct(long productId) {
    productRepository.delete(productId);
  }

  //상품 유무 확인
  public boolean containsProduct(long productId) {
    return productRepository.containsKey(productId);
  }
}
