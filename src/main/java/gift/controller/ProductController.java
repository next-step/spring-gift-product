package gift.controller;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import gift.dto.response.ProductGetResponseDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductCreateResponseDto> createProduct(
        @Valid @RequestBody ProductCreateRequestDto productCreateRequestDto) {

        return new ResponseEntity<>(productService.saveProduct(productCreateRequestDto),
            HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductGetResponseDto> getProducts() {

        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductGetResponseDto> getProductByproductId(
        @PathVariable Long productId) {

        return new ResponseEntity<>(productService.findProductByProductId(productId),
            HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable Long productId,
        @RequestBody RequestDto requestDto) {

        return new ResponseEntity<>(
            productService.updateProductByProductId(productId, requestDto.name(),
                requestDto.price(),
                requestDto.imageUrl()), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long productId) {

        return new ResponseEntity<>(productService.deleteProductByProductId(productId),
            HttpStatus.OK);
    }
}