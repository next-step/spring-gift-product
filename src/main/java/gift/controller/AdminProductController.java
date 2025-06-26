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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
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

    @GetMapping("/products/{id}/edit")
    public String showEditProductForm(@PathVariable Long id, Model model) {

        try {
            ResponseEntity<Product> response = restTemplate.exchange(
                    "http://localhost:8080/api/products/" + id,
                    HttpMethod.GET,
                    null,
                    Product.class
            );

            Product product = response.getBody();
            if (response.getStatusCode() == HttpStatus.OK && product != null) {
                model.addAttribute("product", product);
                model.addAttribute("formAction", "/admin/products/" + id);
                model.addAttribute("pageTitle", "상품 수정");
                model.addAttribute("_method", "PUT");
                return "admin/product_form";
            } else {
                System.err.println(
                        "관리자 화면: 상품 조회 API에서 상품을 찾을 수 없거나 예상치 못한 응답: " + response.getStatusCode());
                return "redirect:/admin/products?error=상품을 찾을 수 없습니다.";
            }
        } catch (HttpClientErrorException.NotFound e) {
            System.err.println("관리자 화면: 상품 수정 폼 - 상품을 찾을 수 없습니다 (ID: " + id + ")");
            return "redirect:/admin/products?error=수정할 상품을 찾을 수 없습니다.";
        } catch (ResourceAccessException e) {
            System.err.println("관리자 화면: 상품 수정 API 서버에 연결할 수 없습니다: " + e.getMessage());
            return "redirect:/admin/products?error=상품 정보를 가져오는 API 서버에 연결할 수 없습니다.";
        } catch (Exception e) {
            System.err.println("관리자 화면: 상품 수정 폼 로드 중 알 수 없는 오류 발생: " + e.getMessage());
            return "redirect:/admin/products?error=상품 수정 폼 로드 중 오류가 발생했습니다.";
        }
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product,
            Model model) {

        try {
            HttpEntity<Product> request = new HttpEntity<>(product);
            ResponseEntity<Product> response = restTemplate.exchange(
                    "http://localhost:8080/api/products/" + id,
                    HttpMethod.PUT,
                    request,
                    Product.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return "redirect:/admin/products";
            } else {
                System.err.println("관리자 화면: 상품 수정 API에서 예상치 못한 응답 코드: " + response.getStatusCode());
                model.addAttribute("errorMessage", "상품 수정 중 예상치 못한 오류가 발생했습니다.");
                model.addAttribute("product", product);
                model.addAttribute("formAction", "/admin/products/" + id);
                model.addAttribute("pageTitle", "상품 수정");
                model.addAttribute("_method", "PUT");
                return "admin/product_form";
            }
        } catch (HttpClientErrorException e) {
            System.err.println("관리자 화면: 상품 수정 API 호출 중 클라이언트 오류: " + e.getStatusCode() + " - "
                    + e.getResponseBodyAsString());
            model.addAttribute("errorMessage", "상품 수정 실패: " + e.getResponseBodyAsString());
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                model.addAttribute("errorMessage",
                        "입력 데이터가 유효하지 않거나 ID가 불일치합니다. 이름을 채우고, 가격은 양수, URL 형식을 확인하세요.");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                model.addAttribute("errorMessage", "수정하려는 상품을 찾을 수 없습니다.");
            } else if (e.getStatusCode() == HttpStatus.CONFLICT) {
                model.addAttribute("errorMessage",
                        "상품 수정 중 충돌이 발생했습니다.");
            }
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (ResourceAccessException e) {
            System.err.println("관리자 화면: 상품 수정 API 서버에 연결할 수 없습니다: " + e.getMessage());
            model.addAttribute("errorMessage", "상품 수정 API 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인하세요.");
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (Exception e) {
            System.err.println("관리자 화면: 상품 수정 중 알 수 없는 오류 발생: " + e.getMessage());
            model.addAttribute("errorMessage", "상품 수정 중 서버 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        }
    }
}