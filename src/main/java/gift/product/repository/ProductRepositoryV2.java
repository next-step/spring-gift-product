package gift.product.repository;

import gift.domain.Product;
import gift.global.exception.CustomDatabaseException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryV2 implements ProductRepository{

    private final JdbcClient client;

    public ProductRepositoryV2(JdbcClient client) {
        this.client = client;
    }

    @Override
    public UUID save(Product product) {
        String sql = "insert into product (id, name, price, image_url) values (:id, :name, :price, :image_url)";
        int update = client.sql(sql)
                .param("id", uuidToBytes(product.getId()))
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageURL())
                .update();
        if (update == 0) throw new CustomDatabaseException("상품 저장 실패");

        return product.getId();
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from product";

        return client.sql(sql)
                .query(getProductRowMapper())
                .list();
    }

    @Override
    public Optional<Product> findById(UUID id) {

        String sql = "select * from product where id = :id";

        return client.sql(sql)
                .param("id", uuidToBytes(id))
                .query(getProductRowMapper())
                .optional();
    }

    @Override
    public void deleteById(UUID id) {
        String sql = "delete from product where id = :id";

        int update = client.sql(sql)
                .param("id", uuidToBytes(id))
                .update();

        if (update == 0) throw new CustomDatabaseException("상품 삭제 실패");
    }

    @Override
    public void update(Product product) {
        String sql = "update product set name = :name, price = :price, image_url = :image_url where id = :id";

        int update = client.sql(sql)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageURL())
                .param("id", uuidToBytes(product.getId()))
                .update();

        if (update == 0) throw new CustomDatabaseException("상품 수정 실패");

    }

    private RowMapper<Product> getProductRowMapper() {
        return (rs, rowNum) -> {
            UUID id = bytesToUUID(rs.getBytes("id"));
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imageUrl = rs.getString("image_url");
            return new Product(id, name, price, imageUrl);
        };
    }

    private UUID bytesToUUID(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long high = bb.getLong();
        long low = bb.getLong();
        return new UUID(high, low);
    }

    private byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return bb.array();
    }
}
