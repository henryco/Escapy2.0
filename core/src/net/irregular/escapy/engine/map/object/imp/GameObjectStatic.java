package net.irregular.escapy.engine.map.object.imp;


import net.irregular.escapy.engine.map.object.GameObject;
import net.irregular.escapy.engine.map.object.GameObjectRenderer;
import net.irregular.escapy.engine.map.object.ObjectDetails;
import net.irregular.escapy.engine.map.object.texture.GameObjectStaticTexturePath;

/**
 * @author Henry on 11/07/17.
 */
public class GameObjectStatic implements GameObject {


	private final ObjectDetails objectDetails;
	private final GameObjectRenderer objectRenderer;
	private GameObjectStaticTexturePath texturePath;



	public GameObjectStatic(GameObjectRenderer<GameObjectStatic> objectRenderer) {
		this(objectRenderer, new ObjectDetails());
	}

	public GameObjectStatic(GameObjectRenderer<GameObjectStatic> objectRenderer,
							ObjectDetails objectDetails) {
		this.objectDetails = objectDetails;
		this.objectRenderer = objectRenderer;
		objectRenderer.bindGameObject(this);
	}

	public GameObjectStatic(GameObjectRenderer<GameObjectStatic> objectRenderer,
							ObjectDetails objectDetails,
							GameObjectStaticTexturePath texturePath) {
		this(objectRenderer, objectDetails);
		setTexturePath(texturePath);
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
	@Override public GameObjectRenderer getGameObjectRenderer() {
		return objectRenderer;
	}

	public GameObjectStaticTexturePath getTexturePath() {
		return texturePath;
	}
	public void setTexturePath(GameObjectStaticTexturePath texturePath) {
		this.texturePath = texturePath;
	}
}
