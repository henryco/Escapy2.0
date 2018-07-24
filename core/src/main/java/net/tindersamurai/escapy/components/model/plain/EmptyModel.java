package net.tindersamurai.escapy.components.model.plain;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmptyModel implements IEscapyModel {

	private final List<IEscapyModel> nested;
	public EmptyModel (IEscapyModel ... nested) {
		this.nested = new ArrayList<>();
		Collections.addAll(this.nested, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		// nothing
	}

	@Override
	public Collection<IEscapyModel> getNestedModels() {
		return nested;
	}

}