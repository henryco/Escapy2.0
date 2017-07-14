package net.irregular.escapy.engine.map.zloader.imp;

import net.irregular.escapy.engine.map.object.GameObject;
import net.irregular.escapy.engine.map.object.ObjectDetails;
import net.irregular.escapy.engine.map.zloader.GameObjectLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject;

import static net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject.SerializedDetails;

/**
 * @author Henry on 14/07/17.
 */
public class DefaultGameObjectLoader implements GameObjectLoader<SerializedGameObject> {



	@Override
	public GameObject loadGameObject(SerializedGameObject serialized) {

		ObjectDetails details = loadObjectDetails(serialized.details);

		return null;
	}


	private ObjectDetails loadObjectDetails(SerializedDetails serialized) {

		ObjectDetails details = new ObjectDetails(serialized.name);
		details.setScale(serialized.scale);
		details.setThickness(serialized.thickness);
		details.setPosition(floatListToArray(serialized.position));
		return details;
	}
}
