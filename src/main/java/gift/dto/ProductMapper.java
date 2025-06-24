package gift.dto;

import gift.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.of(request.id(), request.name(), request.price(), request.imageUrl());
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.of(product.getName(), product.getPrice(), product.getImageUrl());
    }

}
