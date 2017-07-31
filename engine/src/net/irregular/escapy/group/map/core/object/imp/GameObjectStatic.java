package net.irregular.escapy.group.map.core.object.imp;


import net.irregular.escapy.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.group.map.core.object.EscapyGameObjectRenderer;
import net.irregular.escapy.group.map.core.object.ObjectDetails;
import net.irregular.escapy.group.map.core.object.texture.GameObjectStaticTexturePath;

/**
 * @author Henry on 11/07/17.
 */
public class GameObjectStatic implements EscapyGameObject {


	private final EscapyGameObjectRenderer<GameObjectStatic> objectRenderer;
	private final GameObjectStaticTexturePath texturePath;
	private final ObjectDetails objectDetails;


	public GameObjectStatic(EscapyGameObjectRenderer<GameObjectStatic> objectRenderer,
							ObjectDetails objectDetails,
							GameObjectStaticTexturePath texturePath) {

		this.objectDetails = objectDetails;
		this.objectRenderer = objectRenderer;
		this.texturePath = texturePath;

		objectRenderer.bindGameObject(this);
	}


	@Override
	public String toString() {
		return "ObjectStatic{" +
				"objectDetails=" + objectDetails +
				", objectRenderer=" + objectRenderer +
				", texturePath=" + texturePath +
				'}';
	}


	@Override public ObjectDetails getObjectDetails() {
		return objectDetails;
	}
	@Override public EscapyGameObjectRenderer getGameObjectRenderer() {
		return objectRenderer;
	}

	public GameObjectStaticTexturePath getTexturePath() {
		return texturePath;
	}

}