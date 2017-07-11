package net.irregular.escapy.map.location;

import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.map.layer.Layer;

import java.util.Collection;
import java.util.LinkedList;


/**
 * @author Henry on 11/07/17.
 */
public class SubLocation {

	public final String name;
	public final EscapyNamedArray<Layer> layerArray;


	public SubLocation(String name) {
		this.name = name;
		layerArray = new EscapyNamedArray<>(Layer.class);
	}


	public void setLayers(Collection<Layer> layers) {
		Collection<String> names = new LinkedList<>();
		for (Layer l: layers) {

		}
	}

	public String getName() {
		return name;
	}
}
