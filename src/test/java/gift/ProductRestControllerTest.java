package gift;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import gift.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

@DisplayName("Product 삽입 Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestControllerTest {

  @LocalServerPort
  private int port = 8080;

  private RestClient client = RestClient.builder().build();

  @Test
  @DisplayName("1번 Product 삽입 Test")
  void test1() {
    var url = "http://localhost:" + port + "/api/products/1";
    var response = client.get()
        .uri(url)
        .retrieve()
        .toEntity(Product.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    var actual = response.getBody();
    assertThat(actual.getName()).isEqualTo("아이스 카페 아메리카노 T");
    assertThat(actual.getPrice()).isEqualTo(600);
    assertThat(actual.getImageUrl()).isEqualTo(
        "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
  }

  @Test
  @DisplayName("2번 Product 삽입 Test")
  void test2() {
    var url = "http://localhost:" + port + "/api/products/2";
    var response = client.get()
        .uri(url)
        .retrieve()
        .toEntity(Product.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    var actual = response.getBody();
    assertThat(actual.getName()).isEqualTo("코코아 파우더");
    assertThat(actual.getPrice()).isEqualTo(7600);
    assertThat(actual.getImageUrl()).isEqualTo(
        "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRWDlZJBZiXDT8JlPp8iS5eeyJDdsISVl9lBSTXgcLhUAjCjU-qttjbpgg8-JPRHMWWOKEuGqlQWTqIvg_Hw_dAaEdZYPM-JF5z7KMOGPGkpGIj3z9WZMJG");
  }
}
