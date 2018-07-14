package net.tindersamurai.escapy.components.model.plain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.EscapyUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Log
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

		log.info("***********\n\nDIFFUSE BUFFER DIM: " + diffuseBuffer.getWidth() + " : " + diffuseBuffer.getHeight());
		log.info("BUFFER ID: " + diffuseBuffer.toString() + " : " + normalsBuffer.toString());
		log.info("NORMALS BUFFER DIM: " + normalsBuffer.getWidth() + " : " + normalsBuffer.getHeight() + "\n\n***********");
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		wipe();
	}

	@Override
	public Collection<IEscapyModel> getNestedModels() {
		return nested;
	}

	@Override
	public void renderDiffuseModel(IEscapyMemoCam camera, Batch batch, float delta) {

		diffuseBuffer.begin(batch, () -> {
			batch.setProjectionMatrix(camera.update().getProjection());
			renderDiffuseMap(camera, batch, delta);
			for (IEscapyModel model : getNestedModels())
				model.renderDiffuseModel(camera, batch, delta);
		});
	}

	@Override
	public void renderNormalModel(IEscapyMemoCam camera, Batch batch, float delta) {

		normalsBuffer.begin(batch, () -> {
			batch.setProjectionMatrix(camera.update().getProjection());
			renderNormalMap(camera, batch, delta);
			for (IEscapyModel model : getNestedModels())
				model.renderNormalModel(camera, batch, delta);
		});
	}

	@Override
	public void postRender(IEscapyMemoCam camera, Batch batch, float delta) {

		EscapyUtils.centerize(diffuseBuffer.getSprite(),
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()
		);

		batch.setProjectionMatrix(camera.update().getProjection());
		diffuseBuffer.renderGraphics(batch);
	}
}