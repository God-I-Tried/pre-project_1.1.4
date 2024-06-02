package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final static String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT not NULL AUTO_INCREMENT, name VARCHAR (20), lastName VARCHAR (20), age TINYINT not NULL, PRIMARY KEY (id))";
    private final static String DROPS_USER_TABLE = "DROP TABLE IF EXISTS users";
    private final static String CLEAN_USERS_TABLE = "TRUNCATE TABLE users";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = new Util().getSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_USER_TABLE).executeUpdate();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = new Util().getSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROPS_USER_TABLE).executeUpdate();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = new Util().getSession()) {
            session.save(new User(name, lastName, age));
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = new Util().getSession()) {
            session.delete(session.get(User.class, id));
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = new Util().getSession()) {
            return session.createQuery("from User").getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = new Util().getSession()) {
            session.beginTransaction();
            session.createSQLQuery(CLEAN_USERS_TABLE).executeUpdate();
        }
    }
}
