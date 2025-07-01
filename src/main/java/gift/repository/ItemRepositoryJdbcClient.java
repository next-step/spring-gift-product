package gift.repository;

import gift.entity.Item;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryJdbcClient implements ItemRepository{


    private JdbcClient client;

    public ItemRepositoryJdbcClient(JdbcClient client) {
        this.client = client;
    }

    @Override
    public Item saveItem(Item item) {
        var sql = "INSERT INTO items (id, name, price, imageurl) VALUES (:id, :name, :price, :imageurl)";

        client.sql(sql)
                .param("id", item.getId())
                .param("name", item.getName())
                .param("price", item.getPrice())
                .param("imageurl", item.getImageUrl())
                .update();

        return item;
    }

    @Override
    public List<Item> getItems(String name, Integer price) {
        var sql = "SELECT * FROM items WHERE 1=1";
        var builder = client.sql(sql);

        if (name != null && !name.isBlank()) {
            sql += " AND name LIKE :name";
            builder = builder.param("name", "%" + name + "%");
        }
        if (price != null) {
            sql += " AND price = :price";
            builder = builder.param("price", price);
        }

        return client.sql(sql)
                .query(Item.class)
                .list();
    }

    @Override
    public Item deleteItems(String name) {
        Item item = client.sql("SELECT * FROM items WHERE name = :name")
                .param("name", name)
                .query(Item.class)
                .optional()
                .orElse(null);

        if (item != null) {
            client.sql("DELETE FROM items WHERE name = :name")
                    .param("name", name)
                    .update();
        }

        return item;
    }

    @Override
    public Item findById(Long id) {
        return client.sql("SELECT * FROM items WHERE id = :id")
                .param("id", id)
                .query(Item.class)
                .optional()
                .orElse(null);
    }

    @Override
    public List<Item> getAllItems() {
        return client.sql("SELECT * FROM items")
                .query(Item.class)
                .list();
    }

    @Override
    public Item deleteById(Long id) {
        Item item = findById(id);
        if (item != null) {
            client.sql("DELETE FROM items WHERE id = :id")
                    .param("id", id)
                    .update();
        }
        return item;
    }

    @Override
    public Item updateItem(Long id, String name, int price, String imageUrl) {
        client.sql("UPDATE items SET name = :name, price = :price, imageurl = :imageurl WHERE id = :id")
                .param("name", name)
                .param("price", price)
                .param("imageurl", imageUrl)
                .param("id", id)
                .update();

        return new Item(id, name, price, imageUrl);
    }
}
