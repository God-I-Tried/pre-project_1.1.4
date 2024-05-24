package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String createUserTable = String.format("%s %s %s",
                "CREATE TABLE IF NOT EXISTS users (id BIGINT not NULL AUTO_INCREMENT, ",
                "name VARCHAR (20), lastName VARCHAR (20), ",
                " age TINYINT not NULL, PRIMARY KEY (id))");
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(createUserTable).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(dropUsersTable).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            User user = session.get(User.class, id);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE TABLE users";
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(cleanUsersTable).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
