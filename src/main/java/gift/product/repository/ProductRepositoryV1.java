package gift.product.repository;

import gift.domain.Product;
import gift.global.exception.NotFoundProductException;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductUpdateRequest;
import gift.util.StringValidator;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository
public class ProductRepositoryV1 implements ProductRepository{

    private final Map<UUID, Product> products = new HashMap<>();

    public UUID save(ProductCreateRequest dto) {
      
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getImageURL());

        products.put(product.getId(), product);

        return product.getId();
    }

    public List<Product> findAll() {
        return products.values()
                .stream()
                .toList();
    }


    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public void deleteById(UUID id) {
        if (products.containsKey(id)) products.remove(id);
        else throw new NotFoundProductException("삭제 실패 - 존재하지 않는 상품입니다");
    }



    public void update(UUID id, ProductUpdateRequest dto) {
        if (!products.containsKey(id)) throw new NotFoundProductException("수정 실패 - 존재하지 않는 상품입니다");

        Product oldProduct = products.get(id);
        String name = oldProduct.getName();
        int price = oldProduct.getPrice();
        String imageURL = oldProduct.getImageURL();

        if (StringValidator.isNotBlank(dto.getName())) {
            name = dto.getName();
        }
        if (StringValidator.isNotBlank(dto.getImageURL())) {
            imageURL = dto.getImageURL();
        }
        if (dto.getPrice() > 0) {
            price = dto.getPrice();
        }

        products.put(id, new Product(id, name, price, imageURL));
    }
}
