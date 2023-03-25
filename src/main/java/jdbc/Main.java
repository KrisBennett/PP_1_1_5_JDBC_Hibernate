package jdbc;

import jdbc.model.User;
import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        User someUser_1 = new User("SomeNameOne", "SomeSurnameOne", (byte) 10);
        userService.saveUser(someUser_1.getName(), someUser_1.getLastName(), someUser_1.getAge());
        System.out.println("User с именем - " + someUser_1.getName() + " добавлен в базу данных");

        User someUser_2 = new User("SomeNameTwo", "SomeSurnameTwo", (byte) 20);
        userService.saveUser(someUser_2.getName(), someUser_2.getLastName(), someUser_2.getAge());
        System.out.println("User с именем - " + someUser_2.getName() + " добавлен в базу данных");

        User someUser_3 = new User("SomeNameThree", "SomeSurnameThree", (byte) 30);
        userService.saveUser(someUser_3.getName(), someUser_3.getLastName(), someUser_3.getAge());
        System.out.println("User с именем - " + someUser_3.getName() + " добавлен в базу данных");

        User someUser_4 = new User("SomeNameFour", "SomeSurnameFour", (byte) 40);
        userService.saveUser(someUser_4.getName(), someUser_4.getLastName(), someUser_4.getAge());
        System.out.println("User с именем - " + someUser_4.getName() + " добавлен в базу данных");

        System.out.println('\n' + "Получение всех пользователей:");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}