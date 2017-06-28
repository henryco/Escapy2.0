package net.irregular.escapy.objects;

import java.util.List;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.objects
 * At 29.06.17
 */
public class GameMap implements Loadable {

    private String name;
    private List<Location> locations;

    public GameMap(String name) {
        this.name = name;
    }

    public List<Location> getLocations() {
        return this.locations;
    }
    
    public Location findLocation(String name) {
        for (Location location :
                locations) {
            if (location.getName().equals(name)) return location;
        }
        return null;
    }

}
