package gift.controller;

import gift.dto.api.AddProductRequestDto;
import gift.dto.api.AddProductResponseDto;
import gift.dto.api.FindProductResponseDto;
import gift.dto.api.ModifyProductRequestDto;
import gift.dto.api.ModifyProductResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
    
    private final ProductService productService;
    
    //생성자 주입
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    //상품 추가 api
    @PostMapping
    public ResponseEntity<AddProductResponseDto> addProduct(
        @RequestBody AddProductRequestDto requestDto
    ) {
        AddProductResponseDto responseDto = productService.addProduct(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    
    //상품 전체 조회 api
    @GetMapping
    public ResponseEntity<List<FindProductResponseDto>> findAllProducts() {
        List<FindProductResponseDto> responseDtoList = productService.findAllProducts();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }
    
    //상품 단건 조회 api
    @GetMapping("{id}")
    public ResponseEntity<FindProductResponseDto> findProductWithId(
        @PathVariable Long id
    ) {
        FindProductResponseDto responseDto = productService.findProductWithId(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    //상품 전체 수정 api
    @PutMapping("{id}")
    public ResponseEntity<ModifyProductResponseDto> modifyProductWithId(
        @PathVariable Long id,
        @RequestBody ModifyProductRequestDto requestDto
    ) {
        ModifyProductResponseDto responseDto = productService.modifyProductWithId(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    //상품 일부 수정 api
    @PatchMapping("{id}")
    public ResponseEntity<ModifyProductResponseDto> modifyProductInfoWithId(
        @PathVariable Long id,
        @RequestBody ModifyProductRequestDto requestDto
    ) {
        ModifyProductResponseDto responseDto = productService.modifyProductInfoWithId(id,
            requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    //상품 단건 삭제 api
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProductWithId(
        @PathVariable Long id
    ) {
        productService.deleteProductWithId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
