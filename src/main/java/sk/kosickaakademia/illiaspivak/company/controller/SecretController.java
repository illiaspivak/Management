package sk.kosickaakademia.illiaspivak.company.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kosickaakademia.illiaspivak.company.log.Log;
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
        String token = header.substring(7);
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
            if (password.equals(PASSWORD)){
                String token = new Util().generateToken();
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

}
