package gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}

@RestController
class GreetingController {
    @GetMapping("/hello")
    public Greeting hello(@RequestParam(value = "name", defaultValue = "world!") String name) {
        var greeting = new Greeting(1L, name);
        return greeting;
    }
}

record Greeting(long id, String name) {
    public String sayHello() {
        return "Hello, %s!".formatted(name);
    }
}