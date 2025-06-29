package gift;

import gift.entity.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        var connection = getConnection();
        createTable(connection);

        Item item = new Item(1L, "book", 1000, "example.asdfjlasd.png");
        insertItem(connection, item);
    }

    public static void insertItem(Connection connection, Item item) throws Exception {
        var sql = """
                INSERT INTO ITEM (ID,NAME,PRICE,IMAGEURL) VALUES (?,?,?,?);
                """;
        var statement = connection.prepareStatement(sql); // ? 해당하는 필드 채워줌
        statement.setLong(1, item.getId());
        statement.setString(2, item.getName());
        statement.setInt(3, item.getPrice());
        statement.setString(4, item.getImageUrl());
        statement.execute();
        statement.close();

    }

    public static void createTable(Connection connection) throws Exception {
        var sql = """
                create table item (
                    id BIGINT,
                    name varchar(255),
                    price int,
                    imageUrl varchar(255)
                );
                """;
        var statement = connection.createStatement();
        statement.execute(sql);
    }

    public static Connection getConnection() throws Exception {
            var url = "jdbc:h2:mem:test";
            var user = "sa";
            var password = "";
            return DriverManager.getConnection(url, user, password); // 얘는 뭐하는데 커넥션을 만드는거지?
    }
}
