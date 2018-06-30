package net.tindersamurai.escapy.group.map.core.object.texture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Henry on 12/07/17.
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class GameObjectStaticTexturePath {
	private String textureNormal;
	private String textureLight;
	private String texture;
}
