package net.irregular.escapy.engine.map.zloader.imp;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.map.object.GameObject;
import net.irregular.escapy.engine.map.object.GameObjectRenderer;
import net.irregular.escapy.engine.map.object.ObjectDetails;
import net.irregular.escapy.engine.map.object.imp.GameObjectStatic;
import net.irregular.escapy.engine.map.object.renderer.GameObjectStaticRenderer;
import net.irregular.escapy.engine.map.object.texture.GameObjectStaticTexturePath;
import net.irregular.escapy.engine.map.zloader.GameObjectLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject;

import static net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject.SerializedStatic;

/**
 * @author Henry on 14/07/17.
 */
public class DefaultGameObjectLoader implements GameObjectLoader<SerializedGameObject> {


	private final EscapyInstanceLoader<GameObject> gameObjectInstanceAttributeLoader;

	public DefaultGameObjectLoader(EscapyInstanceLoader<GameObject> gameObjectInstanceAttributeLoader) {
		this.gameObjectInstanceAttributeLoader = gameObjectInstanceAttributeLoader;
	}


	@Override
	public GameObject loadGameObject(SerializedGameObject serialized) {

		GameObject gameObject = proxyLoadedGameObject(serialized);
		if (gameObjectInstanceAttributeLoader != null)
			gameObject = gameObjectInstanceAttributeLoader.loadInstanceAttributes(gameObject, serialized.attributes);
		return gameObject;
	}


	private GameObject proxyLoadedGameObject(SerializedGameObject serialized) {

		ObjectDetails details = new ObjectDetails(serialized.details.name);
		details.setScale(serialized.details.scale);
		details.setPosition(floatListToArray(serialized.details.position));

		if (serialized.staticObject != null)
			return loadStaticObject(details, serialized.staticObject, new GameObjectStaticRenderer());

		// TODO MORE OBJECTS
		return null;
	}


	private GameObject loadStaticObject(ObjectDetails details,
										SerializedStatic serializedStatic,
										GameObjectRenderer<GameObjectStatic> staticRenderer) {

		GameObjectStaticTexturePath texturePath = new GameObjectStaticTexturePath();
		texturePath.setTexture(serializedStatic.texture);
		texturePath.setTextureLight(serializedStatic.textureLight);
		texturePath.setTextureNormal(serializedStatic.textureNormal);
		return new GameObjectStatic(staticRenderer, details, texturePath);
	}




}
