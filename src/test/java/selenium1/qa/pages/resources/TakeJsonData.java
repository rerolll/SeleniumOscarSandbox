package selenium1.qa.pages.resources;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class TakeJsonData {
    public static String take_json_data(String data){
        try {
            FileReader reader = new FileReader("config.json");
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            String temp = jsonObject.get(data).getAsString();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";//Sdelay normalno
    }
}
