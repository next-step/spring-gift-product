package gift.repository;

import gift.dto.ProductResponseDto;
import java.util.List;

public interface ProductRepository {

    List<ProductResponseDto> findAllProducts();

}
