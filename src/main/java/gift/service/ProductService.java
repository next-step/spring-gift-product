package gift.service;

import gift.dto.request.ProductRequestDto;
import gift.dto.request.ProductUpdateRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepositoryInterface;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepositoryInterface productRepository;

    public ProductService(ProductRepositoryInterface productRespository) {
        this.productRepository = productRespository;
    }

    //상품 단 건 조회
    public Product getProduct(long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    //상품 추가
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(
            productRequestDto.productId(),
            productRequestDto.name(),
            productRequestDto.price(),
            productRequestDto.imageURL());
        productRepository.createProduct(product);

        return product.toResponseDto();
    }

    //상품 수정
    public ProductResponseDto updateProduct(long productId,
        ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = new Product(
            productId,
            productUpdateRequestDto.name(),
            productUpdateRequestDto.price(),
            productUpdateRequestDto.imageURL());
        productRepository.updateProduct(product);

        return product.toResponseDto();
    }

    //상품 삭제
    public void deleteProduct(long productId) {
        productRepository.delete(productId);
    }

    //상품 유무 확인
    public boolean containsProduct(long productId) {
        return productRepository.containsKey(productId);
    }
}
