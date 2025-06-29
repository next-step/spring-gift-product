package gift.service;

import gift.domain.Product;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(String name, int price, String imageUrl) {
        return repository.save(name, price, imageUrl);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> findAllByPage(int page, int size, String sort, Long categoryId) {
        return repository.findAll().stream()
                .filter(product -> categoryId == null || product.getCategoryId().equals(categoryId))
                .sorted(createComparator(sort))
                .skip((long) page * size)
                .limit(size)
                .toList();
    }


    public List<Product> findAllProducts(String sort, String keyword) {
        return repository.findAll().stream()
                .filter(p -> matchesKeyword(p, keyword))
                .sorted(createComparator(sort))
                .toList();
    }

    private boolean matchesKeyword(Product p, String keyword) {
        if (keyword == null || keyword.isBlank()) return true;

        return p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                String.valueOf(p.getId()).equals(keyword);
    }

    private Comparator<Product> createComparator(String sort) {
        String[] parts = sort.split(",");
        String key = parts[0];
        boolean ascending = parts.length < 2 || parts[1].equalsIgnoreCase("asc");

        Comparator<Product> comparator = switch (key) {
            case "name" -> Comparator.comparing(Product::getName);
            case "price" -> Comparator.comparingInt(Product::getPrice);
            default -> Comparator.comparing(Product::getId);
        };

        return ascending ? comparator : comparator.reversed();
    }


    public void delete(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) throw new ProductNotFoundException(id);
    }

    public Product update(Long id, String name, int price, String imageUrl) {
        return repository.update(id, name, price, imageUrl)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
