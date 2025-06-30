package study;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberRestControllerTest {

  @LocalServerPort
  private int port;

  private RestClient client = RestClient.builder().build();

  @Test
  void test1() {
    var url = "http://localhost:" + port + "/api/members/1";
    var response = client.get()
        .uri(url)
        .retrieve()
        .toEntity(Member.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    var actual = response.getBody();
    assertThat(actual.name()).isEqualTo("choi");
  }

  @Test
  void test2() {
    var url = "http://localhost:" + port + "/api/members/2";
    var response = client.get()
        .uri(url)
        .retrieve()
        .toBodilessEntity();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    var actual = response.getBody();
    assertThat(actual.name()).isEqualTo("choi");
  }
}
