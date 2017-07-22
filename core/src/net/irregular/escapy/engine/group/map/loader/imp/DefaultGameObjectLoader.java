package net.irregular.escapy.engine.group.map.loader.imp;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.group.map.core.object.GameObject;
import net.irregular.escapy.engine.group.map.core.object.GameObjectRenderer;
import net.irregular.escapy.engine.group.map.core.object.ObjectDetails;
import net.irregular.escapy.engine.group.map.core.object.imp.GameObjectStatic;
import net.irregular.escapy.engine.group.map.core.object.renderer.GameObjectStaticRenderer;
import net.irregular.escapy.engine.group.map.core.object.texture.GameObjectStaticTexturePath;
import net.irregular.escapy.engine.group.map.loader.GameObjectLoader;
import net.irregular.escapy.engine.group.map.loader.serial.SerializedGameObject;

import static net.irregular.escapy.engine.group.map.loader.serial.SerializedGameObject.SerializedStatic;

/**
 * @author Henry on 14/07/17.
 */
public class DefaultGameObjectLoader implements GameObjectLoader<SerializedGameObject> {


	private final EscapyInstanceLoader<GameObject> gameObjectInstanceAttributeLoader;

	public DefaultGameObjectLoader(EscapyInstanceLoader<GameObject> gameObjectInstanceAttributeLoader) {
		this.gameObjectInstanceAttributeLoader = gameObjectInstanceAttributeLoader;
	}


	@Override
	public GameObject loadGameObject(String path, SerializedGameObject serialized) {

		GameObject gameObject = proxyLoadedGameObject(path, serialized);
		if (gameObjectInstanceAttributeLoader != null)
			gameObject = gameObjectInstanceAttributeLoader.loadInstanceAttributes(gameObject, serialized.attributes);
		return gameObject;
	}


	private GameObject proxyLoadedGameObject(String path, SerializedGameObject serialized) {

		ObjectDetails details = new ObjectDetails(serialized.details.name);
		details.setScale(serialized.details.scale);
		details.setPosition(floatListToArray(serialized.details.position));
		details.setSize(floatListToArray(serialized.details.size));
		details.setFlip(booleanListToArray(serialized.details.flip));

		if (serialized.staticObject != null)
			return loadStaticObject(path, details, serialized.staticObject, new GameObjectStaticRenderer());

		// TODO MORE OBJECTS
		return null;
	}


	private GameObject loadStaticObject(String path, ObjectDetails details,
										SerializedStatic serializedStatic,
										GameObjectRenderer<GameObjectStatic> staticRenderer) {

		GameObjectStaticTexturePath texturePath = new GameObjectStaticTexturePath();

		if (serializedStatic.texture != null)
			texturePath.setTexture(path +serializedStatic.texture);
		if (serializedStatic.textureLight != null)
			texturePath.setTextureLight(path + serializedStatic.textureLight);
		if (serializedStatic.textureNormal != null)
			texturePath.setTextureNormal(path + serializedStatic.textureNormal);

		return new GameObjectStatic(staticRenderer, details, texturePath);
	}




}
