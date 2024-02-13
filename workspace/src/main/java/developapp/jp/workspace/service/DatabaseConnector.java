package developapp.jp.workspace.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class DatabaseConnector {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${test.aono}")
    private String testAono;

    public Connection connect() {
        Connection connection = null;
//        System.out.println("dbUrl: " + dbUrl);
//        System.out.println("dbUsername: " + dbUsername);
//        System.out.println("dbPassword: " + dbPassword);
//        System.out.println("testAono: " + testAono);
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
