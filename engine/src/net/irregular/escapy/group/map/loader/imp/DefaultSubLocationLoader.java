package net.irregular.escapy.group.map.loader.imp;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.irregular.escapy.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.group.map.core.layer.Layer;
import net.irregular.escapy.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.group.map.core.layer.shift.LayerShiftLogic;
import net.irregular.escapy.group.map.core.layer.shift.LayerShifter;
import net.irregular.escapy.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.group.map.core.location.SubLocation;
import net.irregular.escapy.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.group.map.loader.GameObjectLoader;
import net.irregular.escapy.group.map.loader.SubLocationLoader;
import net.irregular.escapy.group.map.loader.serial.SerializedGameObject;
import net.irregular.escapy.group.map.loader.serial.SerializedSubLocation;
import net.irregular.escapy.utils.EscapyLogger;
import net.irregular.escapy.utils.loader.EscapyInstanceLoader;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;

import static java.io.File.separator;
import static net.irregular.escapy.group.map.loader.serial.SerializedSubLocation.*;


/**
 * @author Henry on 13/07/17.
 */
public class DefaultSubLocationLoader implements SubLocationLoader {


	private final EscapyInstanceLoader<LayerShiftLogic> shiftLogicAttributeLoader;
	private final EscapyInstanceLoader<EscapyLayer> layerAttributeLoader;
	private final EscapyInstanceLoader<EscapySubLocation> subLocationAttributeLoader;
	private final GameObjectLoader<SerializedGameObject> gameObjectLoader;


	public DefaultSubLocationLoader(EscapyInstanceLoader<LayerShiftLogic> shiftLogicAttributeLoader,
									EscapyInstanceLoader<EscapyLayer> layerAttributeLoader,
									EscapyInstanceLoader<EscapySubLocation> subLocationAttributeLoader,
									GameObjectLoader<SerializedGameObject> gameObjectLoader) {

		this.shiftLogicAttributeLoader = shiftLogicAttributeLoader;
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

		LayerShiftLogic shiftLogic = shift -> new float[]{0,0};
		if (shiftLogicAttributeLoader != null)
			shiftLogic = shiftLogicAttributeLoader.loadInstanceAttributes(shiftLogic, serializedShift.attributes);
		shifter.setLayerShiftLogic(shiftLogic);

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
			collection.add(new AbstractMap.SimpleEntry<>(container.getName(), multiLayer));
		}

		return collection;
	}

}