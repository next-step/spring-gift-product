package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequest;
import java.util.List;

public interface ProductServiceAdmin {
    public Product getProductByIdAdmin(Long productId);

    public List<Product> getProductListAdmin();

    public void saveAdmin(Product product);

    public void updateAdmin(ProductRequest request);

    public void deleteByIdAdmin(Long productId);
}
