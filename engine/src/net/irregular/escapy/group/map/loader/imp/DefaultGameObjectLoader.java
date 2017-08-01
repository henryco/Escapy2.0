package net.irregular.escapy.group.map.loader.imp;

import net.irregular.escapy.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.group.map.core.object.EscapyGameObjectRenderer;
import net.irregular.escapy.group.map.core.object.ObjectDetails;
import net.irregular.escapy.group.map.core.object.imp.GameObjectStatic;
import net.irregular.escapy.group.map.core.object.renderer.GameObjectStaticRenderer;
import net.irregular.escapy.group.map.core.object.texture.GameObjectStaticTexturePath;
import net.irregular.escapy.group.map.loader.GameObjectLoader;
import net.irregular.escapy.group.map.loader.serial.SerializedGameObject;
import net.irregular.escapy.utils.loader.EscapyInstanceLoader;

/**
 * @author Henry on 14/07/17.
 */
public class DefaultGameObjectLoader implements GameObjectLoader<SerializedGameObject> {


	private final EscapyInstanceLoader<EscapyGameObject> gameObjectInstanceAttributeLoader;

	public DefaultGameObjectLoader(EscapyInstanceLoader<EscapyGameObject> gameObjectInstanceAttributeLoader) {
		this.gameObjectInstanceAttributeLoader = gameObjectInstanceAttributeLoader;
	}


	@Override
	public EscapyGameObject loadGameObject(String path, SerializedGameObject serialized) {

		EscapyGameObject gameObject = proxyLoadedGameObject(path, serialized);
		if (gameObjectInstanceAttributeLoader != null)
			gameObject = gameObjectInstanceAttributeLoader.loadInstanceAttributes(gameObject, serialized.attributes);
		return gameObject;
	}


	private EscapyGameObject proxyLoadedGameObject(String path, SerializedGameObject serialized) {

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


	private EscapyGameObject loadStaticObject(String path, ObjectDetails details,
											  SerializedGameObject.SerializedStatic serializedStatic,
											  EscapyGameObjectRenderer<GameObjectStatic> staticRenderer) {

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
