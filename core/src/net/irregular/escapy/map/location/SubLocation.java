package net.irregular.escapy.map.location;

import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.map.layer.Layer;

import java.util.Collection;
import java.util.LinkedList;


/**
 * @author Henry on 11/07/17.
 */
public class SubLocation {

	public final String name;
	public final EscapyAssociatedArray<Layer> layerArray;


	public SubLocation(String name) {
		this.name = name;
		layerArray = new EscapyNamedArray<>(Layer.class);
	}

	public SubLocation(String name, Collection<Layer> layers) {
		this(name);
		setLayers(layers);
	}


	public void addLayer(Layer layer) {

	}


	public void setLayers(Collection<Layer> layers) {
		Collection<String> names = new LinkedList<>();
		for (Layer l: layers) names.add(l.getName());
		layerArray.addAll(names, layers);
	}

	public String getName() {
		return name;
	}
}
