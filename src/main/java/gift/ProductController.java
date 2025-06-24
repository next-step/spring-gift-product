package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @GetMapping("/hello")
  public String hello(
      @RequestParam(
          value = "name",
          defaultValue = "world"
      ) String name
  ) {
    return new Product(1L, name)
        .sayHello();
  }
}