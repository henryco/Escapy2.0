package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.IEscapyModelRenderer;

import javax.inject.Inject;
import javax.inject.Named;

@Provide @Log
public class ModelRenderer implements IEscapyModelRenderer {

	{ log.info("ModelRenderer instance [" + this.hashCode() + "]"); }

	private final IEscapyMemoCam camera;
	private final Batch batch;

	@Inject
	public ModelRenderer(
			@Named("main-camera") IEscapyMemoCam camera,
			Batch batch
	) {
		this.batch = batch;
		this.camera = camera;
	}

	@Override // TODO
	public void render(IEscapyModel model, float delta) {
//		wipe();
//		model.renderLightModel(camera, batch, delta);
//		wipe();
//		model.renderNormalModel(camera, batch, delta);
		wipe();
		model.renderDiffuseModel(camera, batch, delta);
	}
}
