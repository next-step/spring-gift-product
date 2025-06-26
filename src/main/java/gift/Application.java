package gift;

import gift.dto.CreateProductRequest;
import gift.repository.ProductMemoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final ProductMemoryRepository repository;


    public Application(ProductMemoryRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        repository.save(new CreateProductRequest("productA", 10000, "exA.com"));
        repository.save(new CreateProductRequest("productB", 20000, "exB.com"));
    }
}
