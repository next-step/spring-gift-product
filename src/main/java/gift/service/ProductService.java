package gift.service;

import gift.dto.ProductRequestDto;
<<<<<<< step2
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Product save(ProductRequestDto dto) {
        Product product = dto.toEntity();
        return productRepository.save(product);
    }

    public void update(Long id, ProductRequestDto dto) {
        Product updated = dto.toEntity();
        productRepository.update(id, updated);
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "상품(id=" + id + ")을 찾을 수 없습니다."
            );
        }
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


=======
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Product product = toEntity(productRequestDto);
        Product savedProduct = productRepository.addProduct(product);
        return toDto(savedProduct);
    }

    private Product toEntity(ProductRequestDto dto) {
        return new Product(dto.getName(), dto.getPrice(), dto.getImageUrl());
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }


    public ProductResponseDto findProduct(Long id){
        Product product = productRepository.findProduct(id);
        if(product==null){
            throw new NoSuchElementException("product does not exist.");
        }
        return new ProductResponseDto(product.getId(), product.getName(),product.getPrice(),product.getImageUrl());
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto){
        Product product = productRepository.findProduct(id);
        if(product==null){
            throw new NoSuchElementException("product does not exist.");
        }
        //db저장
        product.updateProduct(productRequestDto.getName(),productRequestDto.getPrice(),productRequestDto.getImageUrl());
        Product updateProduct = productRepository.updateProduct(product);

        return new ProductResponseDto(updateProduct.getId(),updateProduct.getName(),updateProduct.getPrice(),updateProduct.getImageUrl());
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findProduct(id);
        if(product==null){
            throw new NoSuchElementException("Product does not exist.");
        }
        productRepository.deleteProduct(id);
    }
>>>>>>> hhseo9519
}
