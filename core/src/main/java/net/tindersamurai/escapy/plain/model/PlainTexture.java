package net.tindersamurai.escapy.plain.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModel;

public class PlainTexture implements IEscapyModel {

	private Sprite[] sprite; // diff, normal, light

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {

	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {

	}

	@Override
	public void renderLightMap(IEscapyCamera camera, Batch batch, float delta) {

	}
}