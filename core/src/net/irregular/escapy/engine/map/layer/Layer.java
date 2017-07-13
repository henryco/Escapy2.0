package net.irregular.escapy.engine.map.layer;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.map.layer.shift.LayerShifter;
import net.irregular.escapy.engine.map.object.GameObject;

import java.util.Collection;
import java.util.LinkedList;


/**
 * @author Henry on 11/07/17.
 */
public class Layer implements EscapyObject {

	private float axisZ;
	private LayerShifter layerShifter;

	public final String name;
	public final EscapyAssociatedArray<GameObject> gameObjects;


	public Layer(String name) {
		this.name = name;
		gameObjects = new EscapyNamedArray<>(GameObject.class);
		axisZ = 0;
	}

	public Layer(String name,
				 Collection<GameObject> objects,
				 LayerShifter layerShifter) {
		this(name);
		setGameObjects(objects);
		setLayerShifter(layerShifter);
	}



	public void setGameObjects(Collection<GameObject> objects) {
		Collection<String> objectNames = new LinkedList<>();
		for (GameObject object: objects) objectNames.add(object.getObjectDetails().getName());
		gameObjects.addAll(objectNames, objects);
	}




	public LayerShifter getLayerShifter() {
		return layerShifter;
	}
	public void setLayerShifter(LayerShifter layerShifter) {
		this.layerShifter = layerShifter;
	}
	public float getAxisZ() {
		return axisZ;
	}
	public void setAxisZ(float axisZ) {
		this.axisZ = axisZ;
	}

	@Override public String getName() {
		return name;
	}

	@Override
	public void dispose() {
		for (GameObject gameObject: gameObjects) gameObject.dispose();
	}
}
