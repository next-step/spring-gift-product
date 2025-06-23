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

    //사용자의 요청을 받는 서버의 입구 부분
    @RestController
    class GreetingController{
        //사용자가 get으로 요청하고, 요청하는 곳이 /hello인 경우
        @GetMapping("/hello")
        public String hello(
                @RequestParam(value = "name", defaultValue = "World") String name
        ){
            return new Greeting(1L, name).
                    sayHello();
        }
    }

    record Greeting(long id, String name){
        public String sayHello(){
            return "Hello, %s!".formatted(name);
        }
    }

}
