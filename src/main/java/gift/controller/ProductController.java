package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(
            @RequestBody ProductRequestDto requestDto){
        ProductResponseDto responseDto = productService.addProduct(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        List<ProductResponseDto> responseDtos = productService.getProducts();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id){
        try{
            ProductResponseDto responseDto = productService.getProduct(id);
            return ResponseEntity.ok(responseDto);
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto requestDto) {
        try {
            ProductResponseDto responseDto = productService.updateProduct(id, requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
