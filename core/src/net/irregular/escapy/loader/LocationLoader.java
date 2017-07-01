package net.irregular.escapy.loader;

import net.irregular.escapy.objects.Layer;
import net.irregular.escapy.objects.Loadable;
import net.irregular.escapy.objects.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.loader
 * At 30.06.17
 */
public class LocationLoader implements Loader {

    private JSONObject location;

    private Location currentLocation;

    private Layer tempLayer;
    private Location tempLocation;

    @Override
    public Loadable loadObject(String file) {

        try {
            location = JSONLoader.loadJSONFile(file);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(location.containsKey("level_name")) currentLocation = new Location(location.get("level_name").toString());

        if(location.containsKey("layers")) {
            JSONArray layers = (JSONArray) location.get("layers");
            Iterator<String> iterator = layers.iterator();

            while (iterator.hasNext()) {
                tempLayer = (Layer) new LayerLoader().loadObject(iterator.next());
                currentLocation.addNewLayer(tempLayer);
                //TODO: add necessary logic for layer loading
            }
        }

        if(location.containsKey("locations")) {
            JSONArray locations = (JSONArray) location.get("locations");
            Iterator<String> iterator = locations.iterator();

            while (iterator.hasNext()) {
                tempLocation = (Location) new LocationLoader().loadObject(iterator.next());
                currentLocation.addNewLocation(tempLocation);
                //TODO: add necessary logic for locations loading
            }

            System.out.println(currentLocation.getLocations());
        }

        return currentLocation;
    }

}
