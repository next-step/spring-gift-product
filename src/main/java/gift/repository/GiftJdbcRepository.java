package gift.repository;

import gift.entity.Gift;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class GiftJdbcRepository implements GiftRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AtomicLong id = new AtomicLong(0);

    public GiftJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Gift save(Gift gift) {
        if (gift == null) {
            throw new RuntimeException("Null 값입니다.");
        }
        gift.setId(id.incrementAndGet());
        String sql = "INSERT INTO Gift(id, giftId, giftName, giftPrice, giftPhotoUrl) VALUES (?, ?, ?, ?, ?)";
        Object[] args = new Object[]{
                gift.getId(),
                gift.getGiftId(),
                gift.getGiftName(),
                gift.getGiftPrice(),
                gift.getGiftPhotoUrl()
        };
        int[] argTypes = {Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.INTEGER, Types.VARCHAR};
        jdbcTemplate.update(sql, args, argTypes);
        return gift;
    }

    @Override
    public Optional<Gift> findById(Long id) {
        String sql = "SELECT * FROM Gift WHERE id = ?";
        Object[] args = {id};
        int[] argTypes = {Types.BIGINT};
        return jdbcTemplate.query(sql, args, argTypes, rs -> {
                    if (rs.next()) {
                        Gift gift = new Gift();
                        gift.setId(rs.getLong("id"));
                        gift.setGiftId(rs.getLong("giftId"));
                        gift.setGiftName(rs.getString("giftName"));
                        gift.setGiftPrice(rs.getInt("giftPrice"));
                        gift.setGiftPhotoUrl(rs.getString("giftPhotoUrl"));
                        return Optional.of(gift);
                    }
                    return Optional.empty();
                }
        );
    }

    @Override
    public List<Gift> findAll() {
        String sql = "SELECT * FROM Gift";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Gift gift = new Gift();
            gift.setId(rs.getLong("id"));
            gift.setGiftId(rs.getLong("giftId"));
            gift.setGiftName(rs.getString("giftName"));
            gift.setGiftPrice(rs.getInt("giftPrice"));
            gift.setGiftPhotoUrl(rs.getString("giftPhotoUrl"));
            return gift;
        });
    }

    @Override
    public Gift modify(Long id, Gift gift) {
        StringBuilder sql = new StringBuilder("UPDATE Gift SET ");
        List<Object> params = new ArrayList<>();
        if(gift.getGiftId() != null){
            sql.append("giftId = ?, ");
            params.add(gift.getGiftId());
        }
        if(gift.getGiftName() != null){
            sql.append("giftName = ?, ");
            params.add(gift.getGiftName());
        }
        if(gift.getGiftPrice() != null){
            sql.append("giftPrice = ?, ");
            params.add(gift.getGiftPrice());
        }
        if(gift.getGiftPhotoUrl() != null){
            sql.append("giftPhotoUrl = ?, ");
            params.add(gift.getGiftPhotoUrl());
        }
        if(params.isEmpty()){
            throw new RuntimeException("수정할 내용이 없습니다.");
        }
        sql.deleteCharAt((sql.length()-2));
        sql.append(" WHERE id = ?");
        System.out.println(sql.toString());
        params.add(id);
        System.out.println(params.toString());
        jdbcTemplate.update(sql.toString(), params.toArray());
        gift.setId(id);
        return gift;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM Gift WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
