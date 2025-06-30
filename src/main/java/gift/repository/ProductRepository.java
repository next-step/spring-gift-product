package gift.repository;

import gift.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static final RowMapper<Product> ROW_MAPPER = (rs, rowNum) -> new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("image_url")
    );


    public List<Product> findAll() {
        return jdbc.query(
                "select * from products order by id desc",
                ROW_MAPPER
        );
    }

    public Optional<Product> findById(long id) {
        List<Product> list = jdbc.query(
                "select * from products where id = ?",
                ROW_MAPPER,
                id
        );
        return list.stream().findFirst();
    }

    public long save(Product p) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "insert into products(name, price, image_url) values (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrice());
            ps.setString(3, p.getImageUrl());
            return ps;
        }, kh);
        return kh.getKey().longValue();
    }

    public boolean update(long id, Product p) {
        int rows = jdbc.update(
                "update products set name=?, price=?, image_url=? where id=?",
                p.getName(), p.getPrice(), p.getImageUrl(), id
        );
        return rows > 0;
    }

    public boolean delete(long id) {
        return jdbc.update(
                "delete from products where id=?",
                id
        ) > 0;
    }
}
