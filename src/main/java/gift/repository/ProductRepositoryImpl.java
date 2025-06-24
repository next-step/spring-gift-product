package gift.repository;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    @Override
    public List<FindProductResponseDto> findAllProducts() {
        List<FindProductResponseDto> responseDtoList = new ArrayList<>();
        for (HashMap.Entry<Long, Product> entry : products.entrySet()) {
            responseDtoList.add(new FindProductResponseDto(
                entry.getKey(),
                entry.getValue()
            ));
        }
        return responseDtoList;
    }
}
