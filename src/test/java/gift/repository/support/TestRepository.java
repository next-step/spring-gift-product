package gift.repository.support;

import org.springframework.jdbc.core.JdbcTemplate;

public class TestRepository {

    private final JdbcTemplate jdbcTemplate;

    public TestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM product");
    }

}
