package net.irregular.escapy.graphic.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.graphic.camera.IEscapyCamera;

public interface IEscapyRenderable {

	default void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {}

	default void renderLightMap(IEscapyCamera camera, Batch batch, float delta) {}

	void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta);

}