package net.irregular.escapy.loader;

import com.badlogic.gdx.files.FileHandle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.loader
 * At 01.07.17
 */
public class JSONLoader {

    public static JSONObject loadJSONFile(String path) throws ParseException {

        JSONObject object = new JSONObject();

        FileHandle file = new FileHandle(System.getProperty("user.dir") + path);

        System.out.println(file.path());

        if(file.exists()) {
            object = (JSONObject) new JSONParser().parse(file.readString());
        }

        return object;
    }

}
