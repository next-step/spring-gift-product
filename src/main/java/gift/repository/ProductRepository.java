package gift.repository;

import gift.dto.ResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);

    List<ResponseDto> findAllProducts();

    Product findProductByProductId(Long productId);

    Product deleteProductByProductId(Long productId);
}
