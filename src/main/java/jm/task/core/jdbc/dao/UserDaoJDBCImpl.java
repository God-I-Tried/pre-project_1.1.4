package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT not NULL AUTO_INCREMENT, name VARCHAR (20), lastName VARCHAR (20), age TINYINT not NULL, PRIMARY KEY (id))";
    private final static String DROPS_USER_TABLE = "DROP TABLE IF EXISTS users";
    private final static String SAVE_USER = "INSERT users (name, lastName, age) VALUES ('%s', '%s', '%d')";
    private final static String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = '%d';";
    private final static String GET_ALL_USERS = "SELECT * FROM users";
    private final static String CLEAN_USERS_TABLE = "TRUNCATE TABLE users";
    private final static Connection CONNECTION = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement statement = CONNECTION.prepareStatement(CREATE_USER_TABLE)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = CONNECTION.prepareStatement(DROPS_USER_TABLE)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(String.format(SAVE_USER,
                name, lastName, age))) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(String.format(REMOVE_USER_BY_ID, id))) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = CONNECTION.prepareStatement(GET_ALL_USERS)) {
            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                User user = new User();
                user.setId(resulSet.getLong("id"));
                user.setName(resulSet.getString("name"));
                user.setLastName(resulSet.getString("lastname"));
                user.setAge(resulSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = CONNECTION.prepareStatement(CLEAN_USERS_TABLE)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
