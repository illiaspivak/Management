package sk.kosickaakademia.illiaspivak.company.util;

import com.mysql.cj.xdevapi.JsonArray;
import org.json.XML;
import sk.kosickaakademia.illiaspivak.company.database.Database;
import sk.kosickaakademia.illiaspivak.company.entity.User;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import sk.kosickaakademia.illiaspivak.company.helpclasses.Gender;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Util {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public String getJson (List<User> list){
        if (list.isEmpty()) return "{}";
        JSONObject object = new JSONObject();
        object.put("datetime",getCurrentTime());
        object.put("size",list.size());
        JSONArray jsonArray = new JSONArray();
        for (User user: list){
            JSONObject userJson = new JSONObject();
            userJson.put("id", user.getId());
            userJson.put("fname", user.getFname());
            userJson.put("lname", user.getLname());
            userJson.put("age", user.getAge());
            userJson.put("gender", user.getGender().toString());
            jsonArray.add(userJson);
        }

        object.put("users",jsonArray);

        return object.toJSONString();
    }

    public String getJson (User user){
        if (user==null) return "{}";
        JSONObject object = new JSONObject();
        object.put("datetime",getCurrentTime());
        object.put("size",1);
        JSONArray jsonArray = new JSONArray();
        JSONObject userJson = new JSONObject();
        userJson.put("id",user.getId());
        userJson.put("fname",user.getFname());
        userJson.put("lname",user.getLname());
        userJson.put("age",user.getAge());
        userJson.put("gender",user.getGender().toString());
        jsonArray.add(userJson);
        object.put("users",jsonArray);

        return object.toJSONString();
    }

    public String getCurrentTime(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatForDateNow.format(dateNow);
    }

    public String normalizeName (String name){
        if(name==null || name.equals(""))
            return "";
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String getOverview(List<User> list){
        int male = 0, female = 0, sumAge = 0;
        int min = list.size()>0? list.get(0).getAge():0;
        int max = list.size()>0? list.get(0).getAge():0;
        for(User user : list){
            if(user.getGender()== Gender.FEMALE){
                female++;
            }else if (user.getGender()== Gender.MALE){
                male++;
            }
            sumAge += user.getAge();
            if(min>user.getAge()){
                min=user.getAge();
            }
            if(max<user.getAge()){
                max=user.getAge();
            }
        }
        double age = (double)sumAge/list.size();
        JSONObject object = new JSONObject();
        object.put("count",list.size());
        object.put("male",male);
        object.put("female",female);
        object.put("age",age);
        object.put("min",min);
        object.put("max",max);
        return object.toJSONString();


    }


    public static String generateToken(){
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }





}
