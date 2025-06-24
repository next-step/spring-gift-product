package gift.product.service;

import gift.product.domain.Product;
import gift.product.dto.ProductRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    // 동시성 문제 (HashMap -> ConcurrentHashMap)
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    // id 생성 용도로 AtomicLong 사용
    private final AtomicLong sequence = new AtomicLong(0);


    /**
     * 상품 등록
     * 
     * @param requestDto 
     * @return
     */
    public Product save(ProductRequestDto requestDto){
        Product product = requestDto.toProduct();
        long pId = sequence.incrementAndGet();
        product.setId(pId);

        return product;
    }


    /**
     * 상품 전체 조회
     * 
     * @return 
     */
    public List<Product> findAll(){
        // 전체 조회 (page 등 추후 구현 필요)
        return products.values()
                .stream()
                .toList();
    }

    /**
     * 상품 단일 조회
     * 
     * @param id 
     * @return
     */
    public Product findById(Long id){
        Product product = products.get(id);
        if (product == null){
            // ProductNotFoundException 구현 필요
            throw new RuntimeException("상품을 찾을 수 없습니다. ID: " + id);
        }

        return product;
    }

    /**
     * 상품 업데이트
     * 
     * @param id 
     * @param requestDto
     */
    public void update(Long id, ProductRequestDto requestDto){
        Product product = products.get(id);
        product.update(requestDto.name(), requestDto.price(), requestDto.imageUrl());
        products.put(id ,product);
    }

    /**
     * 상품 삭제
     * 
     * @param id 
     */
    public void delete(Long id){
        // 존재하지 않으면 무시
        products.remove(id);
    }
}
