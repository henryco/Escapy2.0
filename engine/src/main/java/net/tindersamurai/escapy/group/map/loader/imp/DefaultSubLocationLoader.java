package net.tindersamurai.escapy.group.map.loader.imp;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.tindersamurai.escapy.group.map.core.layer.EscapyLayer;
import net.tindersamurai.escapy.group.map.core.layer.Layer;
import net.tindersamurai.escapy.group.map.core.layer.shift.LayerShift;
import net.tindersamurai.escapy.group.map.core.layer.shift.LayerShifter;
import net.tindersamurai.escapy.group.map.core.location.EscapySubLocation;
import net.tindersamurai.escapy.group.map.core.location.SubLocation;
import net.tindersamurai.escapy.group.map.core.object.EscapyGameObject;
import net.tindersamurai.escapy.group.map.loader.GameObjectLoader;
import net.tindersamurai.escapy.group.map.loader.SubLocationLoader;
import net.tindersamurai.escapy.group.map.loader.serial.SerializedGameObject;
import net.tindersamurai.escapy.group.map.loader.serial.SerializedSubLocation;
import net.tindersamurai.escapy.utils.EscapyLogger;
import net.tindersamurai.escapy.utils.loader.EscapyInstanceLoader;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;

import static java.io.File.separator;
import static net.tindersamurai.escapy.group.map.loader.serial.SerializedSubLocation.*;


/**
 * @author Henry on 13/07/17.
 */
public class DefaultSubLocationLoader implements SubLocationLoader {


	private final EscapyInstanceLoader<LayerShift> shiftAttributeLoader;
	private final EscapyInstanceLoader<EscapyLayer> layerAttributeLoader;
	private final EscapyInstanceLoader<EscapySubLocation> subLocationAttributeLoader;
	private final GameObjectLoader<SerializedGameObject> gameObjectLoader;


	public DefaultSubLocationLoader(EscapyInstanceLoader<LayerShift> shiftAttributeLoader,
									EscapyInstanceLoader<EscapyLayer> layerAttributeLoader,
									EscapyInstanceLoader<EscapySubLocation> subLocationAttributeLoader,
									GameObjectLoader<SerializedGameObject> gameObjectLoader) {

		this.shiftAttributeLoader = shiftAttributeLoader;
		this.layerAttributeLoader = layerAttributeLoader;
		this.subLocationAttributeLoader = subLocationAttributeLoader;
		this.gameObjectLoader = gameObjectLoader;
	}


	@Override
	public EscapySubLocation loadSubLocation(String path) {

		Collection<EscapyLayer> layers = new LinkedList<>();
		SerializedSubLocation serialized;
		String safePath = safetyPath(path);
		String folder = safePath.substring(0, safePath.lastIndexOf(separator));

		try {
			Reader reader = new InputStreamReader(Gdx.files.internal(safePath).read());
			serialized = new Gson().fromJson(reader, SerializedSubLocation.class);
			if (serialized.layers == null) return null;
		} catch (Exception e) {
			new EscapyLogger("SubLocationLoader").fileJava().log(e, true);
			return null;
		}

		for (SerializedLayer layer: serialized.layers)
			layers.add(loadLayer(folder, layer));

		Collection<Entry<String, EscapyLayer[]>> layerContainer
				= loadRenderContainer(serialized.layerGroups, layers);

		EscapySubLocation subLocation = new SubLocation(serialized.name, layers, layerContainer);
		if (subLocationAttributeLoader != null)
			return subLocationAttributeLoader.loadInstanceAttributes(subLocation, serialized.attributes);

		return subLocation;
	}



	private EscapyLayer loadLayer(String path, SerializedLayer serializedLayer) {

		Layer layer = new Layer(serializedLayer.name, serializedLayer.axisZ);
		layer.setLayerShifter(loadLayerShift(serializedLayer.shift));
		layer.setGameObjects(loadGameObjects(path, serializedLayer.objects));
		return loadLayerAttributes(layer, serializedLayer.attributes);
	}



	private LayerShift loadLayerShift(SerializedShift serializedShift) {

		LayerShifter shifter = new LayerShifter(null);
		if (serializedShift == null) return shifter;

		shifter.setName(serializedShift.name);
		shifter.setDirect(floatListToArray(serializedShift.directVec));
		shifter.setOffset(floatListToArray(serializedShift.offset));
		shifter.setPinPoint(floatListToArray(serializedShift.pinPoint));

		if (shiftAttributeLoader != null)
			return shiftAttributeLoader.loadInstanceAttributes(shifter, serializedShift.attributes);
		return shifter;
	}



	private EscapyLayer loadLayerAttributes(EscapyLayer layer, Collection<String> attributes) {

		if (layer == null || layerAttributeLoader == null) return layer;
		for (String attr: attributes) {
			EscapyLayer loaded = layerAttributeLoader.loadInstance(attr, layer);
			layer = loaded != null ? loaded : layer;
		}
		return layer;
	}



	private Collection<EscapyGameObject> loadGameObjects(String path, Collection<SerializedGameObject> serializedObjects) {

		Collection<EscapyGameObject> gameObjects = new LinkedList<>();
		for (SerializedGameObject object: serializedObjects)
			gameObjects.add(gameObjectLoader.loadGameObject(path, object));
		return gameObjects;
	}



	private Collection<Entry<String, EscapyLayer[]>> loadRenderContainer(
			Collection<SerializedLayerGroup> serialized, Collection<EscapyLayer> layers) {


		Collection<Entry<String, EscapyLayer[]>> collection = new LinkedList<>();

		for (SerializedLayerGroup container: serialized) {
			EscapyLayer[] multiLayer = new Layer[container.layers.size()];
			for (int i = 0; i < multiLayer.length; i++) {
				for (EscapyLayer layer : layers) {
					if (layer.getName().equals(container.layers.get(i))) {
						multiLayer[i] = layer;
						break;
					}
				}
			}
			Arrays.sort(multiLayer, (o1, o2) -> Float.compare(o1.getAxisZ(), o2.getAxisZ()));
			collection.add(new AbstractMap.SimpleEntry<>(container.getName(), multiLayer));
		}

		return collection;
	}

}