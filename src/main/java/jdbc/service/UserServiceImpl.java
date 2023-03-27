package jdbc.service;

import jdbc.dao.UserDaoHibernateImpl;
import jdbc.model.User;

import java.util.List;

/**
 * Класс, отвечающий за бизнес-операции над сущностью таблицы и пользователя.
 */
public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDaoHibernateImpl.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDaoHibernateImpl.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateImpl.saveUser(name, lastName, age);
        System.out.println("User с именем - " + name + " добавлен в базу данных;");
    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernateImpl.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoHibernateImpl.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernateImpl.cleanUsersTable();
    }
}
