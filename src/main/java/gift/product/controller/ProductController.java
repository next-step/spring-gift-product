package gift.product.controller;

import gift.common.Response;
import gift.common.dto.PagedResult;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.GetProductResDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public Response<GetProductResDto> getProductById(@PathVariable Long productId) {
        GetProductResDto dto = productService.getProductById(productId);
        return Response.ok(dto, "find product success");
    }

    @GetMapping
    public Response<PagedResult<GetProductResDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ){
        //sort 파라미터 파싱
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        boolean ascending = sortParams.length < 2 || sortParams[1].equalsIgnoreCase("asc");

        PagedResult<GetProductResDto> pagedResult = productService.getAllProducts(page, size, sortField, ascending);
        return Response.ok(pagedResult, "find product list success");
    }

    @PostMapping
    public Response<Long> createProduct(@Valid @RequestBody CreateProductReqDto dto) {
        Long productId = productService.createProduct(dto);
        return Response.ok(productId, "create product success");
    }

    @PutMapping("/{id}")
    public Response<Void> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductReqDto dto) {
        productService.updateProduct(id, dto);
        return Response.ok(null, "update product success");
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return Response.ok(null, "delete product success");
    }

}
