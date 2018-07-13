package net.tindersamurai.escapy.map.model.texture;

import lombok.Data;

@Data
public class EscapyTextureData implements IEscapyTexture {

	public final String diffuse;
	public final String normals;
	public final String shadows;

	private boolean flipX;
	private boolean flipY;

	private float scaleX = 1f;
	private float scaleY = 1f;

	private float width;
	private float height;

	private float x;
	private float y;

	@SuppressWarnings("WeakerAccess") public EscapyTextureData (
			String diffuse, String normals, String shadows) {
		this.diffuse = diffuse;
		this.normals = normals;
		this.shadows = shadows;
	}

	public EscapyTextureData(String prefix, String diffuse, String normals, String shadows) {
		this(
				diffuse == null ? null : prefix + diffuse,
				normals == null ? null : prefix + normals,
				shadows == null ? null : prefix + shadows
		);
	}

}