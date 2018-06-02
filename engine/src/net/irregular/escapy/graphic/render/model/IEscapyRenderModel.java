package net.irregular.escapy.graphic.render.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.graphic.camera.IEscapyCamera;

@FunctionalInterface public interface IEscapyRenderModel {
	void renderModel(IEscapyCamera camera, Batch batch, float delta);
}
