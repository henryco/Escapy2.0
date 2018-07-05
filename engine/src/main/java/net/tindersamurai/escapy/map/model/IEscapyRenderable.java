package net.tindersamurai.escapy.map.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Wipeable;

public interface IEscapyRenderable extends Wipeable {

	default void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {}

	default void renderLightMap(IEscapyCamera camera, Batch batch, float delta) {}

	void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta);

}