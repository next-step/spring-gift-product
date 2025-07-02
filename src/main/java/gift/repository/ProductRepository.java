package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository("memProductRepository")
public class ProductRepository implements ProductRepositoryInterface {
    private final Map<Long, Product> products = new HashMap<>();
    private long productId = 1L;

    @Override
    public long getNewProductId() {
        return productId;
    }

    @Transactional
    @Override
    public Product addProduct(Product product) {

        product.setId(productId);
        products.put(productId, product);
        productId++;
        Product addedProduct = new Product(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );

        return addedProduct;
    }

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }


    @Override
    public List<Product> findProductsByPage(int offset, int limit) {
        List<Product> productList = new ArrayList<>(products.values());
        if (offset < 0) offset = 0;
        int toIndex = Math.min(offset + limit, productList.size());
        if (offset > toIndex) {
            return new ArrayList<>();
        }


        List<Product> pageProducts = productList.subList(offset, toIndex);

        return pageProducts;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        Product product = products.get(id);
        if (product != null) {
            return Optional.of(new Product(
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
    public Optional<Product> updateProduct(Long id, Product product) {
        Product originProduct = products.get(id);
        if (originProduct != null) {
            originProduct.setName(product.getName());
            originProduct.setPrice(product.getPrice());
            originProduct.setImageUrl(product.getImageUrl());
            return Optional.of(new Product(
                    id,
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

    @Override
    public int countAllProducts() {
        return products.size();
    }

}
