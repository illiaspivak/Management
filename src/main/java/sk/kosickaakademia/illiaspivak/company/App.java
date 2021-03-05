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
        List<User> list = database.getAllUsers();
        System.out.println(list);

    }
}
