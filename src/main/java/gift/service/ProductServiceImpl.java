package gift.service;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import gift.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. 상품 등록
    @Override
    public ResponseDto create(RequestDto dto) {
        Product product = new Product(null, dto.getName());

        Product saved = productRepository.save(product);

        return new ResponseDto(saved);
    }

    // 2-1. 전체 상품 조회
    @Override
    public List<ResponseDto> findAll() {
        List<Product> products = productRepository.findAll();

        List<ResponseDto> dtoList = new ArrayList<>();

        for (Product product : products) {
            dtoList.add(new ResponseDto(product));
        }

        return dtoList;
    }

    // 2-2. 특정 상품 조회
    @Override
    public ResponseDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id = " + id));

        return new ResponseDto(product);
    }

    // 3. 상품 수정
    @Override
    public ResponseDto update(Long id, RequestDto dto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id = " + id));

        product.update(dto);
        Product updated = productRepository.save(product);
        return new ResponseDto(updated);
    }

    // 4. 상품 삭제
    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 id = " + id));

        productRepository.deleteById(id);
    }
}
