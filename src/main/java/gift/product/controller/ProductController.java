package gift.product.controller;

import gift.common.Response;
import gift.product.domain.Product;
import gift.product.dto.GetProductResDto;
import gift.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
