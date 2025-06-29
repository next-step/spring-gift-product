package gift.repository;

import gift.entity.Item;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/***
 * Q1. 메서드마다 connection 및 테이블을 생성하는데, 제가 저번 실시간 강의 듣기론
 *     커넥션 풀이라는게 있어서, 이게 유한한 자원이라 아껴써야한다. 라는 말을 들었는데
 *     아래는 커넥션을 생성하기만 하지, 회수하는 것을 따로 설정하지 않습니다. 저 밑에
 *     statement.close 라는 메서드가 커넥션 풀도 회수 하는 건가요?
 *
 */



//@Repository
public class ItemRepositoryJdbc {
    public Item saveItem(Item item) throws Exception {

        Connection connection = createConnection();
        createTable(connection);

        var sql = """
                    INSERT INTO ITEM (id,name,price,imageurl) VALUES (?,?,?,?); 
                """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, item.getId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());
            statement.setString(4, item.getImageUrl());
            statement.execute();
            statement.close();
        return item;
    }

    public List<Item> getItem(String name, Integer price) throws Exception {
        List<Item> items = new ArrayList<>();  // 읽기전용, 속도 빨라 ArrayList 채용

        Connection connection = createConnection();
        createTable(connection);

        String sql = """
        SELECT id, name, price, imageurl
        FROM ITEM
        WHERE name = ? OR price = ?;
    """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, price);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("imageurl")
                    );
                    items.add(item);
                }
            }
        }

        return items;
    }




    public static void createTable(Connection connection) throws Exception{
        var sql = """
                    CREATE TABLE IF NOT EXISTS ITEM (
                        id BIGINT,
                        name varchar(255),
                        price INT,
                        imageurl varchar(255));
                """;
        connection.prepareStatement(sql).execute();
    }
    public static Connection createConnection() throws Exception {
        var url = "jdbc:h2:mem:test";
        var user = "sa";
        var password = "";
        return DriverManager.getConnection(url, user, password);
    }
}



