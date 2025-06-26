package gift.product.service;

import gift.product.domain.Product;
import gift.product.dto.ProductRequestDto;
import gift.product.exception.ProductNotFoundException;
import gift.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }


    /**
     * 상품 등록
     * 
     * @param requestDto 
     * @return
     */
    public Product saveProduct(ProductRequestDto requestDto){

        return repository.save(requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }


    /**
     * 상품 전체 조회
     * 
     * @return 
     */
    public List<Product> getProducts(){
        // 전체 조회 (page 등 추후 구현 필요)
        return repository.findAll();
    }

    /**
     * 상품 단일 조회
     * 
     * @param id 
     * @return
     */
    public Product getProduct(Long id){
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()){
            throw new ProductNotFoundException("상품을 찾을 수 없습니다. ID: " + id);
        }

        return product.get();
    }

    /**
     * 상품 업데이트
     * 
     * @param id 
     * @param requestDto
     */
    public void update(Long id, ProductRequestDto requestDto){
        Optional<Product> product = repository.findById(id);

        if (product.isEmpty()){
            throw new ProductNotFoundException("상품을 찾을 수 없습니다. ID: " + id);
        }

        product.get().update(requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }

    /**
     * 상품 삭제
     * 
     * @param id 
     */
    public void delete(Long id){
        // 존재하지 않으면 무시
        repository.deleteById(id);
    }
}
