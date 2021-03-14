package sk.kosickaakademia.illiaspivak.company.controller;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.illiaspivak.company.database.Database;
import sk.kosickaakademia.illiaspivak.company.entity.User;
import sk.kosickaakademia.illiaspivak.company.helpclasses.Gender;
import sk.kosickaakademia.illiaspivak.company.log.Log;

@RestController
public class Controller {
    Log log = new Log();

    @PostMapping("/user/new")
    public ResponseEntity<String> insertNewUser(@RequestBody String data){
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(data);
            String fname = ((String)object.get("fname")).trim();
            String lname = ((String)object.get("lname")).trim();
            String gender = (String)object.get("gender");
            int age = Integer.parseInt(String.valueOf(object.get("age")));

            //Error output if there is not enough input data
            if(fname==null || lname==null || fname.length() ==0 || lname.length()==0 || age < 1){
                log.error("Not enough data");
                JSONObject objectError = new JSONObject();
                objectError.put("error", "Not enough data, missing firstname of lastname");
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(objectError.toJSONString());
            }

            //Determining the gender based on the entered data
            Gender genderObject ;
            if(gender==null){
                genderObject=Gender.OTHER;
            }else if(gender.equalsIgnoreCase("male")){
                genderObject=Gender.MALE;
            }else if(gender.equalsIgnoreCase("female")){
                genderObject=Gender.FEMALE;
            }else genderObject=Gender.OTHER;

            //Adding a new user to the database
            User user = new User(fname,lname,age,genderObject.getValue());
            new Database().insertNewUser(user);

        } catch (Exception e) {
            JSONObject obj = new JSONObject();
            log.error("Cannot process input data ");
            obj.put("error","Cannot process input data ");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return null;
    }
}
