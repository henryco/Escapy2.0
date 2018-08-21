package net.tindersamurai.escapy.components.model.plain.light;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.light.source.LightSource;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteBinder;

import java.util.function.Consumer;

@Log
public class LightSourceModel implements IEscapyModel, IEscapySpriteBinder {

	private final @Getter LightSource lightSource;

	public LightSourceModel(LightSource lightSource) {
		this.lightSource = lightSource;
		if (lightSource == null)
			throw new RuntimeException("LightSource cannot be NULL!");
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		lightSource.drawBuffer(batch);
	}

	@Override
	public void preRender(IEscapyMemoCam camera, Batch batch, float delta) {
		lightSource.prepareBuffer();
	}

	@Override
	public void apply(Consumer<Sprite> s) {
		s.accept(mockProvidableSprite);
	}

	@Override
	public Sprite getEffectiveSprite() {
		return mockProvidableSprite;
	}

	private final Sprite mockProvidableSprite = new Sprite() {

		@Override
		public float getWidth() {
			return 0;
		}

		@Override
		public float getHeight() {
			return 0;
		}

		@Override
		public void setPosition(float x, float y) {
			if (lightSource != null)
				lightSource.setPosition(x, y);
		}

		/** @param degrees from 0 to 1 */
		@Override
		public void setRotation(float degrees) {
			if (lightSource != null) {
				lightSource.rotate(degrees);
			}
		}
	};
}