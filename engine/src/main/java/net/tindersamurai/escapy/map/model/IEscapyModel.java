package net.tindersamurai.escapy.map.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Wipeable;

import java.util.Collection;
import java.util.Collections;

public interface IEscapyModel extends IEscapyRenderable, Wipeable {

	@FunctionalInterface interface Render {
		void render(IEscapyCamera camera, Batch batch, float delta);
	}

	default void preRender(IEscapyCamera camera, Batch batch, float delta) {}

	default void postRender(IEscapyCamera camera, Batch batch, float delta) {}

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

	default void renderDiffuseModel(IEscapyCamera camera, Batch batch, float delta) {
		renderDiffuseMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			model.renderDiffuseMap(camera, batch, delta);
	}

	default void renderNormalModel(IEscapyCamera camera, Batch batch, float delta) {
		renderNormalMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			model.renderNormalMap(camera, batch, delta);
	}

	default void renderLightModel(IEscapyCamera camera, Batch batch, float delta) {
		renderLightMap(camera, batch, delta);
		for (IEscapyModel model : getNestedModels())
			model.renderLightMap(camera, batch, delta);
	}
	
}