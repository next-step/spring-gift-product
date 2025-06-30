package gift.product.service;

import gift.product.domain.Product;
import gift.product.dto.ProductRequestDto;
import gift.product.exception.ProductNotFoundException;
import gift.product.repository.MemoryProductRepository;
import gift.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repository;

    public ProductService(MemoryProductRepository repository){
        this.repository = repository;
    }

    public Product saveProduct(ProductRequestDto requestDto){

        return repository.save(requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }

    public List<Product> getProducts(){
        // 전체 조회 (page 등 추후 구현 필요)
        return repository.findAll();
    }

    public Product getProduct(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("상품을 찾을 수 없습니다. ID: " + id));
    }

    public void update(Long id, ProductRequestDto requestDto){
        Optional<Product> product = repository.findById(id);

        if (product.isEmpty()){
            throw new ProductNotFoundException("상품을 찾을 수 없습니다. ID: " + id);
        }

        product.get().update(requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }

    public void delete(Long id){
        // 존재하지 않으면 무시
        repository.deleteById(id);
    }
}
