package gift.dto;

import gift.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product toEntity(ProductRequest request) {
    return new Product(request.getId(), request.getName(), request.getPrice(),
        request.getImageUrl());
  }

  public ProductResponse toResponse(Product product){
    return new ProductResponse(product.getName(), product.getPrice(), product.getImageUrl());
  }

}
