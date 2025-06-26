package gift.controller;

import gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    private final RestTemplate restTemplate;

    @Autowired
    public AdminProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/products")
    public String productList(Model model) {
        try {
            ResponseEntity<List<Product>> response = restTemplate.exchange(
                    "http://localhost:8080/api/products",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Product>>() {}
            );

            List<Product> products = response.getBody();
            if (products == null) {
                products = Collections.emptyList();
            }
            model.addAttribute("products", products);
        } catch (Exception e) {
            System.err.println("관리자 화면: 상품 목록 API 호출 중 오류 발생: " + e.getMessage());
            model.addAttribute("products", Collections.emptyList()); // 오류 발생 시 빈 리스트 전달
        }

        return "admin/product_list";
    }
}