package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.nio.channels.ReadPendingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDto> findAll() {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        for (Product product : productList) {
            productResponseDtoList.add(ProductResponseDto.from(product));
        }

        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto requestDto) {
        Long id = productRepository.create(requestDto);

        return new ProductResponseDto(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }

    @Override
    public ProductResponseDto find(Long id) {
        Product product = productRepository.find(id);

        return ProductResponseDto.from(product);
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto requestDto) {
        int updateRowCount = productRepository.update(id, requestDto);

        if (updateRowCount < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new ProductResponseDto(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
