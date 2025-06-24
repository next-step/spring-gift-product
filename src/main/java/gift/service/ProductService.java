package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        return new ProductResponseDto(product);
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = new Product(null, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
        Product saved = productRepository.save(product);
        return new ProductResponseDto(saved);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }

        Product product = optional.get();
        product.update(requestDto);
        return new ProductResponseDto(product);
    }

    public void deleteProduct(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        productRepository.deleteById(id);
    }

    public List<ProductResponseDto> getProductList(int page, int size) {
        List<Product> all = productRepository.findAll();

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, all.size());

        if (fromIndex >= all.size()) {
            return List.of();
        }

        return all.subList(fromIndex, toIndex).stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}
