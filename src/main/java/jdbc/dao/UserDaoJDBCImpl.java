package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за отправку CRUD запросов таблице users.
 * DAO - Data Access Object. Объект доступа к данным
 */
public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Statement statement = util.getDBConnection().createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id BIGINT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR(50) NOT NULL, "
                    + "lastName VARCHAR(50) NOT NULL, "
                    + "age TINYINT NOT NULL, "
                    + "PRIMARY KEY(id))";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = util.getDBConnection().prepareStatement(
                "DROP TABLE IF EXISTS users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = util.getDBConnection().prepareStatement(
                "INSERT INTO users (name, lastName, age) "
                        + "VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = util.getDBConnection().prepareStatement(
                "DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (PreparedStatement preparedStatement = util.getDBConnection().prepareStatement(
                "SELECT *  FROM users")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = util.getDBConnection().prepareStatement(
                "TRUNCATE users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}