package net.irregular.escapy.engine.map.zloader.imp;

import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.location.SubLocation;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author Henry on 13/07/17.
 */
public class DefaultSubLocationLoader implements SubLocationLoader {


	private final Comparator<Layer> layerComparator;

	public DefaultSubLocationLoader(Comparator<Layer> layerComparator) {
		this.layerComparator = layerComparator;
	}

	@Override
	public SubLocation loadSubLocation(String name) {


		Collection<Layer> layers = new LinkedList<>();


		return new SubLocation("", layers, layerComparator);
	}
}
