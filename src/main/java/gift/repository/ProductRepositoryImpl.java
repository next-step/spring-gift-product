package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final Map<Long, Product> productList = new HashMap<>();

    @Override
    public List<ProductResponseDto> findAllProducts() {

        return productList.values()
                .stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    @Override
    public Product findProductByIdElseThrow(Long id) {

        Product product = productList.get(id);

        if (product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품은 존재하지 않습니다.");

        return product;
    }

    @Override
    public ProductResponseDto saveProduct(String name, Long price, String imageUrl) {

        Long id = productList.isEmpty() ? 1 : Collections.max(productList.keySet()) + 1;

        Product product = new Product(id, name, price, imageUrl);
        productList.put(id, product);

        return new ProductResponseDto(product);
    }

    @Override
    public Product updateProduct(Long id, String name, Long price, String imageUrl) {

        Product product = productList.get(id);

        product.updateProduct(name, price, imageUrl);

        return product;
    }
}
