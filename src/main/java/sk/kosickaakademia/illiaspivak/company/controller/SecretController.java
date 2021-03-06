package sk.kosickaakademia.illiaspivak.company.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kosickaakademia.illiaspivak.company.log.Log;
import sk.kosickaakademia.illiaspivak.company.util.Login;
import sk.kosickaakademia.illiaspivak.company.util.Util;


import java.util.HashMap;
import java.util.Map;

@RestController
public class SecretController {
    private final String PASSWORD = "bigdog";
    Map<String, String> map = new HashMap<>();
    Log log = new Log();


    @GetMapping("/secret")
    public String secret(@RequestHeader("token") String header){
        System.out.println(header);
        String token = header.substring(7); // without Bearer
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getValue().equalsIgnoreCase(token)){
                return "secret";
            }
        }
        return "401";
    }


    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody String body){

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(body);

            String login = ((String) jsonObject.get("login"));
            String password = ((String) jsonObject.get("password"));

            if (login == null || login.isBlank() || password == null || password.isBlank()){
                return ResponseEntity.status(400).body("Bad request.");
            }
            String token = new Login().loginUser(login,password);

            if (token == "2" || token == "1"){
                return ResponseEntity.status(401).body("Incorrect password! Number of remaining attempts: " + token);
            }
            if (token == "0"){
                return ResponseEntity.status(401).body("Incorrect password! Number of remaining attempts: " + token + ". You are blocked. Wait one minute");
            }
            if (token == "-2"){
                return ResponseEntity.status(401).body("You are blocked. Wait one minute");
            }

            if (token != null){
                map.put(login, token);
                JSONObject obj = new JSONObject();
                obj.put("info", "User logged in.");
                obj.put("login", login);
                obj.put("token", "Bearer "+token);
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
            }else {
                return ResponseEntity.status(401).body("Incorrect password.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(400).body("Bad request.");
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<String> logOut(@RequestHeader("token") String token){
        if (token == null || token.isBlank())
            return ResponseEntity.status(401).body("Invalid token");

        for (Map.Entry<String, String> entry: map.entrySet()){
            if (("Bearer "+entry.getValue()).equals(token)) {
                if (map.remove(entry.getKey(), entry.getValue())) {
                    return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("You have been logged out");
                }else {
                    return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body("Internal server error");
                }
            }
        }
        return ResponseEntity.status(401).body("Invalid token");
    }

    @GetMapping(path = "/erasmus")
    public ResponseEntity<String> erasmus(@RequestHeader("token") String token){
        JSONObject student = new JSONObject();
        student.put("first name", "Vlad");
        student.put("last name", "Skobley");

        if (isLoggedIn(token)){
            student.put("country", "Greece");
            student.put("stipend", "650$");

            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(student.toJSONString());
        }else {
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(student.toJSONString());
        }
    }

    /**
     * Checking whether you are logged in
     * @param token
     * @return true/false
     */
    private boolean isLoggedIn(String token) {
        if (token == null || token.isBlank())
            return false;

        for (Map.Entry<String, String> entry: map.entrySet()){
            if (("Bearer "+entry.getValue()).equals(token)) {
                return true;
            }
        }
        return false;
    }

}
