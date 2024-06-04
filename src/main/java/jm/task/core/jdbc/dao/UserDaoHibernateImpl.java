package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final static String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT not NULL AUTO_INCREMENT, name VARCHAR (20), lastName VARCHAR (20), age TINYINT not NULL, PRIMARY KEY (id))";
    private final static String DROPS_USER_TABLE = "DROP TABLE IF EXISTS users";
    private final static String CLEAN_USERS_TABLE = "TRUNCATE TABLE users";

    private final SessionFactory sessionFactory = new Util().getSessionFactory();
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_USER_TABLE).executeUpdate();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(DROPS_USER_TABLE).executeUpdate();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.save(new User(name, lastName, age));
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.delete(session.get(User.class, id));
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User").getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CLEAN_USERS_TABLE).executeUpdate();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
