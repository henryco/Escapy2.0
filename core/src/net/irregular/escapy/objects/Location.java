package net.irregular.escapy.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.objects
 * At 29.06.17
 */
public class Location implements Loadable {

    private String name;
    private List<Location> locations;
    private List<Layer> layers;

    public Location(String name) {
        this.name = name;
        this.locations = new ArrayList<Location>();
        this.layers = new ArrayList<Layer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Layer> getLayers() {
        return this.layers;
    }

    public List<Location> getLocations() {
        return this.locations;
    }

    public Layer findLayer(String name) {
        for (Layer layer :
                layers) {
            if(layer.getName().equals(name)) return  layer;
        }
        return null;
    }

    public void removeLayer(Layer layer) {
        layers.remove(layer);
        this.sortLayers();
    }

    public void addNewLayer(Layer layer) {
        layers.add(layer);
        this.sortLayers();
    }

    public void addNewLocation(Location location) {
        locations.add(location);
    }

    private void sortLayers() {
        Collections.sort(layers);
    }

}
