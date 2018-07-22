package net.tindersamurai.escapy.components.model.plain.light;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.light.source.LightSource;
import net.tindersamurai.escapy.map.model.IEscapyModel;

@Log
public class LightSourceModel implements IEscapyModel {

	private final @Getter LightSource lightSource;

	public LightSourceModel(LightSource lightSource) {
		this.lightSource = lightSource;
		if (lightSource == null)
			throw new RuntimeException("LightSource cannot be NULL!");
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		lightSource.drawBuffer(batch);
	}

	@Override
	public void preRender(IEscapyMemoCam camera, Batch batch, float delta) {
		lightSource.prepareBuffer();
	}

//	@Override
//	public void postRender(IEscapyMemoCam camera, Batch batch, float delta) {
//		batch.begin();
//		lightSource.drawBuffer(batch);
//		batch.end();
//	}

}