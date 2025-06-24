package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    Long nextId = 1L;

    public Product save(Product product) {

        Long id = nextId++;
        product.setId(id);
        products.put(id, product);

        return product;
    }

    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public Optional<Product> update(Long id, String name, Integer price, String imageUrl) {
        Product existingProduct = products.get(id);
        if (existingProduct == null) {
            return Optional.empty();
        }

        existingProduct.setName(name);
        existingProduct.setPrice(price);
        existingProduct.setImageUrl(imageUrl);

        return Optional.of(existingProduct);
    }

    public Optional<Product> deleteById(Long id) {
        return Optional.ofNullable(products.remove(id));
    }

    public List<Product> findAll(int page, int size, String sortField, String sortDir) {
        List<Product> productList = new ArrayList<>(products.values());

        if (sortField.equals("price")) {
            if (sortDir.equalsIgnoreCase("desc")) {
                productList.sort((a, b) -> b.getPrice().compareTo(a.getPrice()));
            } else {
                productList.sort((a, b) -> a.getPrice().compareTo(b.getPrice()));
            }
        } else if (sortField.equals("name")) {
            if (sortDir.equalsIgnoreCase("desc")) {
                productList.sort((a, b) -> b.getName().compareToIgnoreCase(a.getName()));
            } else {
                productList.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
            }
        } else {
            if (sortDir.equalsIgnoreCase("desc")) {
                productList.sort((a, b) -> b.getId().compareTo(a.getId()));
            } else {
                productList.sort((a, b) -> a.getId().compareTo(b.getId()));
            }
        }

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, productList.size());

        if (fromIndex >= productList.size()) {
            return new ArrayList<>();
        }

        return productList.subList(fromIndex, toIndex);
    }
}
