package gift;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final JdbcTemplate jdbcTemplate;

    public Application(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... strings) throws Exception {
        jdbcTemplate.execute("DROP TABLE products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE products(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price BIGINT, imageUrl VARCHAR(255))");
    }
}