package net.tindersamurai.escapy.graphic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Wipeable;

public interface IEscapyRenderable extends Wipeable {

	default void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) { }

	default void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) { }

	void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta);


	static void draw (Sprite sprite, IEscapyCamera camera, Batch batch) {
		if (sprite == null) return;
		batch.setProjectionMatrix(camera.getProjection());
		sprite.draw(batch);
	}
}