package sk.kosickaakademia.illiaspivak.company.util;

import sk.kosickaakademia.illiaspivak.company.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {

    public String getJson (List<User> list){

        return null;
    }

    public String getJson (User user){

        return null;
    }

    public String getCurrentTime(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return formatForDateNow.format(dateNow);
    }

    public String normalizeName (String name){
        return name;
    }
}
