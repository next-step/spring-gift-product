package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ProductResponseDto findProductByIdElseThrow(Long id) {

        Product p1 = new Product(1L, "p", 1000L, "t");
        productList.put(1L, p1);

        Product product = productList.get(id);

        if (product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품은 존재하지 않습니다.");

        return new ProductResponseDto(product);
    }
}
