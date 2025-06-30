package gift.service;

import gift.dto.PageResponseDto;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepositoryInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepositoryInterface productRepository;

    public ProductService(@Qualifier("jdbcProductRepository") ProductRepositoryInterface productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {

        long productId = productRepository.getNewProductId();
        Product product = new Product(
                productId,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        Product addedProduct = productRepository.addProduct(product);

        return new ProductResponseDto(addedProduct.getId(), addedProduct.getName(), addedProduct.getPrice(), addedProduct.getImageUrl());
    }

    @Override
    public PageResponseDto getPageProducts(int page, int pageSize) {
        List<Product> productList = productRepository.findAllProducts();
        List<ProductResponseDto> products = new ArrayList<>();
        for (Product product : productList) {
            products.add(new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            ));
        }

        int totalProducts = products.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        if (totalPages == 0){
            totalPages = 1;
        }

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalProducts);

        List<ProductResponseDto> pageProducts = new ArrayList<>();
        if (fromIndex < totalProducts) {
            pageProducts = products.subList(fromIndex, toIndex);
        }

        return new PageResponseDto(page, totalPages, pageProducts);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<Product> productList = productRepository.findAllProducts();
        List<ProductResponseDto> products = new ArrayList<>();
        for (Product product : productList) {
            products.add(new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            ));
        }
        return products;
    }

    @Override
    public Optional<ProductResponseDto> findProductById(Long id) {
        return productRepository.findProductById(id)
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl()
                ));
    }

    @Override
    public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto requestDto) {

        Product product = new Product(
                id,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        return productRepository.updateProduct(id, product)
                .map(updated -> new ProductResponseDto(
                        updated.getId(),
                        updated.getName(),
                        updated.getPrice(),
                        updated.getImageUrl()
                ));
    }

    @Override
    public void deleteProduct(Long id) {
        boolean deleted = productRepository.deleteProduct(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
