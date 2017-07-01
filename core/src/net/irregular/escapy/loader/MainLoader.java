package net.irregular.escapy.loader;

import net.irregular.escapy.objects.Loadable;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.loader
 * At 30.06.17
 */
public class MainLoader implements Loader {

    private String rootPath;
    private String configsPath;

    private JSONObject mainConfig;

    private Loadable currentScene;


    public MainLoader(String path) {

        try {
            mainConfig = JSONLoader.loadJSONFile(path);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rootPath = mainConfig.get("root_path").toString();
        configsPath = mainConfig.get("configs_path").toString();

        if(mainConfig.containsKey("main_scene")) {
            currentScene = new LocationLoader().loadObject(rootPath + configsPath + mainConfig.get("main_scene").toString());
        }

    }


    @Override
    public Loadable loadObject(String file) {
        return null;
    }
}
