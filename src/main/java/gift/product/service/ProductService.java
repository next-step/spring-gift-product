package gift.product.service;

import gift.common.exception.ErrorCode;
import gift.product.domain.Product;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.GetProductResDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.exception.ProductNotFoundException;
import gift.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<GetProductResDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(GetProductResDto::new).toList();
    }

    public GetProductResDto getProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id);
        return new GetProductResDto(product);
    }

    public Long createProduct(CreateProductReqDto dto) {
        return productRepository.save(dto);
    }

    public void updateProduct(Long id, UpdateProductReqDto dto) throws ProductNotFoundException {
        Product product = productRepository.findById(id);
        product.updateProduct(dto);
        productRepository.update(id,product);
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        boolean result = productRepository.delete(id);
        if (!result) {
            throw new ProductNotFoundException(ErrorCode.NOT_FOUND);
        }
    }
}
