package gift.service;

import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import gift.dto.response.ProductDeleteResponseDto;
import gift.dto.response.ProductGetResponseDto;
import gift.dto.response.ProductUpdateResponseDto;
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
    public ProductUpdateResponseDto updateProductByProductId(Long productId, String name,
        Double price,
        String imageUrl) {
        Product product = productRepository.findProductByProductId(productId);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Does not exist productId = " + productId);
        }

        if (name == null || price == null || imageUrl == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "name, price, imageUrl must not be null.");
        }

        // 메모리 상(products)에 존재하는 Product를 직접 수정하기 때문에, 데이터베이스 접근을 하지 않았다.
        product.update(name, price, imageUrl);

        return new ProductUpdateResponseDto(product);
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
