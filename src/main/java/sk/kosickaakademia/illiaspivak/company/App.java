package sk.kosickaakademia.illiaspivak.company;

import sk.kosickaakademia.illiaspivak.company.database.Database;
import sk.kosickaakademia.illiaspivak.company.entity.User;
import sk.kosickaakademia.illiaspivak.company.util.Util;

import java.io.OutputStream;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Util util = new Util();
        Database database = new Database();
        User user = database.getUserById(3);
        System.out.println(user);
        database.changeAge(3,18);
        User meky = database.getUserById(3);
        System.out.println(meky);

    }
}
