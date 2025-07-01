package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.InvalidImageUrlException;
import gift.exception.InvalidNameException;
import gift.exception.InvalidPriceException;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  private Product findProductByIdOrFail(Long id) {
    Product product = productRepository.findById(id);
    if (product == null) {
      throw new ProductNotFoundException();
    }
    return product;
  }

  public ProductResponseDto findProductById(Long productId) {
    return findProductByIdOrFail(productId).toDto();
  }

  public ProductResponseDto saveProduct(ProductRequestDto dto) {
    Product product = productRepository.saveProduct(dto.name(), dto.price(), dto.imageUrl());
    return product.toDto();
  }

  public ProductResponseDto updateProduct(Long productId, ProductRequestDto dto) {
    Product updatedProduct = productRepository.updateProduct(productId, dto.name(), dto.price(),
        dto.imageUrl());
    return updatedProduct.toDto();
  }

  //가격만 수정하는 것은 꽤 합리적이라고 생각
  public ProductResponseDto updateProductPrice(Long productId, int price) {
    if (price < 0) {
      throw new InvalidPriceException();
    }
    Product updatedProduct = productRepository.updatePrice(productId, price);
    return updatedProduct.toDto();
  }

  public void deleteProduct(Long productId) {
    findProductByIdOrFail(productId);
    productRepository.deleteById(productId);
  }

  public List<ProductResponseDto> findAllProducts() {
    return
        productRepository.findAllProducts()
            .stream()
            .map(Product::toDto)
            .collect(Collectors.toList());
  }

}
