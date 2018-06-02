package net.irregular.escapy.graphic.render.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.graphic.camera.IEscapyCamera;

import java.util.Collection;
import java.util.Collections;

public interface IEscapyModel extends IEscapyRenderable {

	default void preRender(IEscapyCamera camera, Batch batch, float delta) {}

	default void postRender(IEscapyCamera camera, Batch batch, float delta) {}

	default IEscapyRenderModel[] preRenderQueue() {
		return new IEscapyRenderModel[] {
				this::preRender
		};
	}

	default IEscapyRenderModel[] postRenderQueue() {
		return new IEscapyRenderModel[] {
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