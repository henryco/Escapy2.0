package net.irregular.escapy.map.objects;


/**
 * @author Henry on 11/07/17.
 */
public class ObjectStatic implements GameObject {


	public final ObjectDetails objectDetails;


	public ObjectStatic() {
		this(new ObjectDetails());
	}
	public ObjectStatic(ObjectDetails objectDetails) {
		this.objectDetails = objectDetails;
	}


	private String textureNormal;
	private String textureLight;
	private String textureHeight;
	private String texture;




	@Override
	public String toString() {
		return "ObjectStatic{" +
				"objectDetails=" + objectDetails +
				", textureNormal='" + textureNormal + '\'' +
				", textureLight='" + textureLight + '\'' +
				", textureHeight='" + textureHeight + '\'' +
				", texture='" + texture + '\'' +
				'}';
	}



	public String getTextureNormal() {
		return textureNormal;
	}

	public void setTextureNormal(String textureNormal) {
		this.textureNormal = textureNormal;
	}

	public String getTextureLight() {
		return textureLight;
	}

	public void setTextureLight(String textureLight) {
		this.textureLight = textureLight;
	}

	public String getTextureHeight() {
		return textureHeight;
	}

	public void setTextureHeight(String textureHeight) {
		this.textureHeight = textureHeight;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}
}
