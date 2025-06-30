package gift;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {
    private final DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(ProductDTO productDto) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "INSERT INTO PRODUCTS (name, price, imageUrl) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, productDto.getName());
            pstmt.setInt(2, productDto.getPrice());
            pstmt.setString(3, productDto.getImageUrl());

            pstmt.executeUpdate();
        } finally {
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }

    public List<Product> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            String sql = "SELECT * FROM PRODUCTS";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String imageUrl = rs.getString("imageUrl");

                products.add(new Product(id, name, price, imageUrl));
            }

            return products;
        } finally {
            if(rs != null) {
                rs.close();
            }

            if(pstmt != null) {
                pstmt.close();
            }

            if(con != null) {
                con.close();
            }
        }
    }

    public Product findById(Long id) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product product = null;
        try {
            con = dataSource.getConnection();
            String sql = "SELECT * FROM PRODUCTS WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("imageUrl"));
                return product;
            }

            else{
                return null;
            }

        } finally {
            if(rs != null) {
                rs.close();
            }

            if(pstmt != null) {
                pstmt.close();
            }

            if(con != null) {
                con.close();
            }
        }
    }

    public void update(Long id, ProductDTO productDto) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            if(productDto.getName() != null) {
                String sql = "UPDATE PRODUCTS SET name = ? WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, productDto.getName());
                pstmt.setLong(2, id);
                pstmt.executeUpdate();
            }
            if(productDto.getPrice() != 0) {
                String sql = "UPDATE PRODUCTS SET price = ? WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, productDto.getPrice());
                pstmt.setLong(2, id);
                pstmt.executeUpdate();
            }
            if(productDto.getImageUrl() != null) {
                String sql = "UPDATE PRODUCTS SET imageUrl = ? WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, productDto.getImageUrl());
                pstmt.setLong(2, id);
                pstmt.executeUpdate();
            }
        } finally {
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }

    public void delete(Long id) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();
            String sql = "DELETE FROM PRODUCTS WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.executeUpdate();
        } finally {
            if(pstmt != null) {
                pstmt.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }
}
