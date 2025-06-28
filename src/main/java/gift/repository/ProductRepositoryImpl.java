package gift.repository;

import gift.dao.ProductDao;
import gift.entity.Product;
import gift.exception.DBServerException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductDao productDao;

    public ProductRepositoryImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(Long productId) {
        return productDao.findById(productId);
    }

    @Override
    public Product save(Product product) {
        // 제품 ID가 null인 경우 새 제품을 추가
        if (product.getId() == null) {
            Long newId = productDao.insertWithKey(product);
            return productDao.findById(newId);
        }
        // 제품 ID가 있는 경우 기존 제품을 업데이트
        if (productDao.update(product) <= 0) {
            throw new DBServerException("제품을 업데이트하는데 실패했습니다.");
        }
        return productDao.findById(product.getId());
    }

    @Override
    public Product updateNameById(Long productId, String name) {
        // 제품 ID가 존재하는지 확인
        if (productDao.findById(productId) == null) {
            throw new DBServerException("업데이트할 제품이 존재하지 않습니다.");
        }
        // 제품 이름 업데이트
        if (productDao.updateNameById(productId, name) <= 0) {
            throw new DBServerException("제품 이름 업데이트에 실패했습니다.");
        }
        return productDao.findById(productId);
    }

    @Override
    public Product updatePriceById(Long productId, Long price) {
        if (productDao.findById(productId) == null) {
            throw new DBServerException("업데이트할 제품이 존재하지 않습니다.");
        }
        if (productDao.updatePriceById(productId, price) <= 0) {
            throw new DBServerException("제품 가격 업데이트에 실패했습니다.");
        }
        return productDao.findById(productId);
    }

    @Override
    public Product updateImageUrlById(Long productId, String imageUrl) {
        if (productDao.findById(productId) == null) {
            throw new DBServerException("업데이트할 제품이 존재하지 않습니다.");
        }
        if (productDao.updateImageUrlById(productId, imageUrl) <= 0) {
            throw new DBServerException("제품 이미지 URL 업데이트에 실패했습니다.");
        }
        return productDao.findById(productId);
    }

    @Override
    public Boolean deleteById(Long productId) {
        if (productDao.findById(productId) == null) {
            throw new DBServerException("삭제할 제품이 존재하지 않습니다.");
        }
        if (productDao.deleteById(productId) <= 0) {
            throw new DBServerException("제품 삭제에 실패했습니다.");
        }
        return true;
    }
}
