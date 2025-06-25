package gift.service;

import gift.domain.Product;
import gift.dto.product.CreateProductRequest;
import gift.dto.product.ProductManageResponse;
import gift.dto.product.ProductResponse;
import gift.dto.product.UpdateProductRequest;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(CreateProductRequest request) {
        Product product = new Product(request.name(), request.price(), request.quantity());
        return productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::from).toList();
    }

    public Product updateProduct(Long id, UpdateProductRequest request) {
        Product product = getById(id);
        return product.update(request.name(), request.price(), request.quantity());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteByid(id);
    }

    public List<ProductManageResponse> getAllProductsManagement() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductManageResponse::from).toList();
    }

    public ProductManageResponse getProductManagement(Long id) {
        Product product = getById(id);
        return ProductManageResponse.from(product);
    }

    private Product getById(Long id) {
        Optional<Product> getProduct = productRepository.findById(id);
        return getProduct.orElseThrow(() -> new IllegalStateException("Product를 찾을 수 없습니다."));
    }
}
