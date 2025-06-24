package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductReqDTO;
import gift.dto.response.ProductResDTO;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public ProductResDTO save(ProductReqDTO productReqDTO) {
        return ProductResDTO.toDTO(
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
        return ProductResDTO.toDTO(
            productRepository.findById(id)
        );
    }

    public ProductResDTO update(Long id, ProductReqDTO productReqDTO) {
        Product product = productRepository.findById(id);

        if (productReqDTO.name() != null) {
            product.setName(productReqDTO.name());
        }
        if (productReqDTO.price() != null) {
            product.setPrice(productReqDTO.price());
        }
        if (productReqDTO.imageURL() != null) {
            product.setImageURL(productReqDTO.imageURL());
        }

        return ProductResDTO.toDTO(
            productRepository.save(product)
        );
    }
}
