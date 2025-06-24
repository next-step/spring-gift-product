package gift.repository;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    //DB 역할을 해줄 Map
    private final Map<Long, Product> products = new HashMap<>();
    
    @Override
    public AddProductResponseDto addProduct(AddProductRequestDto requestDto) {
        
        Product product = new Product(
            requestDto.getId(),
            requestDto.getName(),
            requestDto.getPrice(),
            requestDto.getImageUrl()
        );
        
        Long dbId = (long) products.size() + 1;
        products.put(dbId, product);
        
        return new AddProductResponseDto(dbId, product);
    }
}
