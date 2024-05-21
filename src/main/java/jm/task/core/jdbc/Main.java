package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.dropUsersTable();
        //userService.cleanUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 20);
        userService.saveUser("Petr", "Popov", (byte) 22);
        userService.saveUser("Boris", "Sidorov", (byte) 90);
        userService.saveUser("Gleb", "Ivanov", (byte) 36);
        userService.saveUser("Fedor", "Johov", (byte) 41);
        userService.removeUserById(11);
        userService.removeUserById(3);
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
    }
}
