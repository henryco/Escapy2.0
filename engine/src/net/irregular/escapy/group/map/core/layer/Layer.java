package net.irregular.escapy.group.map.core.layer;

import net.irregular.escapy.environment.utils.array.EscapyAssociatedArray;
import net.irregular.escapy.environment.utils.array.EscapyNamedArray;
import net.irregular.escapy.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.group.map.core.object.EscapyGameObject;

import java.util.Collection;
import java.util.LinkedList;


/**
 * @author Henry on 11/07/17.
 */
public class Layer implements EscapyLayer {


	private LayerShift layerShifter;

	public final String name;
	public final float axisZ;
	public final EscapyAssociatedArray<EscapyGameObject> gameObjects;


	public Layer(final String name) {
		this(name, 0);
	}

	public Layer(final String name, final float axisZ) {
		this.name = name;
		this.axisZ = axisZ;
		this.gameObjects = new EscapyNamedArray<>(EscapyGameObject.class);
	}

	public Layer(final String name,
				 final float axisZ,
				 Collection<EscapyGameObject> objects,
				 LayerShift layerShifter) {
		this(name, axisZ);
		setGameObjects(objects);
		setLayerShifter(layerShifter);
	}


	public void setGameObjects(Collection<EscapyGameObject> objects) {
		Collection<String> objectNames = new LinkedList<>();
		for (EscapyGameObject object: objects) objectNames.add(object.getObjectDetails().getName());
		gameObjects.addAll(objectNames, objects);
	}

	public void setLayerShifter(LayerShift layerShifter) {
		this.layerShifter = layerShifter;
	}



	@Override public EscapyAssociatedArray<EscapyGameObject> getGameObjects() {
		return gameObjects;
	}
	@Override public LayerShift getLayerShifter() {
		return layerShifter;
	}
	@Override public float getAxisZ() {
		return axisZ;
	}
	@Override public String getName() {
		return name;
	}



	@Override
	public void dispose() {
		for (EscapyGameObject gameObject: gameObjects)
			gameObject.dispose();
	}

}
