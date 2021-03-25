package sk.kosickaakademia.illiaspivak.company.util;




import sk.kosickaakademia.illiaspivak.company.log.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login {
    Log log = new Log();
    private static Map<String, Date> blocked ;
    private static Map<String, Integer> attemp;
    private final String PASSWORD = "bigdog";


    public Login() {
        blocked=new HashMap<>();
        attemp=new HashMap<>();
    }

    public String loginUser(String username, String password){
        log.info("Is the collection empty?");
        System.out.println(attemp.isEmpty());

        if(blocked.containsKey(username)){

        }else{
            log.info("Verifying the correct password");
            if(password.equals(PASSWORD)){
                log.print("The password is correct");
                String token = new Util().generateToken();
                return token;
            }else{
                log.error("The password is incorrect");
                log.info("Checking if the user is in the collection");
                System.out.println(attemp.containsKey(username));
                if(attemp.containsKey(username)){
                    log.info("The user is in the collection");
                    int count = attemp.get(username);
                    System.out.println(count);
                    if(count<3){
                        count++;
                        attemp.replace(username,count);
                        System.out.println(count);
                    }
                }else{
                    log.info("User not in the collection");
                    log.info("Adding the user to a collection");
                    attemp.put(username,0);
                    System.out.println(username);
                    System.out.println(attemp.get(username));
                    System.out.println(attemp.containsKey(username));

                }

            }
        }
        // 1 je user blokovany
        // ak ano, neuplynul cas blokacie
        // ak uplynul ...odblokuj + over heslo
        // ak cas neuplynul, potom vrat blocked

        // je heslo spravne
        // ak ano generuj a vrat token + vymaz mu zle pokusy ak nejake ma
        // ak je heslo zle, pridaj alebo zvys pocet zlych pokusov
        // ak uz mame 3 zle pokusy, zistim aktualny cas , pridaj 1 min a zapis do blocked + vymaz ho z attemp;


        return null;
    }

}
