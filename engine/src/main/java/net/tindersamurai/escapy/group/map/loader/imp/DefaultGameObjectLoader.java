package net.tindersamurai.escapy.group.map.loader.imp;

import net.tindersamurai.escapy.group.map.core.object.EscapyGameObject;
import net.tindersamurai.escapy.group.map.core.object.EscapyGameObjectRenderer;
import net.tindersamurai.escapy.group.map.core.object.ObjectDetails;
import net.tindersamurai.escapy.group.map.core.object.imp.GameObjectStatic;
import net.tindersamurai.escapy.group.map.core.object.renderer.GameObjectStaticRenderer;
import net.tindersamurai.escapy.group.map.core.object.texture.GameObjectStaticTexturePath;
import net.tindersamurai.escapy.group.map.loader.GameObjectLoader;
import net.tindersamurai.escapy.group.map.loader.serial.SerializedGameObject;
import net.tindersamurai.escapy.utils.loader.EscapyInstanceLoader;

/**
 * @author Henry on 14/07/17.
 */
public class DefaultGameObjectLoader implements GameObjectLoader<SerializedGameObject> {


	private final EscapyInstanceLoader<EscapyGameObject> gameObjectAttributeLoader;

	public DefaultGameObjectLoader(EscapyInstanceLoader<EscapyGameObject> gameObjectAttributeLoader) {
		this.gameObjectAttributeLoader = gameObjectAttributeLoader;
	}


	@Override
	public EscapyGameObject loadGameObject(String path, SerializedGameObject serialized) {

		EscapyGameObject gameObject = proxyLoadedGameObject(path, serialized);
		if (gameObjectAttributeLoader != null)
			gameObject = gameObjectAttributeLoader.loadInstanceAttributes(gameObject, serialized.attributes);
		return gameObject;
	}


	private EscapyGameObject proxyLoadedGameObject(String path, SerializedGameObject serialized) {

		String name1 = serialized.name;
		String name2 = serialized.details.name;

		ObjectDetails details = new ObjectDetails(name2.isEmpty() ? name1 : name2);
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
