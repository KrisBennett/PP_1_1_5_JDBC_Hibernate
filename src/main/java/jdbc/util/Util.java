package jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс, содержащий логику настройки соединения с БД.
 */
public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Vac/zto=.24q";
    private Connection connection;

    /**
     * Создание Connection соединения с БД для последующего создания объектов
     * Statement и PrepareStatement для выполнения SQL-команд.
     *
     * @return connection
     */
    public Connection getDBConnection() {

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("провал");
            e.printStackTrace();
        }
        return connection;
    }
}
