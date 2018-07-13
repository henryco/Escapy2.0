package net.tindersamurai.escapy.components.model.plain;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RootModel implements IEscapyModel {

	private final List<IEscapyModel> nested;
	private final IEscapyCamera vpCamera;

	public RootModel (
			IEscapyCamera vpCamera,
			IEscapyModel ... nested
	) {
		this.vpCamera = vpCamera;
		this.nested = new ArrayList<>();
		Collections.addAll(this.nested, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		camera.update();
		clear();
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		camera.update();
		clear();
	}

	@Override
	public void renderLightMap(IEscapyCamera camera, Batch batch, float delta) {
		camera.update();
		clear();
	}

	@Override
	public Collection<IEscapyModel> getNestedModels() {
		return nested;
	}


}