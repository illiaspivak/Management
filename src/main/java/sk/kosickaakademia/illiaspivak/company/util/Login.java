package sk.kosickaakademia.illiaspivak.company.util;

import sk.kosickaakademia.illiaspivak.company.log.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login {
    Log log = new Log();
    private static Map<String, Date> blocked = new HashMap<>();
    private static Map<String, Integer> attempt = new HashMap<>();
    private final String PASSWORD = "bigdog";




    public String loginUser(String username, String password){
        log.info("Checking whether the blocking time has passed");
        if(blocked.containsKey(username)){
            log.info("The user is in the collection blocked");
            Date dateNow = new Date(System.currentTimeMillis());
            System.out.println(dateNow);
            Date dateUser = blocked.get(username);
            System.out.println(dateUser);
            if(dateNow.compareTo(dateUser)>0){
                log.info("The blocking time has passed. Removing a user from collections");
                blocked.remove(username);
                attempt.remove(username);
            }else {
                log.info("The user is blocked");
                return "-2";
            }
        }

        log.info("The user is not blocked");
        log.info("Checking whether the password is correct");
        if(password.equals(PASSWORD)){
            log.print("The password is correct");
            String token = new Util().generateToken();
            return token;
        }else{
            log.error("The password is incorrect");
            log.info("Checking if the user is in the collection attemp");
            System.out.println(attempt.containsKey(username));
            if(attempt.containsKey(username)){
                log.info("The user is in the collection attemp");
                int count = attempt.get(username);
                System.out.println(count);
                if(count<2){
                    count++;
                    attempt.put(username,count);
                    System.out.println(count);
                    if(count==1) {
                        log.info("One more attempt left");
                        return "1";
                    }
                    if(count == 2){
                        log.info("Adding the user to the collection blocked");
                        Date afterFiveMinute = new Date(System.currentTimeMillis()+60*1000);
                        System.out.println(afterFiveMinute);
                        blocked.put(username,afterFiveMinute);
                        return "0";
                    }
                }
            }else{
                log.info("User not in the collection attemp");
                log.info("Adding the user to the collection attemp");
                attempt.put(username,0);
                System.out.println(username);
                System.out.println(attempt.get(username));
                System.out.println(attempt.containsKey(username));
                return "2";
            }
        }
        return null;
    }

}
