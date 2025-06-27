package gift.product.service;

import gift.common.dto.PagedResult;
import gift.common.exception.ErrorCode;
import gift.product.domain.Product;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.GetProductResDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.exception.ProductNotFoundException;
import gift.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public PagedResult<GetProductResDto> getAllProducts(int page, int size, String sortField,
      boolean ascending) throws IllegalArgumentException {
    PagedResult<Product> pagedResult = productRepository.findAll(page, size, sortField, ascending);

    List<GetProductResDto> dtoList = pagedResult.content().stream()
        .map(GetProductResDto::from).toList();

    return PagedResult.from(dtoList, pagedResult);
  }

  public GetProductResDto getProductById(Long id) throws ProductNotFoundException {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(ErrorCode.NOT_FOUND));
    return GetProductResDto.from(product);
  }

  public Long createProduct(CreateProductReqDto dto) {
    Product newProduct = new Product(null, dto.name(), dto.price(), dto.description());
    return productRepository.save(newProduct);
  }

  public void updateProduct(Long id, UpdateProductReqDto dto) throws ProductNotFoundException {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(ErrorCode.NOT_FOUND));
    product.updateProduct(dto);
    productRepository.update(id, product);
  }

  public void deleteProduct(Long id) throws ProductNotFoundException {
    Product deletedProduct = productRepository.delete(id);
    if (deletedProduct == null) {
      throw new ProductNotFoundException(ErrorCode.NOT_FOUND);
    }
  }
}
