package net.irregular.escapy.map.object;


import net.irregular.escapy.map.object.texture.StaticTexturePath;

/**
 * @author Henry on 11/07/17.
 */
public class ObjectStatic implements GameObject {


	private final ObjectDetails objectDetails;
	private final GameObjectRenderer objectRenderer;
	private StaticTexturePath texturePath;



	public ObjectStatic(GameObjectRenderer<ObjectStatic> objectRenderer) {
		this(objectRenderer, new ObjectDetails());
	}

	public ObjectStatic(GameObjectRenderer<ObjectStatic> objectRenderer,
						ObjectDetails objectDetails) {
		this.objectDetails = objectDetails;
		this.objectRenderer = objectRenderer;
		objectRenderer.bindGameObject(this);
	}

	public ObjectStatic(GameObjectRenderer<ObjectStatic> objectRenderer,
						ObjectDetails objectDetails,
						StaticTexturePath texturePath) {
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

	@Override
	public ObjectDetails getObjectDetails() {
		return objectDetails;
	}

	@Override
	public GameObjectRenderer getGameObjectRenderer() {
		return objectRenderer;
	}


	public StaticTexturePath getTexturePath() {
		return texturePath;
	}

	public void setTexturePath(StaticTexturePath texturePath) {
		this.texturePath = texturePath;
	}
}
