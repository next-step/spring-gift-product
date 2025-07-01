package gift.controller;

import gift.dto.ProductCreateResponse;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 조회
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(
        @PathVariable Long productId
    ) {
        return productService.getProductById(productId);
    }

    // 상품 목록 조회
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductList() {
        return productService.getProductList();
    }

    // 상품 생성
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductCreateResponse createProduct(
        @RequestBody ProductRequest request
    ) {
        Long id = productService.insert(request);

        return new ProductCreateResponse(id);
    }

    // 상품 수정
    // API 명세서에는 PathVariable로 productId를 받아오도록 명시되어있어 그대로 구현했지만,
    // 데이터베이스를 연결하지 않은 상태에서는 상품 생성 요청을 보낼 때
    // 요청 dto에 상품 id까지 담겨서 오는 것을 가정하겠습니다.
    @PatchMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(
        @RequestBody ProductRequest request,
        @PathVariable Long productId
    ) {
        productService.update(request);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(
        @PathVariable Long productId
    ) {
        productService.deleteById(productId);
    }

}

