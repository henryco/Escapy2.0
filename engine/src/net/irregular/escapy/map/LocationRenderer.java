package net.irregular.escapy.map;

import lombok.Getter;
import lombok.Setter;
import net.irregular.escapy.graphic.camera.IEscapyMemoCam;
import net.irregular.escapy.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.map.i.ILocationRenderer;

public class LocationRenderer implements ILocationRenderer {

	private @Getter @Setter
	IEscapyMemoCam memoCamera;

	@Override
	public void render(EscapyRenderable renderable, float delta) {

	}

}