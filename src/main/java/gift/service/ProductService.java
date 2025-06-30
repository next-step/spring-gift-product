package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductReqDTO;
import gift.dto.response.ProductResDTO;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResDTO save(ProductReqDTO productReqDTO) {
        return convertToDTO(
            productRepository.save(
                new Product(
                    productReqDTO.name(),
                    productReqDTO.price(),
                    productReqDTO.imageURL()
                )
            )
        );
    }

    public ProductResDTO findById(Long id) {
        return convertToDTO(
            productRepository.findById(id)
        );
    }

    public List<ProductResDTO> findAllProducts() {
        return productRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .toList();
    }

    public ProductResDTO update(Long id, ProductReqDTO productReqDTO) {
        Product product = new Product(
            productReqDTO.name(),
            productReqDTO.price(),
            productReqDTO.imageURL()
        );
        int result = productRepository.update(id, product);

        if (result == 1) {
            return convertToDTO(
                productRepository.findById(id)
            );
        }

        throw new RuntimeException("상품 수정에 실패하였습니다.");
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    private ProductResDTO convertToDTO(Product product) {
        return new ProductResDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageURL()
        );
    }
}
