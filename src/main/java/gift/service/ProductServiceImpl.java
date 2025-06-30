package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> findAllProducts(){

        List<ProductResponseDto> findList = productRepository.findAllProducts();

        return findList;
    }

    @Override
    public ProductResponseDto findProductById(Long id) {

        Optional<Product> product = productRepository.findProductById(id);

        if (product.isEmpty())
            return null;

        Product findProduct = product.get();
        return new ProductResponseDto(
                findProduct.getId(),
                findProduct.getName(),
                findProduct.getPrice(),
                findProduct.getImageUrl());
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto dto) {

        String name = dto.getName();
        Long price = dto.getPrice();
        String imageUrl = dto.getImageUrl();

        Product product = productRepository.saveProduct(name, price, imageUrl);

        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Transactional
    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {

        Product updatedProduct = productRepository.updateProduct(
                id,
                dto.getName(),
                dto.getPrice(),
                dto.getImageUrl()
        );

        return new ProductResponseDto(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getImageUrl()
        );
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteProduct(id);
    }
}
