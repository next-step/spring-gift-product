package study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Application {
    @Autowired
    private MemberDao memberDao;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        var connection = getConnection();
        createMemberTable(connection);
        var member = new Member(1L, "최현준", 20, "test@email.com");
    }

    public static void createMemberTable(Connection connection) throws Exception {
        var sql = """
            create table member(
            id bigint,
            name varchar(100),
            age int,
            email varchar(255),
            primary key (id)
            );
            """;
        var statement = connection.createStatement();
        statement.execute(sql);
    }

    public static void insertMember(Connection connection, Member member) throws Exception {
        var sql = """
            insert into member (id, name, age, email) values (?, ?, ?, ?);
        """;
        var statement = connection.prepareStatement(sql);
        statement.setLong(1,member.id());
        statement.setString(1,member.name());
        statement.setInt(1,member.age());
        statement.setString(1,member.email());
        statement.execute();
        statement.close();
    }

    public static Connection getConnection() throws Exception {
        var url = "jdbc:h2:mem:test";
        var user = "sa";
        var password = "";
        return DriverManager.getConnection(url, user, password);
    }
}

