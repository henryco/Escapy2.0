package net.tindersamurai.escapy.components.model.plain;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LayerModel implements IEscapyModel {

	private final List<IEscapyModel> nested;
	private final EscapyFBO diffuseBuffer;
	private final EscapyFBO normalsBuffer;


	public LayerModel (
			EscapyFBO diffuseBuffer,
			EscapyFBO normalsBuffer,
			IEscapyModel... nested
	) {
		this.normalsBuffer = normalsBuffer;
		this.diffuseBuffer = diffuseBuffer;
		this.nested = new ArrayList<>();
		Collections.addAll(this.nested, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		clear();
	}

	@Override
	public Collection<IEscapyModel> getNestedModels() {
		return nested;
	}

	@Override
	public void renderDiffuseModel(IEscapyCamera camera, Batch batch, float delta) {
		diffuseBuffer.begin(() -> {
			diffuseBuffer.clear();
			renderDiffuseMap(camera, batch, delta);
			for (IEscapyModel model : getNestedModels())
				model.renderDiffuseMap(camera, batch, delta);
		});
	}

	@Override
	public void renderNormalModel(IEscapyCamera camera, Batch batch, float delta) {
		normalsBuffer.begin(() -> {
			normalsBuffer.clear();
			renderNormalModel(camera, batch, delta);
			for (IEscapyModel model : getNestedModels())
				model.renderDiffuseMap(camera, batch, delta);
		});
	}

}