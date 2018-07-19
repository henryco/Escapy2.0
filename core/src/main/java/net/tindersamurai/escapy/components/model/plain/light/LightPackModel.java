package net.tindersamurai.escapy.components.model.plain.light;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log
public class LightPackModel implements IEscapyModel {


	private final @Getter List<IEscapyModel> nestedModels;

	public LightPackModel (
			IEscapyModel... nested
	) {
		this.nestedModels = new ArrayList<>();
		Collections.addAll(this.nestedModels, nested);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		wipe();
	}
}