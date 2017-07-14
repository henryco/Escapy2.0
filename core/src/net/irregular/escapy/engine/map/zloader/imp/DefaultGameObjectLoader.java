package net.irregular.escapy.engine.map.zloader.imp;

import net.irregular.escapy.engine.map.object.GameObject;
import net.irregular.escapy.engine.map.object.GameObjectRenderer;
import net.irregular.escapy.engine.map.object.ObjectDetails;
import net.irregular.escapy.engine.map.object.ObjectStatic;
import net.irregular.escapy.engine.map.object.texture.StaticTexturePath;
import net.irregular.escapy.engine.map.zloader.GameObjectLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject;

import static net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject.SerializedStatic;

/**
 * @author Henry on 14/07/17.
 */
public class DefaultGameObjectLoader implements GameObjectLoader<SerializedGameObject> {



	@Override
	public GameObject loadGameObject(SerializedGameObject serialized) {

		ObjectDetails details = new ObjectDetails(serialized.details.name);
		details.setScale(serialized.details.scale);
		details.setThickness(serialized.details.thickness);
		details.setPosition(floatListToArray(serialized.details.position));


		if (serialized.staticObject != null)
			return loadStaticObject(details, serialized.staticObject, null);

		// TODO MORE OBJECTS
		return null;
	}



	private GameObject loadStaticObject(ObjectDetails details,
										SerializedStatic serializedStatic,
										GameObjectRenderer<ObjectStatic> staticRenderer) {

		StaticTexturePath texturePath = new StaticTexturePath();
		texturePath.setTexture(serializedStatic.texture);
		texturePath.setTextureLight(serializedStatic.textureLight);
		texturePath.setTextureNormal(serializedStatic.textureNormal);

		return new ObjectStatic(staticRenderer, details, texturePath);
	}




}
