package gift.product.service;

import gift.domain.Product;
import gift.global.exception.NotFoundProductException;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductResponse;
import gift.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV1 implements ProductService{

    private final ProductRepository productRepository;

    public PostServiceV1(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public String addProduct(ProductCreateRequest dto) {
        return productRepository.addProduct(dto);
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream().map(ProductResponse::new)
                .toList();
    }


    public ProductResponse findProduct(String id) {
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundProductException("상품이 존재하지 않습니다."));
        return new ProductResponse(findProduct);
    }
}
