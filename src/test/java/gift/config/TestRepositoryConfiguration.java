package gift.config;

import gift.repository.support.TestRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@TestConfiguration
public class TestRepositoryConfiguration {

    @Bean
    public TestRepository testRepository(JdbcTemplate jdbcTemplate) {
        return new TestRepository(jdbcTemplate);
    }
}
