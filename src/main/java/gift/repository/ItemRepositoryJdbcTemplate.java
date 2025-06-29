package gift.repository;

import gift.entity.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/***
 * Q2. 제가 직접 수동(?) jdbc도 만들어보고, jdbcTemplate으로도 만들어봤는데,
 * 제가 보기엔 수동 JDBC랑 template 차이가 mapper 함수의 유무 차이 같은데 개인적은 생각으론,
 * 커넥션도 application.properties에 설정하는거 봐서는 수동이랑 별 차이가 없어보이고,
 * 어찌어찌 수동 jdbc도 mapper 함수 구현해서 하면 비슷할 것 같다 라고 생각이 들게 되는데요.
 * - 내부적으로 성능차이가 더 있나요?
 * - 아니면 그저 setString(1,"name") 이 과정이 없어져서 편해졌다 라는것에 의의를 두는 건가요?
 *
 * Q3. 빠른 피드백을 받고 싶어서, 시간 관계성 JdbcClient는 구현 후 질문은 추후에 3차 피드백에 할 예정인데요. 강의를 듣다가 궁금해서 여쭤봅니다.
 *     JdbcClient와 JdbcTemplate 차이를 잘 모르겟습니다. Client는 체인형 구조로 설계 할 수 있다. 라고 강의에서 짤막하게 언급해주셨는데요.
 *     이 부분이 어떤 메리트가 있는지를 잘 모르겠습니다.
 *
 *  Q4. 결론적으로 어떤 방식을 채택해야 하나요? 스프링이 아니더라도 제가 지금까지 배운걸 되짚어보면
 *      "세팅이 할게 많고 복잡할 수록 더욱 디테일하게 설정할 수 있다" 라는게 제가 지금까지 느끼는 바 인데요.
 *      지금까지 세 방법중에 어떤 방식을 채택하며 주로 실무에선 어떤 사용 하나요?
 *
 * PS. 해당 클래스의 saveItem 매서드는 LLM 도움을 받았습니다. 이 부분은 제가 따로 학습해서 온전히 이해 해본 다음 피드백에 요청하겠습니다.
 * 이번 ItemRepositoryJdbcTemplate 클래스의 saveItem 함수는 피드백에서 제외해주시길 바랍니다. :)
 */
@Repository
public class ItemRepositoryJdbcTemplate implements ItemRepository{

    private final JdbcTemplate jdbcTemplate;

    public ItemRepositoryJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item saveItem(Item item) {
        var sql = "insert into items (name, price, image_url) values (?, ?, ?)";
        KeyHolder keyholder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getPrice());
            ps.setString(3, item.getImageUrl());
            return ps;
        },keyholder);

        Long id = keyholder.getKey().longValue();
        return new Item(id,item.getName(),item.getPrice(),item.getImageUrl());

    }

    @Override
    public List<Item> getItems(String name, Integer price) {
        var sql = "select id, name, price, image_url from items where name=? or price=?";
        return jdbcTemplate.query(
                sql,
                new Object[]{name, price},
                itemRowMapper
        );

    }

    @Override
    public Item deleteItems(String name) {
        String selectSql = "SELECT id, name, price, image_url FROM items WHERE name = ? LIMIT 1";
        Item item = jdbcTemplate.queryForObject(selectSql, new Object[]{name}, itemRowMapper);



        String deleteSql = "DELETE FROM items WHERE id = ?";
        jdbcTemplate.update(deleteSql, item.getId());

        return item;
    }

    @Override
    public Item findById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, itemRowMapper);
    }

    @Override
    public List<Item> getAllItems() {
        var sql = "select id, name, price, image_url from items";

        return  jdbcTemplate.query(sql, itemRowMapper);
    }

    @Override
    public Item deleteById(Long id) {
        String selectSql = "SELECT id, name, price, image_url FROM items WHERE id = ?";
        Item item = jdbcTemplate.queryForObject(selectSql, new Object[]{id}, itemRowMapper);



        String deleteSql = "DELETE FROM items WHERE id = ?";
        jdbcTemplate.update(deleteSql, id);

        return item;
    }

    @Override
    public Item updateItem(Long id, String name, int price, String imageUrl) {
        String sql = "UPDATE items SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, price, imageUrl, id);

        return findById(id);
    }
    private final RowMapper<Item> itemRowMapper = new RowMapper<>() {
        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Item(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("image_url")
            );
        }
    };
}
