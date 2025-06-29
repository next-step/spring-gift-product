package gift.service;

import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductCreateResponseDto saveProduct(ProductCreateRequestDto productCreateRequestDto) {

        Product product = new Product(productCreateRequestDto.name(),
            productCreateRequestDto.price(), productCreateRequestDto.imageUrl());

        productRepository.saveProduct(product);

        return new ProductCreateResponseDto(product);
    }

    @Override
    public List<ProductGetResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public ProductGetResponseDto findProductById(Long productId) {
        return productRepository.findProductById(productId)
            .map(ProductGetResponseDto::new)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Does not exist productId = " + productId)
            );
    }

    @Override
    public void updateProductById(Long productId, String name, Double price, String imageUrl) {

        Product product = new Product(productId, name, price, imageUrl);

        productRepository.updateProductById(product);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteProductById(productId);
    }
}
