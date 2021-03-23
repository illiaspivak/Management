package sk.kosickaakademia.illiaspivak.company.helpclasses;


import org.json.JSONObject;

public class XML {
    public String FromJsonToXML (String jsonString){
        JSONObject json = new JSONObject(jsonString);
        String xml = org.json.XML.toString(json);
        return xml;
    }
}
