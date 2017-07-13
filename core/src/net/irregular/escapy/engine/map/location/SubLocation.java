package net.irregular.escapy.engine.map.location;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.map.layer.Layer;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * @author Henry on 11/07/17.
 */
public class SubLocation implements EscapyObject {

	public final String name;
	public final EscapyAssociatedArray<Layer> layerArray;
	private Comparator<Layer> layerComparator;


	public SubLocation(String name) {
		this.name = name;
		layerArray = new EscapyNamedArray<>(Layer.class);
	}

	public SubLocation(String name,
					   Collection<Layer> layers,
					   Comparator<Layer> layerComparator) {
		this(name);
		setLayerComparator(layerComparator);
		setLayers(layers);
	}


	public void addLayer(Layer layer) {
		layerArray.add(layer);
		layerArray.sort(layerComparator);
	}


	public void setLayers(Collection<Layer> layers) {
		Collection<String> names = new LinkedList<>();
		for (Layer l: layers) {
			names.add(l.getName());
		}
		layerArray.addAll(names, layers);
		layerArray.sort(layerComparator);
	}

	public void setLayerComparator(Comparator<Layer> comparator) {
		this.layerComparator = comparator;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void dispose() {
		for (Layer layer: layerArray) layer.dispose();
	}
}