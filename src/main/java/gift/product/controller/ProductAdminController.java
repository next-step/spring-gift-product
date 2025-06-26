package gift.product.controller;

import gift.product.dto.ProductInfoDto;
import gift.product.dto.ProductResponseDto;
import gift.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService){
        this.productService = productService;
    }


    /**
     * 상품 목록 조회
     *
     * @param model
     * @return
     */
    @GetMapping
    public String products(Model model) {
        List<ProductInfoDto> products = productService.getProducts()
                .stream()
                .map(product -> ProductInfoDto.productFrom(product))
                .toList();

        model.addAttribute("products", products);

        return "admin/products";
    }


}
