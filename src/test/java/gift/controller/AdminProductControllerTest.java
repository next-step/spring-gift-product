package gift.controller;

import static org.assertj.core.api.Assertions.assertThat;

import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class AdminProductControllerTest {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // 초기화
        List<Product> all = productRepository.findAll();
        all.forEach(p -> productRepository.deleteById(p.getId()));

        baseUrl = "http://localhost:" + port + "/admin/products";
    }

    @Test
    void listPage_ShouldReturnProductsInModel() {
        productRepository.save(new Product(null, "A", 10, "uA"));
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("상품 목록");
        assertThat(response.getBody()).contains("A");
    }

    @Test
    void createFormPage_ShouldReturnFormHtml() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/new", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("신규 상품 등록");
        assertThat(response.getBody()).contains("name=\"name\"");
    }

    @Test
    void createProduct_Success_and_MissingField_Fail() {
        // 성공
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("name", "NewProd");
        form.add("price", "100");
        form.add("imageUrl", "http://img");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<String> resp = restTemplate.postForEntity(baseUrl, entity, String.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(resp.getHeaders().getLocation().toString()).endsWith("/admin/products");

        // 실패: 누락(name)
        MultiValueMap<String, String> badForm = new LinkedMultiValueMap<>();
        badForm.add("price", "0");
        badForm.add("imageUrl", "");
        HttpEntity<MultiValueMap<String, String>> badEntity = new HttpEntity<>(badForm, headers);
        ResponseEntity<String> fail = restTemplate.postForEntity(baseUrl, badEntity, String.class);
        assertThat(fail.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(fail.getBody()).contains("상품 이름은 필수 입력값입니다");
    }

    @Test
    void editFormPage_and_UpdateProduct_Success_and_NotFound() {
        Product saved = productRepository.save(new Product(null, "EProd", 20, "uE"));
        Long id = saved.getId();

        // 수정 폼
        ResponseEntity<String> formResp = restTemplate.getForEntity(baseUrl + "/" + id + "/edit",
                String.class);
        assertThat(formResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(formResp.getBody()).contains("상품 수정");

        // 성공 업데이트
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("name", "EProdX");
        form.add("price", "30");
        form.add("imageUrl", "uX");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);
        ResponseEntity<String> updateResp = restTemplate.exchange(
                baseUrl + "/" + id, HttpMethod.PUT, entity, String.class);
        assertThat(updateResp.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(productRepository.findById(id).get().getName()).isEqualTo("EProdX");

        // 실패 업데이트
        HttpEntity<MultiValueMap<String, String>> badEntity = new HttpEntity<>(form, headers);
        ResponseEntity<String> notFound = restTemplate.exchange(
                baseUrl + "/999", HttpMethod.PUT, badEntity, String.class);
        assertThat(notFound.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteProduct_Success_and_NotFound() {
        Product d = productRepository.save(new Product(null, "DelP", 50, "uD"));
        Long id = d.getId();

        ResponseEntity<String> delResp = restTemplate.exchange(
                baseUrl + "/" + id, HttpMethod.DELETE, null, String.class);
        assertThat(delResp.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(productRepository.existsById(id)).isFalse();

        ResponseEntity<String> notFound = restTemplate.exchange(
                baseUrl + "/999", HttpMethod.DELETE, null, String.class);
        assertThat(notFound.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
