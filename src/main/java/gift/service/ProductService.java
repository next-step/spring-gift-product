package gift.service;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import gift.repository.ProductRepositoryImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepositoryImp productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> findAllProducts() {

        return productRepository.findAllProducts()
                .stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    public ProductResponseDto findProductById(Long id) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(id);

        return ProductResponseDto.from(findProduct);
    }

    public ProductResponseDto createProduct(String name, Long price, String imageUrl) {
        Product createdProduct = productRepository.createProduct(name, price, imageUrl);

        return ProductResponseDto.from(createdProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(id);
        productRepository.deleteProduct(id);
    }

    @Transactional
    public void patchProduct(Long id, String name, Long price, String imageUrl) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(id);

        if (name != null) {
            findProduct.setName(name);
        }

        if (price != null) {
            findProduct.setPrice(price);
        }

        if (imageUrl != null) {
            findProduct.setImageUrl(imageUrl);
        }
    }

    @Transactional
    public void updateProduct(Long id, String name, Long price, String imageUrl) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(id);
        findProduct.updateProduct(name, price, imageUrl);
    }
}
