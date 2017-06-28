package net.irregular.escapy.objects;

import java.util.List;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.objects
 * At 29.06.17
 */
public class Location implements Loadable {

    private String name;
    private List<Layer> layers;

    public Location(String name) {
        this.name = name;
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

    public Layer findLayer(String name) {
        for (Layer layer :
                layers) {
            if(layer.getName().equals(name)) return  layer;
        }
        return null;
    }

    public void removeLayer(Layer layer) {
        layers.remove(layer);
    }

    public void addNewLayer() {
        //TODO: implement addition of new layer to list.
    }

}
