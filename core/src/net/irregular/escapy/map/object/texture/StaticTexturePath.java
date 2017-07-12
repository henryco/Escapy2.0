package net.irregular.escapy.map.object.texture;

/**
 * @author Henry on 12/07/17.
 */
public class StaticTexturePath {

	private String textureNormal;
	private String textureLight;
	private String texture;


	public StaticTexturePath() {}

	public StaticTexturePath(String textureNormal, String textureLight, String texture) {
		this.textureNormal = textureNormal;
		this.textureLight = textureLight;
		this.texture = texture;
	}

	@Override
	public String toString() {
		return "StaticTexturePath{" +
				"textureNormal='" + textureNormal + '\'' +
				", textureLight='" + textureLight + '\'' +
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

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}
}
