package net.irregular.escapy.map.object;


import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author Henry on 11/07/17.
 */
public class ObjectStatic implements GameObject {


	private final ObjectDetails objectDetails;


	public ObjectStatic() {
		this(new ObjectDetails());
	}
	public ObjectStatic(ObjectDetails objectDetails) {
		this.objectDetails = objectDetails;
	}


	private String textureNormal;
	private String textureLight;
	private String texture;


	@Override
	public void renderLightMap(Batch batch) {

	}

	@Override
	public void renderGraphics(Batch batch) {

	}

	@Override
	public void renderNormalsMap(Batch batch) {

	}

	@Override
	public String toString() {
		return "ObjectStatic{" +
				"objectDetails=" + objectDetails +
				", textureNormal='" + textureNormal + '\'' +
				", textureLight='" + textureLight + '\'' +
				", texture='" + texture + '\'' +
				'}';
	}


	@Override
	public ObjectDetails getObjectDetails() {
		return objectDetails;
	}


	public void setTextureNormal(String textureNormal) {
		this.textureNormal = textureNormal;
	}

	public void setTextureLight(String textureLight) {
		this.textureLight = textureLight;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}
}
