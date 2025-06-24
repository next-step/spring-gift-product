package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductSaveReqDTO;
import gift.dto.response.ProductSaveResDTO;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public ProductSaveResDTO save(ProductSaveReqDTO productSaveReqDTO) {
        return ProductSaveResDTO.toDTO(
            productRepository.save(
                new Product(
                    productSaveReqDTO.name(),
                    productSaveReqDTO.price(),
                    productSaveReqDTO.imageURL()
                )
            )
        );
    }
}
