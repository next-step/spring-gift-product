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
            model.addAttribute("products", products != null ? products : Collections.emptyList());
            return "admin/product_list";
        } catch (ResourceAccessException e) {
            // Direct redirect for connection issues
            return "redirect:/admin/products?error=API 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인하세요.";
        } catch (HttpClientErrorException e) {
            // Catch other HTTP client errors for list view
            return "redirect:/admin/products?error=" + mapApiErrorToUserMessage(
                    (HttpStatus) e.getStatusCode());
        } catch (Exception e) {
            // Catch all other unexpected errors
            return "redirect:/admin/products?error=상품 데이터를 가져오는 중 알 수 없는 오류가 발생했습니다.";
        }
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
            restTemplate.exchange(
                    "http://localhost:8080/api/products",
                    HttpMethod.POST,
                    request,
                    Product.class
            );
            return "redirect:/admin/products";
        } catch (HttpClientErrorException e) {
            String errorMessage = mapApiErrorToUserMessage((HttpStatus) e.getStatusCode());
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products");
            model.addAttribute("pageTitle", "새 상품 추가");
            return "admin/product_form";
        } catch (ResourceAccessException e) {
            model.addAttribute("errorMessage", "API 서버에 연결할 수 없습니다. 서버가 실행 중인지 확인하세요.");
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products");
            model.addAttribute("pageTitle", "새 상품 추가");
            return "admin/product_form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 추가 중 예상치 못한 오류가 발생했습니다: " + e.getMessage());
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
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (HttpClientErrorException e) {
            // Catch all HttpClientErrorException (including NotFound)
            String errorMessage = mapApiErrorToUserMessage((HttpStatus) e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Specific redirect for 404
                return "redirect:/admin/products?error=수정할 상품을 찾을 수 없습니다.";
            } else {
                // Other 4xx errors redirect to list with generic API error
                return "redirect:/admin/products?error=상품 정보를 가져오는 중 API 오류가 발생했습니다: "
                        + e.getStatusCode().value();
            }
        } catch (ResourceAccessException e) {
            // Catch connection issues for redirecting
            return "redirect:/admin/products?error=상품 정보를 가져오는 API 서버에 연결할 수 없습니다.";
        } catch (Exception e) {
            // Catch all other unexpected errors for redirecting
            return "redirect:/admin/products?error=상품 수정 폼 로드 중 알 수 없는 오류가 발생했습니다.";
        }
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product,
            Model model) {
        try {
            HttpEntity<Product> request = new HttpEntity<>(product);
            restTemplate.exchange(
                    "http://localhost:8080/api/products/" + id,
                    HttpMethod.PUT,
                    request,
                    Product.class
            );
            return "redirect:/admin/products";
        } catch (HttpClientErrorException e) {
            String errorMessage = mapApiErrorToUserMessage((HttpStatus) e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                errorMessage = "수정하려는 상품을 찾을 수 없습니다.";
            }
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (ResourceAccessException e) {
            model.addAttribute("errorMessage", "API 서버에 연결할 수 없거나 상품 수정 중 오류가 발생했습니다.");
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 예상치 못한 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        }
    }

    // Helper method for mapping API error statuses to user-friendly messages
    private String mapApiErrorToUserMessage(HttpStatus status) {
        if (status == HttpStatus.BAD_REQUEST) {
            return "입력 데이터가 유효하지 않습니다. 이름을 채우고, 가격은 양수, URL 형식을 확인하세요.";
        } else if (status == HttpStatus.CONFLICT) {
            return "이미 존재하는 ID입니다. 다른 ID를 사용해 주세요.";
        } else if (status == HttpStatus.NOT_FOUND) {
            return "요청한 리소스를 찾을 수 없습니다.";
        } else {
            return "API 서버에서 오류가 발생했습니다. (상태 코드: " + status.value() + ")";
        }
    }
}
