package gift.product.controller;

import gift.common.Response;
import gift.product.domain.Product;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.GetProductResDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public Response<GetProductResDto> getProductById(@PathVariable Long productId) {
        GetProductResDto dto = productService.getProductById(productId);
        return Response.ok(dto, "find product success");
    }

    @GetMapping
    public Response<List<GetProductResDto>> getAllProducts() {
        List<GetProductResDto> allProducts = productService.getAllProducts();
        return Response.ok(allProducts, "find product list success");
    }

    @PostMapping
    public Response<Long> createProduct(@RequestBody CreateProductReqDto product) {
        Long productId = productService.createProduct(product);
        return Response.ok(productId, "create product success");
    }

    @PutMapping("/{id}")
    public Response<Void> updateProduct(@PathVariable Long id, @RequestBody UpdateProductReqDto dto) {
        productService.updateProduct(id, dto);
        return Response.ok(null, "update product success");
    }



}
