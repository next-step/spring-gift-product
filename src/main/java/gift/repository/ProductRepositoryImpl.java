package gift.repository;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long id = 1L;

    @Override
    public Product createProduct(CreateProductRequestDto requestDto) {
        Product newProduct = new Product(id++, requestDto.getName(), requestDto.getPrice(),
                requestDto.getImageUrl());
        products.put(newProduct.getId(), newProduct);
        return newProduct;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> productsList = new ArrayList<>();
        for (Product product : products.values()) {
            ProductResponseDto responseDto = new ProductResponseDto(product.getId(),
                    product.getName(), product.getPrice(), product.getImageUrl());
            productsList.add(responseDto);
        }
        return productsList;
    }

    @Override
    public Product findProductById(Long id) {
        return products.get(id);
    }

    @Override
    public void deleteProductById(Long id) {
        products.remove(id);
    }
}
