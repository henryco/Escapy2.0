package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
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
	public ModelRenderer (
			@Named("main-camera") IEscapyMemoCam camera,
			Batch batch
	) {
		this.camera = camera;
		this.batch = batch;
	}

	@Override
	public void render(IEscapyModel model, float delta) {

		preRender(model, delta);
		model.renderDiffuseModel(camera, batch, delta);
		model.renderNormalModel(camera, batch, delta);
		model.renderLightModel(camera, batch, delta);
		postRender(model, delta);
	}


	private void preRender(IEscapyModel model, float delta) {
		for (val preRenderer : model.preRenderQueue())
			preRenderer.render(camera, batch, delta);
		for (val nested : model.getNestedModels())
			preRender(nested, delta);
	}

	private void postRender(IEscapyModel model, float delta) {
		for (val postRenderer : model.postRenderQueue())
			postRenderer.render(camera, batch, delta);
		for (val nested : model.getNestedModels())
			postRender(nested, delta);
	}

}