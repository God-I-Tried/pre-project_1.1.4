package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;
import java.sql.SQLException;


public class UserServiceImpl implements UserService {

    private final static UserDao USER = new UserDaoJDBCImpl();

    public void createUsersTable() {
        USER.createUsersTable();
    }

    public void dropUsersTable() {
        USER.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        USER.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        USER.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return USER.getAllUsers();
    }

    public void cleanUsersTable() {
        USER.cleanUsersTable();
    }
}
