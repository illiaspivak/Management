package sk.kosickaakademia.illiaspivak.company;

import sk.kosickaakademia.illiaspivak.company.database.Database;
import sk.kosickaakademia.illiaspivak.company.util.Util;

import java.io.OutputStream;

public class App {
    public static void main(String[] args) {
        Util util = new Util();
        System.out.println(util.getCurrentTime());
        System.out.println(util.normalizeName("erIK"));
    }
}
