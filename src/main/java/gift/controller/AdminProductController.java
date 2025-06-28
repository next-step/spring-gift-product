package gift.controller;

import gift.model.Product;
import gift.service.ApiErrorMappingService;
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
    private final ApiErrorMappingService apiErrorMappingService;

    @Autowired
    public AdminProductController(RestTemplate restTemplate,
            ApiErrorMappingService apiErrorMappingService) {
        this.restTemplate = restTemplate;
        this.apiErrorMappingService = apiErrorMappingService;
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
            return "redirect:/admin/products?error="
                    + apiErrorMappingService.getErrorMessageForResourceAccess();
        } catch (HttpClientErrorException e) {
            return "redirect:/admin/products?error="
                    + apiErrorMappingService.mapApiErrorToUserMessage(
                    (HttpStatus) e.getStatusCode());
        } catch (Exception e) {
            return "redirect:/admin/products?error="
                    + apiErrorMappingService.getGenericErrorMessage();
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
            String errorMessage = apiErrorMappingService.mapApiErrorToUserMessage(
                    (HttpStatus) e.getStatusCode());
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products");
            model.addAttribute("pageTitle", "새 상품 추가");
            return "admin/product_form";
        } catch (ResourceAccessException e) {
            model.addAttribute("errorMessage",
                    apiErrorMappingService.getErrorMessageForResourceAccess());
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products");
            model.addAttribute("pageTitle", "새 상품 추가");
            return "admin/product_form";
        } catch (Exception e) {
            model.addAttribute("errorMessage",
                    apiErrorMappingService.getGenericErrorMessage() + ": " + e.getMessage());
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
            if (product == null) {
                return "redirect:/admin/products?error="
                        + apiErrorMappingService.getErrorMessageForNotFoundProduct();
            }

            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (HttpClientErrorException e) {
            String errorMessage = apiErrorMappingService.mapApiErrorToUserMessage(
                    (HttpStatus) e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return "redirect:/admin/products?error="
                        + apiErrorMappingService.getErrorMessageForNotFoundProduct();
            } else {
                return "redirect:/admin/products?error=상품 정보를 가져오는 중 API 오류가 발생했습니다: "
                        + e.getStatusCode().value();
            }
        } catch (ResourceAccessException e) {
            return "redirect:/admin/products?error="
                    + apiErrorMappingService.getErrorMessageForResourceAccess();
        } catch (Exception e) {
            return "redirect:/admin/products?error="
                    + apiErrorMappingService.getGenericErrorMessage();
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
            String errorMessage = apiErrorMappingService.mapApiErrorToUserMessage(
                    (HttpStatus) e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                errorMessage = apiErrorMappingService.getErrorMessageForNotFoundProduct();
            }
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (ResourceAccessException e) {
            model.addAttribute("errorMessage",
                    apiErrorMappingService.getErrorMessageForResourceAccess());
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        } catch (Exception e) {
            model.addAttribute("errorMessage",
                    apiErrorMappingService.getGenericErrorMessage() + ": " + e.getMessage());
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/admin/products/" + id);
            model.addAttribute("pageTitle", "상품 수정");
            model.addAttribute("_method", "PUT");
            return "admin/product_form";
        }
    }
}
