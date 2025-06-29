package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
        Product saveProduct = productRepository.saveProduct(product);

        return new ProductResponseDto(saveProduct.getId(), saveProduct.getName(), saveProduct.getPrice(), saveProduct.getImageUrl());
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> allProducts = productRepository.findAllProducts();
        return allProducts;
    }


    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {

        Product product = productRepository.findProduct(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다. id = " + id);
        }

        product.changeName(requestDto.getName());
        product.changePrice(requestDto.getPrice());
        product.changImageUrl(requestDto.getImageUrl());

        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
