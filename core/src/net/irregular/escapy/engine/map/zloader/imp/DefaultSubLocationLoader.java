package net.irregular.escapy.engine.map.zloader.imp;

import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.layer.shift.LayerShift;
import net.irregular.escapy.engine.map.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.map.layer.shift.LayerShifter;
import net.irregular.escapy.engine.map.location.SubLocation;
import net.irregular.escapy.engine.map.object.GameObject;
import net.irregular.escapy.engine.map.zloader.GameObjectLoader;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject;
import net.irregular.escapy.engine.map.zloader.serial.SerializedSubLocation;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import static net.irregular.escapy.engine.map.zloader.serial.SerializedSubLocation.*;


/**
 * @author Henry on 13/07/17.
 */
public class DefaultSubLocationLoader implements SubLocationLoader {


	private final EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstancer;
	private final GameObjectLoader<SerializedGameObject> gameObjectLoader;


	public DefaultSubLocationLoader(EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstancer,
									GameObjectLoader<SerializedGameObject> gameObjectLoader) {

		this.gameObjectLoader = gameObjectLoader;
		this.shiftLogicInstancer = shiftLogicInstancer;
	}



	@Override
	public SubLocation loadSubLocation(String path) {

		Collection<Layer> layers = new LinkedList<>();
		SerializedSubLocation serialized;

		try {
			Reader reader = new InputStreamReader(new FileInputStream(path));
			serialized = new Gson().fromJson(reader, SerializedSubLocation.class);
			if (serialized.layers == null) return null;
		} catch (Exception ignored) {return null;}

		for (SerializedLayer layer: serialized.layers)
			layers.add(loadLayer(layer));

		Collection<Entry<String, Layer[]>> layerContainer
				= loadRenderContainer(serialized.layerGroups, layers);

		return new SubLocation(serialized.name, layers, layerContainer);
	}



	private Layer loadLayer(SerializedLayer serializedLayer) {

		Layer layer = new Layer(serializedLayer.name, serializedLayer.axisZ);
		layer.setLayerShifter(loadLayerShift(serializedLayer.shift));
		layer.setGameObjects(loadGameObjects(serializedLayer.objects));
		return layer;
	}



	private LayerShift loadLayerShift(SerializedShift serializedShift) {

		LayerShifter shifter = new LayerShifter(null);
		shifter.setDirect(floatListToArray(serializedShift.directVec));
		shifter.setOffset(floatListToArray(serializedShift.offset));
		shifter.setPinPoint(floatListToArray(serializedShift.pinPoint));
		shifter.setLayerShiftLogic(shiftLogicInstancer.load(serializedShift.name));
		return shifter;
	}


	private Collection<GameObject> loadGameObjects(List<SerializedGameObject> serializedObjects) {

		Collection<GameObject> gameObjects = new LinkedList<>();
		for (SerializedGameObject object: serializedObjects)
			gameObjects.add(gameObjectLoader.loadGameObject(object));
		return gameObjects;
	}



	private Collection<Entry<String, Layer[]>> loadRenderContainer(
			List<SerializedLayerGroup> serialized, Collection<Layer> layers) {


		Collection<Entry<String, Layer[]>> collection = new LinkedList<>();

		for (SerializedLayerGroup container: serialized) {
			Layer[] multiLayer = new Layer[container.layers.size()];
			for (int i = 0; i < multiLayer.length; i++) {
				for (Layer layer : layers) {
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
