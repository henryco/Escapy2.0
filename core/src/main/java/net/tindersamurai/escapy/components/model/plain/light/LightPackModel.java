package net.tindersamurai.escapy.components.model.plain.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.light.processor.EscapyLightProcessor;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.EscapyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log public class LightPackModel implements IEscapyModel {

	private final @Getter EscapyLightProcessor lightProcessor;

	private final @Getter UpWrapper<EscapyFBO> lightColorFbo;
	private final @Getter UpWrapper<EscapyFBO> normalsFbo;
	private final @Getter UpWrapper<EscapyFBO> maskFbo;

	private final @Getter List<IEscapyModel> nestedModels;

	private final Batch postRenderBatch;

	public LightPackModel (
			EscapyLightProcessor lightProcessor,
			UpWrapper<EscapyFBO> lightColorFbo,
			UpWrapper<EscapyFBO> normalsFbo,
			UpWrapper<EscapyFBO> maskFbo,
			IEscapyModel... nested
	) {
		this.postRenderBatch = new SpriteBatch();
		this.lightProcessor = lightProcessor;
		this.lightColorFbo = lightColorFbo;
		this.normalsFbo = normalsFbo;
		this.maskFbo = maskFbo;
		this.nestedModels = new ArrayList<>();
		Collections.addAll(this.nestedModels, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		wipe();
	}

	@Override
	public void postRender(IEscapyMemoCam camera, Batch batch, float delta) {

		if (maskFbo == null) return;

		postRenderBatch.setProjectionMatrix(camera.update().getProjection());
		EscapyUtils.centerize (
				maskFbo.get().getSprite(),
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()
		);
		maskFbo.get().draw(postRenderBatch);

		if (lightColorFbo == null) return;

		EscapyUtils.centerize (
				lightColorFbo.get().getSprite(),
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()
		);

		Sprite normalSprite = null;
		if (normalsFbo != null) {
			EscapyUtils.centerize(
					normalsFbo.get().getSprite(),
					Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight()
			);
			normalSprite = normalsFbo.get().getSprite();
		}

		if (lightProcessor == null) {
			lightColorFbo.get().draw(postRenderBatch);
			return;
		}

		lightProcessor.draw (
				postRenderBatch,
				lightColorFbo.get().getSprite(),
				normalSprite,
				maskFbo.get().getSprite()
		);
	}
}