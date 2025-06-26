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
    private Long listId;

    public ProductRepositoryImpl() {
        this.listId = 0L;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {

        return productList.values()
                .stream()
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl()
                ))
                .toList();
    }

    @Override
    public Product findProductById (Long id) {

        return productList.get(id);
    }

    @Override
    public Product findProductByIdElseThrow(Long id) {

        Product product = productList.get(id);

        if (product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품은 존재하지 않습니다.");

        return product;
    }

    @Override
    public Product saveProduct(String name, Long price, String imageUrl) {

        listId++;

        Product product = new Product(listId, name, price, imageUrl);
        productList.put(listId, product);

        return product;
    }

    @Override
    public Product updateProduct(Long id, String name, Long price, String imageUrl) {

        Product product = productList.get(id);

        product.updateProduct(name, price, imageUrl);

        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productList.remove(id);
    }
}
