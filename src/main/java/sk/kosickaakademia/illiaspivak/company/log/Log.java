package sk.kosickaakademia.illiaspivak.company.log;

public class Log {
    public static void error(String str){
        System.out.println("[ERROR] - "+str);
    }

    public static void print(String str){
        System.out.println("[OK] - "+str);
    }
}
