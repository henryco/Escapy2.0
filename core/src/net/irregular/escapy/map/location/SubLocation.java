package net.irregular.escapy.map.location;

import net.irregular.escapy.engine.env.utils.Named;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.map.layer.Layer;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * @author Henry on 11/07/17.
 */
public class SubLocation implements Named {

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
		setLayers(layers);
		setLayerComparator(layerComparator);
	}


	public void addLayer(Layer layer) {
		layerArray.add(layer, layer.getName());
		layerArray.sort(layerComparator);
	}


	public void setLayers(Collection<Layer> layers) {
		Collection<String> names = new LinkedList<>();
		for (Layer l: layers) names.add(l.getName());
		layerArray.addAll(names, layers);
	}

	public void setLayerComparator(Comparator<Layer> comparator) {
		this.layerComparator = comparator;
	}

	@Override
	public String getName() {
		return name;
	}
}
