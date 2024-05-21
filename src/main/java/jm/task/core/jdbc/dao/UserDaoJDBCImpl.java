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
    Connection connection = Util.connectToSql();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        StringBuilder createUserTable = new StringBuilder("CREATE TABLE IF NOT EXISTS users ")
                .append("(id BIGINT not NULL AUTO_INCREMENT, ")
                .append(" name VARCHAR (20), ")
                .append(" lastName VARCHAR (20), ")
                .append(" age TINYINT not NULL, ")
                .append(" PRIMARY KEY (id))");
        try (PreparedStatement statement = connection.prepareStatement(createUserTable.toString())) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP TABLE IF EXISTS users";
        try (PreparedStatement statement = connection.prepareStatement(dropUsersTable)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        createUsersTable();
        StringBuilder saveUser = new StringBuilder("INSERT users (name, lastName, age) VALUES (")
                .append("'")
                .append(name)
                .append("', '")
                .append(lastName)
                .append("', ")
                .append(age)
                .append(")");
        try (PreparedStatement statement = connection.prepareStatement(saveUser.toString())) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        StringBuilder removeUserById = new StringBuilder("DELETE FROM users WHERE id = '")
                .append(id)
                .append("';");
        try (PreparedStatement statement = connection.prepareStatement(removeUserById.toString())) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(getAllUsers);) {
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
        String cleanUsersTable = "TRUNCATE TABLE users";
        try (PreparedStatement statement = connection.prepareStatement(cleanUsersTable)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
