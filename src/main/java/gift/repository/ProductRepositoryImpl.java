package gift.repository;

import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.dto.ModifyProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.Collections;
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
    public Product findProductWithId(Long id) {
        return Optional.ofNullable(products.get(id))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
    @Override
    public ModifyProductResponseDto modifyProductWithId(Long id, Product newProduct) {
        products.put(id, newProduct);
        return new ModifyProductResponseDto(newProduct);
    }
    
    @Override
    public void deleteProductWithId(Long id) {
        products.remove(id);
    }
    
    @Override
    public Long getRecentId() {
        if (products.isEmpty()) {
            return 1L;
        }
        return Collections.max(products.keySet()) + 1;
    }
    //기존 구현은 db size 의존 -> 겹치는 id 생성 가능
    //어떻게 할 것인가?
    //key 목록에서 가장 큰 숫자 + 1로 하면 되지 않을까?
}
