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

        Product savedProduct = productRepository.saveProduct(product);

        return new ProductCreateResponseDto(savedProduct);
    }

    @Override
    public List<ProductGetResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public ProductGetResponseDto findProductByProductId(Long productId) {
        Product product = productRepository.findProductByProductId(productId);

        // TODO: id가 없을 때 404에러가 아니라 500이 뜬다.

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Does not exist productId = " + productId);
        }

        return new ProductGetResponseDto(product);
    }

    @Override
    public void updateProductByProductId(Long productId, String name,
        Double price,
        String imageUrl) {
        int updatedProductRows = productRepository.updateProductByProductId(productId, name, price,
            imageUrl);

        if (updatedProductRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Failed to update productId = " + productId);
        }
    }

    @Override
    public void deleteProductByProductId(Long productId) {
        int deletedProductRows = productRepository.deleteProductByProductId(productId);

        if (deletedProductRows == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Failed to delete productId = " + productId);
        }
    }
}
