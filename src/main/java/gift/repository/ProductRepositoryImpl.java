package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private final Map<Long, Product> products = new HashMap<>();
    @Override
    public Product createProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
        return newProduct;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> productsList = new ArrayList<>();
        for (Product product : products.values()){
            ProductResponseDto responseDto = new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
            productsList.add(responseDto);
        }
        return productsList;
    }
}
