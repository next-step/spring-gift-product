package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductResponseDto> findAll();

    ProductResponseDto create(ProductRequestDto requestDto);

    ProductResponseDto findById(Long id);

    ProductResponseDto update(Long id, ProductRequestDto requestDto);

    void delete(Long id);
}
