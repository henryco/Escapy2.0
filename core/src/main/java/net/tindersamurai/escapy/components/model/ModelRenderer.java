package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.IEscapyModelRenderer;
import net.tindersamurai.escapy.map.model.IEscapyRenderable;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Provide @Log
public class ModelRenderer implements IEscapyModelRenderer {

	{ log.info("ModelRenderer instance [" + this.hashCode() + "]"); }

	private final List<IEscapyRenderable> extra;
	private final IEscapyMemoCam finalCamera;
	private final IEscapyMemoCam camera;
	private final Batch batch;

	@Inject
	public ModelRenderer (
			@Named("final-camera") IEscapyMemoCam finalCamera,
			@Named("main-camera") IEscapyMemoCam camera,
			Batch batch
	) {
		this.extra = new ArrayList<>();
		this.finalCamera = finalCamera;
		this.camera = camera;
		this.batch = batch;
	}

	@Override
	public void render(IEscapyModel model, float delta) {
		camera.safety(() -> preRender(model, delta));
		camera.safety(() -> model.renderDiffuseModel(camera, batch, delta));
		camera.safety(() -> model.renderNormalModel(camera, batch, delta));
		camera.safety(() -> model.renderShadowModel(camera, batch, delta));
		finalCamera.safety(() -> postRender(model, delta));

		// debug only
		renderExtra(delta);
	}


	private void preRender(IEscapyModel model, float delta) {
		if (model == null) return;
		for (val preRenderer : model.preRenderQueue())
			preRenderer.render(camera, batch, delta);
		for (val nested : model.getNestedModels())
			preRender(nested, delta);
	}

	private void postRender(IEscapyModel model, float delta) {
		if (model == null) return;
		for (val nested : model.getNestedModels()) postRender(nested, delta);
		for (val postRenderer : model.postRenderQueue()) postRenderer.render(finalCamera, batch, delta);
	}

	private void renderExtra(float delta) {
		for (val e : extra) {
			camera.safety(() -> e.renderDiffuseMap(camera, batch, delta));
			camera.safety(() -> e.renderNormalMap(camera, batch, delta));
			camera.safety(() -> e.renderShadowMap(camera, batch, delta));
		}
	}

	/**
	 * Use it for DEBUG ONLY!
	 * @param extra extra data
	 */ public final void addExtraRenderData(IEscapyRenderable extra) {
		this.extra.add(extra);
	}

}