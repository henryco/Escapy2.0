package net.irregular.escapy.map.layer;

import java.util.Comparator;

/**
 * @author Henry on 12/07/17.
 */
public class LayerComparator implements Comparator<Layer> {

	@Override
	public int compare(Layer o1, Layer o2) {
		return Float.compare(o1.getAxisZ(), o2.getAxisZ());
	}
}
