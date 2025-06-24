package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryProductRepository implements ProductRepository{
    private final Map<Long, Product> productList = new HashMap<>();

    public ProductResponseDto createProduct(Product product){
        Long productId = productList.isEmpty() ? 1 : Collections.max(productList.keySet()) + 1;
        product.setId(productId);

        productList.put(productId, product);

        return new ProductResponseDto(product);
    }
    public List<ProductResponseDto> searchAllProducts(){
        List<ProductResponseDto> allProducts = new ArrayList<>();

        for(Product product : productList.values()){
            ProductResponseDto productResponseDto = new ProductResponseDto(product);
            allProducts.add(productResponseDto);
        }

        return allProducts;
    }
    public Optional<Product> searchProductById(Long id){
        return Optional.ofNullable(productList.get(id));
    }
    public Product updateProduct(Long id, String name, Integer price, String imageUrl){
        Product product = productList.get(id);
        if (product == null) {
            throw new NoSuchElementException("해당 ID의 상품이 존재하지 않습니다.");
        }

        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        return product;
    }
    public void deleteProduct(Long id){
        productList.remove(id);
    }
}
