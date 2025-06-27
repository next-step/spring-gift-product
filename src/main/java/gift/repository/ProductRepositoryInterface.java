package gift.repository;

import gift.dto.response.ProductResponseDto;
import gift.entity.Product;
import java.util.Collection;
import java.util.Optional;


public interface ProductRepositoryInterface {

  public Optional<Product> findById(long productId);

  public void add(Product product);

  public void update(Product product);

  public void delete(long productId);

  public Collection<ProductResponseDto> findAll();

  public boolean containsKey(long id);

}
