package net.irregular.escapy.engine.map.zloader.imp;

import com.google.gson.Gson;
import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.layer.shift.LayerShift;
import net.irregular.escapy.engine.map.layer.shift.LayerShiftLogicInstancer;
import net.irregular.escapy.engine.map.layer.shift.LayerShifter;
import net.irregular.escapy.engine.map.location.SubLocation;
import net.irregular.escapy.engine.map.object.GameObject;
import net.irregular.escapy.engine.map.object.ObjectDetails;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedSubLocation;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static net.irregular.escapy.engine.map.zloader.serial.SerializedSubLocation.*;

/**
 * @author Henry on 13/07/17.
 */
public class DefaultSubLocationLoader implements SubLocationLoader {


	private final Comparator<Layer> layerComparator;
	private final LayerShiftLogicInstancer shiftLogicInstancer;



	public DefaultSubLocationLoader(Comparator<Layer> layerComparator,
									LayerShiftLogicInstancer shiftLogicInstancer) {

		this.layerComparator = layerComparator;
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

		return new SubLocation(serialized.name, layers, layerComparator);
	}



	private Layer loadLayer(SerializedLayer serializedLayer) {

		Layer layer = new Layer(serializedLayer.name, serializedLayer.axisZ);
		layer.setLayerShifter(loadLayerShift(serializedLayer.shift));
		layer.setGameObjects(loadGameObjects(serializedLayer.objects));
		return layer;
	}



	private LayerShift loadLayerShift(SerializedShift serializedShift) {

		LayerShifter shifter = new LayerShifter(null);
		shifter.setDirect(floatToFloat2f(serializedShift.directVec));
		shifter.setOffset(floatToFloat2f(serializedShift.offset));
		shifter.setPinPoint(floatToFloat2f(serializedShift.pinPoint));
		shifter.setLayerShiftLogic(shiftLogicInstancer.load(serializedShift.logic));
		return shifter;
	}


	private Collection<GameObject> loadGameObjects(List<SerializedObject> serializedObjects) {

		Collection<GameObject> gameObjects = new LinkedList<>();
		for (SerializedObject object: serializedObjects) {

			ObjectDetails details = loadObjectDetails(object.details);



		}

		return null;
	}


	private ObjectDetails loadObjectDetails(SerializedDetails serialized) {

		ObjectDetails details = new ObjectDetails(serialized.name);
		details.setScale(serialized.scale);
		details.setThickness(serialized.thickness);
		details.setPosition(floatToFloat2f(serialized.position));
		return details;
	}


	private static float[] floatToFloat2f(List<Float> floats) {
		return new float[]{floats.get(0), floats.get(1)};
	}
}
