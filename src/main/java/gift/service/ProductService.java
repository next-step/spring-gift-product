package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private Product findProductByIdFun(Long id) {
        Product product =  productRepository.findById(id);
        if(product == null)
            throw new ProductNotFoundException();
        return product;
    }
    public ProductResponseDto findProductById(Long productId) {
        return findProductByIdFun(productId).toDto();
    }

    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        Product product = productRepository.saveProduct(dto.name(),dto.price(),dto.imageUrl());
        return product.toDto();
    }

    public ProductResponseDto updateProduct(Long productId, ProductRequestDto dto) {
        Product product = findProductByIdFun(productId);
        product.updateProductInfo(dto.name(), dto.price(), dto.imageUrl());
        return product.toDto();
    }

    //가격만 수정하는 것은 꽤 합리적이라고 생각
    public ProductResponseDto updateProductPrice(Long productId, int price) {
        Product product = productRepository.findById(productId);
        if(product == null) {
            throw new ProductNotFoundException();
        }
        product.updatePrice(price);
        return product.toDto();
    }

    public void deleteProduct(Long productId) {
            findProductByIdFun(productId);
            productRepository.deleteById(productId);
    }

    public List<ProductResponseDto> findAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product product : products) {
            result.add(product.toDto());
        }
        return result;
    }

    }
