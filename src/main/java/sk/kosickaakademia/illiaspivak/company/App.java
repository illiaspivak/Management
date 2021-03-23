package sk.kosickaakademia.illiaspivak.company;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sk.kosickaakademia.illiaspivak.company.database.Database;
import sk.kosickaakademia.illiaspivak.company.entity.User;
import sk.kosickaakademia.illiaspivak.company.helpclasses.XML;
import sk.kosickaakademia.illiaspivak.company.util.Util;

import java.io.OutputStream;
import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);

       // Util util = new Util();
       // Database database = new Database();
       // XML xml = new XML();

       // System.out.println(xml.FromJsonToXML(util.getJson(database.getAllUsers())));
       
    }
}
