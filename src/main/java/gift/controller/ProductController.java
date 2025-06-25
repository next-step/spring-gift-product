package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        List<ProductResponseDto> responseDtos = productService.getProducts();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id){
        try{
            ProductResponseDto responseDto = productService.getProduct(id);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDto requestDto) {
        try {
            ProductResponseDto responseDto = productService.updateProduct(id, requestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 성공 시 204
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 실패 시 404
        }
    }

}
