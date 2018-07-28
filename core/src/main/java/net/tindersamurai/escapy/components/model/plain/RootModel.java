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

	public RootModel (IEscapyModel ... nested) {
		this.nested = new ArrayList<>();
		Collections.addAll(this.nested, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		camera.update();
		wipe();
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		camera.update();
		wipe();
	}

	@Override
	public void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		camera.update();
		wipe();
	}

	@Override
	public Collection<IEscapyModel> getNestedModels() {
		return nested;
	}


}