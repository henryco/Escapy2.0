package net.tindersamurai.escapy.components.model.plain.light;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightTypeModel implements IEscapyModel {

	private final @Getter UpWrapper<EscapyFBO> lightTypeFbo;
	private final @Getter List<IEscapyModel> nestedModels;

	public LightTypeModel (
			UpWrapper<EscapyFBO> lightTypeFbo,
			IEscapyModel ... nested
	) {
		if (lightTypeFbo == null)
			throw new RuntimeException("LightType FBO == NULL");
		this.lightTypeFbo = lightTypeFbo;
		this.nestedModels = new ArrayList<>();
		Collections.addAll(this.nestedModels, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		wipe();
	}

	@Override
	public void renderDiffuseModel(IEscapyMemoCam camera, Batch batch, float delta) {
		lightTypeFbo.get().begin(batch, () -> {
			batch.setProjectionMatrix(camera.update().getProjection());
			renderDiffuseMap(camera, batch, delta);
			for (IEscapyModel model : getNestedModels()) {
				model.renderDiffuseModel(camera, batch, delta);
			}
		});
		lightTypeFbo.setUpdated(true);
	}

	@Override
	public void preRender(IEscapyMemoCam camera, Batch batch, float delta) {
		lightTypeFbo.setUpdated(false);
	}

	@Override
	public void postRender(IEscapyMemoCam camera, Batch batch, float delta) {

	}
}