package ten.give.connection;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class ConnectionTest {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/give?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PW = "1234";

    @Test
    public void testConnection() throws Exception {

        Class.forName(DRIVER);

        try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
            System.out.println(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    }
