package gift.repository;

import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.dto.ModifyProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    //DB 역할을 해줄 Map
    private final Map<Long, Product> products = new HashMap<>();
    
    @Override
    public AddProductResponseDto addProduct(Product product) {
        
        Long dbId = product.getId();
        products.put(dbId, product);
        
        return new AddProductResponseDto(product);
    }
    
    @Override
    public List<FindProductResponseDto> findAllProducts() {
        List<FindProductResponseDto> responseDtoList = new ArrayList<>();
        for (Product product : products.values()) {
            responseDtoList.add(
                new FindProductResponseDto(product)
            );
        }
        return responseDtoList;
    }
    
    @Override
    public Product findProductWithDbId(Long id) {
        return Optional.ofNullable(products.get(id))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
    @Override
    public ModifyProductResponseDto modifyProductWithDbId(Long id, Product newProduct) {
        products.put(id, newProduct);
        return new ModifyProductResponseDto(id, newProduct);
    }
    
    @Override
    public void deleteProductWithDbId(Long id) {
        products.remove(id);
    }
    
    @Override
    public Long getRecentId() {
        return (long) products.size() + 1;
    }
}
