package net.tindersamurai.escapy.map.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.screen.Wipeable;

import java.util.Collection;
import java.util.Collections;

public interface IEscapyModel extends IEscapyRenderable, Wipeable, Disposable {

	@FunctionalInterface interface Render {
		void render(IEscapyMemoCam camera, Batch batch, float delta);
	}

	default void preRender(IEscapyMemoCam camera, Batch batch, float delta) {}

	default void postRender(IEscapyMemoCam camera, Batch batch, float delta) {}

	default Render[] preRenderQueue() {
		return new Render[] {
				this::preRender
		};
	}

	default Render[] postRenderQueue() {
		return new Render[] {
				this::postRender
		};
	}

	default Collection<IEscapyModel> getNestedModels() {
		//noinspection unchecked
		return Collections.EMPTY_LIST;
	}

	default void renderDiffuseModel(IEscapyMemoCam camera, Batch batch, float delta) {
		renderDiffuseMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			if (model != null)
				model.renderDiffuseModel(camera, batch, delta);
	}

	default void renderNormalModel(IEscapyMemoCam camera, Batch batch, float delta) {
		renderNormalMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			if (model != null)
				model.renderNormalModel(camera, batch, delta);
	}

	default void renderShadowModel(IEscapyMemoCam camera, Batch batch, float delta) {
		renderShadowMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			if (model != null)
				model.renderShadowModel(camera, batch, delta);
	}


	default void dispose() {
	}
}