package gift.repository;

import gift.entity.Gift;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;


@Repository
public class GiftJdbcClientRepository implements GiftRepository {

  private final JdbcClient jdbcClient;

  public GiftJdbcClientRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }


  @Override
  public Gift saveGift(Gift gift) {
    String sql = "INSERT INTO gift (name, price, image_url) VALUES (:name, :price, :imageUrl)";
    jdbcClient.sql(sql)
              .param("name", gift.getName())
              .param("price", gift.getPrice())
              .param("imageUrl", gift.getImageUrl())
              .update();

    return gift;
  }


  @Override
  public List<Gift> findAllGifts() {
    String sql = "SELECT id, name, price, image_url AS imageUrl FROM gift;";
    return jdbcClient.sql(sql)
                     .query(Gift.class)
                     .list();
  }

  @Override
  public Optional<Gift> findById(Long id) {
    String sql = "SELECT id, name, price, image_url AS imageUrl FROM gift;";
    return jdbcClient.sql(sql)
                     .param("id", id)
                     .query(Gift.class)
                     .optional();
  }

  @Override
  public void updateGift(Gift gift) {
    String sql = "UPDATE gift SET name = :name, price = :price, image_url = :image_url WHERE id = :id;";
    jdbcClient.sql(sql)
              .param("name", gift.getName())
              .param("price", gift.getPrice())
              .param("image_url", gift.getImageUrl())
              .param("id", gift.getId())
              .update();
  }

  @Override
  public void deleteGiftById(Long id) {
    String sql = "DELETE FROM gift WHERE id = :id";
    jdbcClient.sql(sql)
              .param("id", id)
              .update();
  }
}
