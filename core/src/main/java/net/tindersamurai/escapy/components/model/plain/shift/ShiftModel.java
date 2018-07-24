package net.tindersamurai.escapy.components.model.plain.shift;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log
public class ShiftModel implements IEscapyModel {

	private final @Getter
	List<IEscapyModel> nestedModels;

	private final @Getter
	IShiftLogic shiftLogic;

	public ShiftModel (
			IShiftLogic shiftLogic,
			IEscapyModel ... nested
	) {
		this.shiftLogic = shiftLogic;
		this.nestedModels = new ArrayList<>();
		Collections.addAll(this.nestedModels, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		// void
	}

	@Override
	public void renderDiffuseModel(IEscapyMemoCam camera, Batch batch, float delta) {
		camera.safety(() -> {
			camera.setCameraPosition(shiftLogic.calculatePosition(camera.getPosition()));
			batch.setProjectionMatrix(camera.update().getProjection());
			for (IEscapyModel model : getNestedModels())
				model.renderDiffuseModel(camera, batch, delta);
		});
	}

	@Override
	public void renderNormalModel(IEscapyMemoCam camera, Batch batch, float delta) {
		camera.safety(() -> {
			camera.setCameraPosition(shiftLogic.calculatePosition(camera.getPosition()));
			batch.setProjectionMatrix(camera.update().getProjection());
			for (IEscapyModel model : getNestedModels())
				model.renderNormalModel(camera, batch, delta);
		});
	}

	@Override
	public void renderShadowModel(IEscapyMemoCam camera, Batch batch, float delta) {
		camera.safety(() -> {
			camera.setCameraPosition(shiftLogic.calculatePosition(camera.getPosition()));
			batch.setProjectionMatrix(camera.update().getProjection());
			for (IEscapyModel model : getNestedModels())
				model.renderShadowModel(camera, batch, delta);
		});
	}
}