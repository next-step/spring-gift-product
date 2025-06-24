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
//    @GetMapping("/hello")
//    public String hello() {
//        return new Greeting(1L, "")
//                .sayHello();
//    }

    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "name", required = true, defaultValue = "world!") String name
    ){
        return new Greeting(1L, name)
                .sayHello();
    }
}

record Greeting(long id, String name){
    public String sayHello(){
        return "Hello, %s!".formatted(name);
    }
}
