package jdbc;

import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("SomeNameOne", "SomeSurnameOne", (byte) 10);
        userService.saveUser("SomeNameTwo", "SomeSurnameTwo", (byte) 20);
        userService.saveUser("SomeNameThree", "SomeSurnameThree", (byte) 30);
        userService.saveUser("SomeNameFour", "SomeSurnameFour", (byte) 40);

        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}