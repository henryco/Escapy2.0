package net.irregular.escapy.engine.group.map.loader.imp;

import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.engine.group.map.core.layer.Layer;
import net.irregular.escapy.engine.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.engine.group.map.core.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.group.map.core.layer.shift.LayerShifter;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.map.core.location.SubLocation;
import net.irregular.escapy.engine.group.map.core.object.GameObject;
import net.irregular.escapy.engine.group.map.loader.GameObjectLoader;
import net.irregular.escapy.engine.group.map.loader.SubLocationLoader;
import net.irregular.escapy.engine.group.map.loader.serial.SerializedGameObject;
import net.irregular.escapy.engine.group.map.loader.serial.SerializedSubLocation;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;

import static net.irregular.escapy.engine.group.map.loader.serial.SerializedSubLocation.*;


/**
 * @author Henry on 13/07/17.
 */
public class DefaultSubLocationLoader implements SubLocationLoader {


	private final EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstancer;
	private final EscapyInstanceLoader<EscapyLayer> layerInstanceAttributeLoader;
	private final EscapyInstanceLoader<EscapySubLocation> subLocationInstanceAttributeLoader;
	private final GameObjectLoader<SerializedGameObject> gameObjectLoader;


	public DefaultSubLocationLoader(EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstancer,
									EscapyInstanceLoader<EscapyLayer> layerInstanceAttributeLoader,
									EscapyInstanceLoader<EscapySubLocation> subLocationInstanceAttributeLoader,
									GameObjectLoader<SerializedGameObject> gameObjectLoader) {

		this.shiftLogicInstancer = shiftLogicInstancer;
		this.layerInstanceAttributeLoader = layerInstanceAttributeLoader;
		this.subLocationInstanceAttributeLoader = subLocationInstanceAttributeLoader;
		this.gameObjectLoader = gameObjectLoader;
	}


	@Override
	public EscapySubLocation loadSubLocation(String path) {

		Collection<EscapyLayer> layers = new LinkedList<>();
		SerializedSubLocation serialized;

		try {
			Reader reader = new InputStreamReader(new FileInputStream(path));
			serialized = new Gson().fromJson(reader, SerializedSubLocation.class);
			if (serialized.layers == null) return null;
		} catch (Exception ignored) {return null;}

		for (SerializedLayer layer: serialized.layers)
			layers.add(loadLayer(layer));

		Collection<Entry<String, EscapyLayer[]>> layerContainer
				= loadRenderContainer(serialized.layerGroups, layers);

		EscapySubLocation subLocation = new SubLocation(serialized.name, layers, layerContainer);
		if (subLocationInstanceAttributeLoader != null)
			return subLocationInstanceAttributeLoader.loadInstanceAttributes(subLocation, serialized.attributes);

		return subLocation;
	}



	private EscapyLayer loadLayer(SerializedLayer serializedLayer) {

		Layer layer = new Layer(serializedLayer.name, serializedLayer.axisZ);
		layer.setLayerShifter(loadLayerShift(serializedLayer.shift));
		layer.setGameObjects(loadGameObjects(serializedLayer.objects));
		return loadLayerAttributes(layer, serializedLayer.attributes);
	}



	private LayerShift loadLayerShift(SerializedShift serializedShift) {

		LayerShifter shifter = new LayerShifter(null);
		shifter.setDirect(floatListToArray(serializedShift.directVec));
		shifter.setOffset(floatListToArray(serializedShift.offset));
		shifter.setPinPoint(floatListToArray(serializedShift.pinPoint));
		shifter.setLayerShiftLogic(shiftLogicInstancer.loadInstance(serializedShift.name));
		return shifter;
	}



	private EscapyLayer loadLayerAttributes(EscapyLayer layer, Collection<String> attributes) {

		if (layer == null || layerInstanceAttributeLoader == null) return layer;
		for (String attr: attributes) {
			EscapyLayer loaded = layerInstanceAttributeLoader.loadInstance(attr, layer);
			layer = loaded != null ? loaded : layer;
		}
		return layer;
	}



	private Collection<GameObject> loadGameObjects(Collection<SerializedGameObject> serializedObjects) {

		Collection<GameObject> gameObjects = new LinkedList<>();
		for (SerializedGameObject object: serializedObjects)
			gameObjects.add(gameObjectLoader.loadGameObject(object));
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
			collection.add(new AbstractMap.SimpleEntry<>(container.getName(), multiLayer));
		}

		return collection;
	}

}
