package sk.kosickaakademia.illiaspivak.company.util;

import com.mysql.cj.xdevapi.JsonArray;
import sk.kosickaakademia.illiaspivak.company.entity.User;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {

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
}
