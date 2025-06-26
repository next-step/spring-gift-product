package gift.controller;

import gift.model.Product;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
                    new ParameterizedTypeReference<List<Product>>() {
                    }
            );

            List<Product> products = response.getBody();
            if (products == null) {
                products = Collections.emptyList();
            }
            model.addAttribute("products", products);
        } catch (Exception e) {
            System.err.println("관리자 화면: 상품 목록 API 호출 중 오류 발생: " + e.getMessage());
            model.addAttribute("products", Collections.emptyList());
        }

        return "admin/product_list";
    }

    @GetMapping("/products/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("formAction", "/admin/products");
        model.addAttribute("pageTitle", "새 상품 추가");
        return "admin/product_form";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product, Model model) {

        try {
            HttpEntity<Product> request = new HttpEntity<>(product);
            ResponseEntity<Product> response = restTemplate.exchange(
                    "http://localhost:8080/api/products",
                    HttpMethod.POST,
                    request,
                    Product.class
            );

            if (response.getStatusCode() == HttpStatus.CREATED) {
                return "redirect:/admin/products";
            } else {
                System.err.println("관리자 화면: 상품 추가 API에서 예상치 못한 응답 코드: " + response.getStatusCode());
                model.addAttribute("errorMessage", "상품 추가 중 예상치 못한 오류가 발생했습니다.");
                model.addAttribute("product", product);
                model.addAttribute("formAction", "/admin/products");
                model.addAttribute("pageTitle", "새 상품 추가");
                return "admin/product_form";
            }
        } catch (HttpClientErrorException e) {
            System.err.println("관리자 화면: 상품 추가 API 호출 중 클라이언트 오류: " + e.getStatusCode() + " - "
                    + e.getResponseBodyAsString());
            model.addAttribute("errorMessage", "상품 추가 실패: " + e.getResponseBodyAsString());
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                model.addAttribute("errorMessage",
                        "입력 데이터가 유효하지 않습니다. 이름을 채우고, 가격은 양수, URL 형식을 확인하세요.");
            } else if (e.getStatusCode() == HttpStatus.CONFLICT) {
                model.addAttribute("errorMessage", "입력하신 상품 ID가 이미 존재합니다. 다른 ID를 사용해 주세요.");
            }
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products");
            model.addAttribute("pageTitle", "새 상품 추가");
            return "admin/product_form";
        } catch (Exception e) {
            System.err.println("관리자 화면: 상품 추가 중 알 수 없는 오류 발생: " + e.getMessage());
            model.addAttribute("errorMessage", "상품 추가 중 서버 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products");
            model.addAttribute("pageTitle", "새 상품 추가");
            return "admin/product_form";
        }
    }
}