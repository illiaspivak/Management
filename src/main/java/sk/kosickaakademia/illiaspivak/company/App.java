package sk.kosickaakademia.illiaspivak.company;

import sk.kosickaakademia.illiaspivak.company.database.Database;

public class App {
    public static void main(String[] args) {
        Database database=new Database();
        database.getConnection();
    }
}
