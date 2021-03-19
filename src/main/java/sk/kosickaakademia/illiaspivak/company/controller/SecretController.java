package sk.kosickaakademia.illiaspivak.company.controller;

import org.springframework.web.bind.annotation.*;
import sk.kosickaakademia.illiaspivak.company.log.Log;


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

}
