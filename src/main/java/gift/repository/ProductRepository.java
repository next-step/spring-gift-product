package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private final Map<Long, Product> products = new HashMap<>();
    private long productId = 1L;

    @Override
    public long getNewProductId() {
        return productId;
    }

    @Transactional
    @Override
    public ProductResponseDto addProduct(Product product) {

        products.put(productId, product);
        productId++;
        ProductResponseDto responseDto = new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );

        return responseDto;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> productList = new ArrayList<>();
        for (Product product : products.values()) {
            productList.add(new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            ));
        }
        return productList;
    }

    @Override
    public Optional<ProductResponseDto> findProductById(Long id) {
        Product product = products.get(id);
        if (product != null) {
            return Optional.of(new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            ));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(requestDto.getName());
            product.setPrice(requestDto.getPrice());
            product.setImageUrl(requestDto.getImageUrl());
            return Optional.of(new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            ));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (products.containsKey(id)) {
            products.remove(id);
            return true;
        } else {
            return false;
        }
    }

}
