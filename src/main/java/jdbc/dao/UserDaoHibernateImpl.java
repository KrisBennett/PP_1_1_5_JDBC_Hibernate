package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            String query = "CREATE TABLE IF NOT EXISTS usersHibernate ("
                    + "id BIGINT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR(50) NOT NULL, "
                    + "lastName VARCHAR(50) NOT NULL, "
                    + "age TINYINT NOT NULL, "
                    + "PRIMARY KEY(id))";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            String query = "DROP TABLE IF EXISTS usersHibernate";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.createQuery("DELETE User WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            users = session.createQuery("FROM User", User.class).list();
            users.forEach(System.out::println);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.createQuery("DELETE User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                System.out.println("Роллбэк");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
