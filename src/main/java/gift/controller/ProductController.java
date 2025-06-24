package gift.controller;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.dto.ModifyProductRequestDto;
import gift.dto.ModifyProductResponseDto;
import gift.service.ProductService;
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
@RequestMapping("api/product")
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
    
    //상품 단건 조회 api (dbId)
    @GetMapping("{id}")
    public ResponseEntity<FindProductResponseDto> findProductWithDbId(
        @PathVariable Long id
    ) {
        FindProductResponseDto responseDto = productService.findProductWithDbId(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    //상품 전체 수정 api (dbId)
    @PutMapping("{id}")
    public ResponseEntity<ModifyProductResponseDto> modifyProductWithDbId(
        @PathVariable Long id,
        @RequestBody ModifyProductRequestDto requestDto
    ) {
        ModifyProductResponseDto responseDto = productService.modifyProductWithDbId(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProductWithDbId(
        @PathVariable Long id
    ) {
        productService.deleteProductWithDbId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
