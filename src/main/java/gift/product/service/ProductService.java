package gift.product.service;

import gift.common.dto.PageRequest;
import gift.common.dto.PagedResult;
import gift.common.exception.ErrorCode;
import gift.product.domain.Product;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.GetProductResDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.exception.ProductNotFoundException;
import gift.product.repository.InMemoryProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final InMemoryProductRepository productRepository;

  public PagedResult<GetProductResDto> getAllByPage(PageRequest pageRequest) throws IllegalArgumentException {
    List<Product> pagedProductList = productRepository.findAll(pageRequest.offset(),
        pageRequest.pageSize(),pageRequest.sortInfo());
    return PagedResult.of(pagedProductList, pageRequest.offset(), pageRequest.pageSize()).map(GetProductResDto::from);
  }

  public GetProductResDto getProductById(Long id) throws ProductNotFoundException {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
    return GetProductResDto.from(product);
  }

  public Long createProduct(CreateProductReqDto dto) {
    Product newProduct = Product.of(
        dto.name(),
        dto.price(),
        dto.description(),
        dto.imageUrl()
    );
    return productRepository.save(newProduct);
  }

  public void updateProduct(Long id, UpdateProductReqDto dto) throws ProductNotFoundException {
    if(productRepository.findById(id).isEmpty()){
      throw new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
    }
    Product newProduct = Product.of(
        dto.name(),
        dto.price(),
        dto.description(),
        dto.imageUrl()
    );
    productRepository.update(id, newProduct);
  }

  public void deleteProduct(Long id) throws ProductNotFoundException {
    if(productRepository.findById(id).isEmpty()){
      throw new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
    }
    productRepository.deleteById(id);
  }
}
