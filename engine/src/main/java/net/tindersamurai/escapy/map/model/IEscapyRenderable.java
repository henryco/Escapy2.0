package net.tindersamurai.escapy.map.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Wipeable;

public interface IEscapyRenderable extends Wipeable {

	default void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		color(0.502f, 0.502f, 1f, 1f);
	}

	default void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		wipe();
	}

	void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta);

}