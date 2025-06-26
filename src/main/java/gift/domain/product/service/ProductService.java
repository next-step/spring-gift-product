package gift.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gift.domain.product.dto.ProductRequest;
import gift.domain.product.dto.ProductResponse;
import gift.domain.product.repository.ProductRepository;

public interface ProductService {

    public List<ProductResponse> getAllProducts();

    public ProductResponse getProductById(Long id);

    public ProductResponse addProduct(ProductRequest productRequest);

    public void updateProduct(Long id, ProductRequest productRequest);

    public void deleteProduct(Long id);


}
