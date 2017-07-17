package net.irregular.escapy.engine.map.location;

import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.map.layer.EscapyLayer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;


/**
 * @author Henry on 11/07/17.
 */
public class SubLocation implements EscapySubLocation {

	public final String name;

	public final EscapyAssociatedArray<EscapyLayer> layerArray;
	public final EscapyAssociatedArray<EscapyLayer[]> layerGroupArray;


	public SubLocation(String name,
					   Collection<EscapyLayer> layers,
					   Collection<Entry<String, EscapyLayer[]>> layerGroupArray) {

		this.name = name;
		this.layerArray = new EscapyNamedArray<>(EscapyLayer.class);
		this.layerGroupArray = new EscapyNamedArray<>(EscapyLayer[].class);
		setLayerGroupArray(layerGroupArray);
		setLayers(layers);
	}


	@Override
	public EscapyAssociatedArray<EscapyLayer> getLayers() {
		return layerArray;
	}

	@Override
	public EscapyAssociatedArray<EscapyLayer[]> getLayerGroups() {
		return layerGroupArray;
	}



	private void setLayers(Collection<EscapyLayer> layers) {
		Collection<String> names = new LinkedList<>();
		for (EscapyLayer l: layers) names.add(l.getName());
		layerArray.addAll(names, layers);
	}

	private void setLayerGroupArray(Collection<Entry<String, EscapyLayer[]>> container) {
		for (Entry<String, EscapyLayer[]> entry: container)
			layerGroupArray.add(entry.getValue(), entry.getKey());
	}



	@Override
	public String getName() {
		return name;
	}

	@Override
	public void dispose() {
		for (EscapyLayer layer: layerArray) layer.dispose();
	}
}