package sk.kosickaakademia.illiaspivak.company;

import sk.kosickaakademia.illiaspivak.company.database.Database;
import sk.kosickaakademia.illiaspivak.company.entity.User;
import sk.kosickaakademia.illiaspivak.company.util.Util;

import java.io.OutputStream;

public class App {
    public static void main(String[] args) {
        Util util = new Util();
        Database database = new Database();
        User frances = new User("Frances", "McDormand", 63, 1);
        database.insertNewUser(frances);

    }
}
