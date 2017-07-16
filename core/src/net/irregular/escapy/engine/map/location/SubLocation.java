package net.irregular.escapy.engine.map.location;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.map.layer.Layer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;


/**
 * @author Henry on 11/07/17.
 */
public class SubLocation implements EscapyObject {

	public final String name;

	private final EscapyAssociatedArray<Layer> layerArray;
	private final EscapyAssociatedArray<Layer[]> layerContainer;


	public SubLocation(String name,
					   Collection<Layer> layers,
					   Collection<Entry<String, Layer[]>> layerContainer) {

		this.name = name;
		this.layerArray = new EscapyNamedArray<>(Layer.class);
		this.layerContainer = new EscapyNamedArray<>(Layer[].class);
		setLayerContainer(layerContainer);
		setLayers(layers);
	}


	public EscapyAssociatedArray<Layer[]> getLayerContainer() {
		return layerContainer;
	}



	private void setLayers(Collection<Layer> layers) {
		Collection<String> names = new LinkedList<>();
		for (Layer l: layers) names.add(l.getName());
		layerArray.addAll(names, layers);
	}

	private void setLayerContainer(Collection<Entry<String, Layer[]>> container) {
		for (Entry<String, Layer[]> entry: container)
			layerContainer.add(entry.getValue(), entry.getKey());
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