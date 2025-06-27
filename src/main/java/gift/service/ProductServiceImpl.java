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

        int savedProductRows = productRepository.saveProduct(product);

        if (savedProductRows == 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Failed to save product.");
        }

        return new ProductCreateResponseDto(product);
    }

    @Override
    public List<ProductGetResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public ProductGetResponseDto findProductById(Long productId) {
        Product product = productRepository.findProductById(productId);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Does not exist productId = " + productId);
        }

        return new ProductGetResponseDto(product);
    }

    @Override
    public void updateProductById(Long productId, String name,
        Double price,
        String imageUrl) {
        int updatedProductRows = productRepository.updateProductById(productId, name, price,
            imageUrl);

        if (updatedProductRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Failed to update productId = " + productId);
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        int deletedProductRows = productRepository.deleteProductById(productId);

        if (deletedProductRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Failed to delete productId = " + productId);
        }
    }
}
