package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за отправку CRUD запросов таблице users.
 * DAO - Data Access Object. Объект доступа к данным
 */
public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(50) NOT NULL, "
                + "lastName VARCHAR(50) NOT NULL, "
                + "age TINYINT NOT NULL, "
                + "PRIMARY KEY(id))";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT *  FROM users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            users.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
