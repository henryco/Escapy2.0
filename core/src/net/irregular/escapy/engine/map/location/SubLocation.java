package net.irregular.escapy.engine.map.location;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.map.layer.Layer;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * @author Henry on 11/07/17.
 */
public class SubLocation implements EscapyObject {

	public final String name;

	private final EscapyAssociatedArray<Layer> layerArray;
	private final Comparator<Layer> layerComparator;
	private final EscapyRenderable renderContainer;

	public SubLocation(String name,
					   Collection<Layer> layers,
					   Comparator<Layer> layerComparator,
					   EscapyRenderable renderContainer) {

		this.layerArray = new EscapyNamedArray<>(Layer.class);
		this.layerComparator = layerComparator;
		this.renderContainer = renderContainer;
		this.name = name;
		setLayers(layers);
	}




	private void setLayers(Collection<Layer> layers) {
		Collection<String> names = new LinkedList<>();
		for (Layer l: layers) {
			names.add(l.getName());
		}
		layerArray.addAll(names, layers);
		layerArray.sort(layerComparator);
	}


	public EscapyRenderable getRenderable() {
		return renderContainer;
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