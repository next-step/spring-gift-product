package gift.repository;

import gift.entity.Item;
import gift.exception.ItemNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemRepositoryJdbc {



    public Item saveItem(Item item) throws Exception {
        createTable();

        String sql = "INSERT INTO ITEM (id, name, price, imageurl) VALUES (?, ?, ?, ?)";

        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, item.getId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());
            statement.setString(4, item.getImageUrl());

            statement.execute();
        }

        return item;
    }

    public List<Item> getItem(String name, Integer price) throws Exception {
        createTable();
        List<Item> items = new ArrayList<>();

        String sql = "SELECT id, name, price, imageurl FROM ITEM WHERE name = ? OR price = ?";

        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

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

    public Item deleteItems(String name) throws Exception {
        createTable();
        Item targetItem;

        String selectSql = "SELECT id, name, price, imageurl FROM ITEM WHERE name = ?";

        try (Connection connection = createConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {

            selectStatement.setString(1, name);

            try (ResultSet rs = selectStatement.executeQuery()) {
                if (rs.next()) {
                    targetItem = new Item(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("imageurl")
                    );
                } else {
                    throw new ItemNotFoundException(name);
                }
            }

            String deleteSql = "DELETE FROM ITEM WHERE name = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                deleteStatement.setString(1, name);
                deleteStatement.executeUpdate();
            }
        }

        return targetItem;
    }

    public static void createTable() throws Exception {
        String sql = """
            CREATE TABLE IF NOT EXISTS ITEM (
                id BIGINT,
                name VARCHAR(255),
                price INT,
                imageurl VARCHAR(255)
            );
        """;

        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public static Connection createConnection() throws Exception {
        String url = "jdbc:h2:mem:test";
        String user = "sa";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
