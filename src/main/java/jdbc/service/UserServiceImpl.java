package jdbc.service;

import jdbc.dao.UserDaoJDBCImpl;
import jdbc.model.User;

import java.util.List;

/**
 * Класс, отвечающий за бизнес-операции над сущностью таблицы и пользователя.
 */
public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        userDaoJDBCImpl.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBCImpl.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBCImpl.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDaoJDBCImpl.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoJDBCImpl.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoJDBCImpl.cleanUsersTable();
    }
}