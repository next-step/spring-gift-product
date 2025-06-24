package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImp implements ProductRepository{
    private final Map<Long, Product> products = new HashMap<>();

    public ProductRepositoryImp(){
        Product product1 = new Product(1L, "test1", 12000L, "temp1");
        Product product2 = new Product(2L, "test2", 13000L, "temp2");
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {

        return products.values()
                .stream()
                .map(ProductResponseDto::toDto)
                .toList();
    }

    @Override
    public ProductResponseDto findProductByIdOrElseThrow(Long id) {
        Product product = products.get(id);

        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품을 찾을 수 없습니다.");
        }

        return ProductResponseDto.toDto(product);
    }
}
