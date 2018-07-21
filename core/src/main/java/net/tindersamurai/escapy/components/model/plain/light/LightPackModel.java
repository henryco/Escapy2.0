package net.tindersamurai.escapy.components.model.plain.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.EscapyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log public class LightPackModel implements IEscapyModel {

	private final @Getter UpWrapper<EscapyFBO> lightColorFbo;
	private final @Getter UpWrapper<EscapyFBO> normalsFbo;
	private final @Getter UpWrapper<EscapyFBO> maskFbo;

	private final @Getter List<IEscapyModel> nestedModels;

	public LightPackModel (
			UpWrapper<EscapyFBO> lightColorFbo,
			UpWrapper<EscapyFBO> normalsFbo,
			UpWrapper<EscapyFBO> maskFbo,
			IEscapyModel... nested
	) {
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
		EscapyUtils.centerize(
				maskFbo.get().getSprite(),
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()
		);

		batch.setProjectionMatrix(camera.update().getProjection());
		maskFbo.get().renderGraphics(batch);
	}
}