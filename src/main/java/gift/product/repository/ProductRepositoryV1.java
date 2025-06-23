package gift.product.repository;

import gift.domain.Product;
import gift.global.exception.NotFoundProductException;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductUpdateRequest;
import gift.util.StringValidator;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ProductRepositoryV1 implements ProductRepository{
    private final Map<String, Product> products = new HashMap<>();

    public String save(ProductCreateRequest dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getImageURL());

        products.put(product.getId(), product);

        return product.getId();
    }

    public List<Product> findAll() {
        return products.values()
                .stream()
                .toList();
    }


    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }


    public void deleteById(String id) {
        if (products.get(id) != null) products.remove(id);
        else throw new NotFoundProductException("삭제 실패 - 존재하지 않는 상품입니다");
    }


    public void update(String id, ProductUpdateRequest dto) {
        if (products.get(id) == null) throw new NotFoundProductException("수정 실패 - 존재하지 않는 상품입니다");

        Product oldProduct = products.get(id);
        String name = oldProduct.getName();
        int price = oldProduct.getPrice();
        String imageURL = oldProduct.getImageURL();

        if (StringValidator.validate(dto.getName())) {
            name = dto.getName();
        }
        if (StringValidator.validate(dto.getImageURL())) {
            imageURL = dto.getImageURL();
        }
        if (dto.getPrice() != 0) {
            price = dto.getPrice();
        }

        products.put(id, new Product(id, name, price, imageURL));
    }
}
