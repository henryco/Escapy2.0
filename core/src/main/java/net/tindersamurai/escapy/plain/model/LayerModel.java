package net.tindersamurai.escapy.plain.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LayerModel implements IEscapyModel {

	private final List<IEscapyModel> nested;
	public LayerModel(IEscapyModel ... nested) {
		this.nested = new ArrayList<>();
		Collections.addAll(this.nested, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		// nothing
	}

	@Override
	public Collection<? extends IEscapyModel> getNestedModels() {
		return nested;
	}
}