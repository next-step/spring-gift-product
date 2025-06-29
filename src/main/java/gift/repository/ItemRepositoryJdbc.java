package gift.repository;

import gift.entity.Item;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



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



    public static void createTable(Connection connection) throws Exception{
        var sql = """
                    CREATE TABLE ITEM(
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



