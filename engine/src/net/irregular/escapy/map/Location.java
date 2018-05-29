package net.irregular.escapy.map;

import lombok.val;
import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.map.i.ILocationLayer;
import net.irregular.escapy.map.i.ILocationRenderer;

public class Location {

	private ILocationRenderer renderer;
	private ILocationLayer[] layers;

	private EscapyCamera camera;

	public void render(float delta) {
		for (val layer : layers) {
			renderer.render(null, delta);
		}
	}

}