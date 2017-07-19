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
