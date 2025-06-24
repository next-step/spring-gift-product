package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductSaveReqDTO;
import gift.dto.response.ProductResDTO;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public ProductResDTO save(ProductSaveReqDTO productSaveReqDTO) {
        return ProductResDTO.toDTO(
            productRepository.save(
                new Product(
                    productSaveReqDTO.name(),
                    productSaveReqDTO.price(),
                    productSaveReqDTO.imageURL()
                )
            )
        );
    }

    public ProductResDTO findById(Long id) {
        return ProductResDTO.toDTO(
            productRepository.findById(id)
        );
    }
}
