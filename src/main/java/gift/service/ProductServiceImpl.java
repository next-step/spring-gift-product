package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> findAllProducts(){

        List<ProductResponseDto> findList = productRepository.findAllProducts();

        return findList;
    }

    @Override
    public ProductResponseDto findProductById(Long id) {

        ProductResponseDto findProduct = productRepository.findProductByIdElseThrow(id);

        return findProduct;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto dto) {

        String name = dto.getName();
        Long price = dto.getPrice();
        String imageUrl = dto.getImageUrl();

        return productRepository.saveProduct(name, price, imageUrl);
    }
}
