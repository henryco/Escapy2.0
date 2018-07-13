package net.tindersamurai.escapy.map.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.screen.Wipeable;

import java.util.Collection;
import java.util.Collections;

public interface IEscapyModel extends IEscapyRenderable, Wipeable {

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
			model.renderDiffuseModel(camera, batch, delta);
	}

	default void renderNormalModel(IEscapyMemoCam camera, Batch batch, float delta) {
		renderNormalMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			model.renderNormalModel(camera, batch, delta);
	}

	default void renderLightModel(IEscapyMemoCam camera, Batch batch, float delta) {
		renderLightMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			model.renderLightModel(camera, batch, delta);
	}
	
}