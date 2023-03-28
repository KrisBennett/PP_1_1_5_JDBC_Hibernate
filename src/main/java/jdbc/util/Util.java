package jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс, содержащий логику настройки соединения с БД.
 */
public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_db";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Vac/zto=.24q";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";

    /**
     * Создание фабрики по производству сессий
     *
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Properties properties = new Properties();

            properties.setProperty("hibernate.connection.url", URL);
            properties.setProperty("hibernate.connection.driver_class", DRIVER);
            properties.setProperty("hibernate.connection.username", USERNAME);
            properties.setProperty("hibernate.connection.password", PASSWORD);

            properties.setProperty("current_session_context_class", "thread");
            properties.setProperty("dialect", DIALECT);
            properties.setProperty("hibernate.show_sql", "true");
            properties.setProperty("hibernate.format_sql", "true");
            properties.setProperty("hbm2ddl.auto", "update");

            sessionFactory = new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }


    /**
     * Создание Connection соединения с БД для последующего создания объектов
     * Statement и PrepareStatement для выполнения SQL-команд.
     *
     * @return connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
