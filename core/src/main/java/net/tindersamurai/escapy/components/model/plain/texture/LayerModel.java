package net.tindersamurai.escapy.components.model.plain.texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log
public class LayerModel implements IEscapyModel {

	private final @Getter UpWrapper<EscapyFBO> diffuseBuffer;
	private final @Getter UpWrapper<EscapyFBO> normalsBuffer;
	private final @Getter UpWrapper<EscapyFBO> shadowsBuffer;
	private final @Getter List<IEscapyModel> nestedModels;

	public LayerModel(
			UpWrapper<EscapyFBO> diffuseBuffer,
			UpWrapper<EscapyFBO> normalsBuffer,
			UpWrapper<EscapyFBO> shadowsBuffer,
			IEscapyModel... nested
	) {
		this.normalsBuffer = normalsBuffer;
		this.diffuseBuffer = diffuseBuffer;
		this.shadowsBuffer = shadowsBuffer;
		this.nestedModels = new ArrayList<>();
		Collections.addAll(this.nestedModels, nested);

		log();
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		wipe();
	}

	@Override
	public void renderDiffuseModel(IEscapyMemoCam camera, Batch batch, float delta) {

		if (diffuseBuffer == null) return;

		diffuseBuffer.get().begin(batch, () -> {
			batch.setProjectionMatrix(camera.update().getProjection());
			renderDiffuseMap(camera, batch, delta);
			for (IEscapyModel model : getNestedModels())
				model.renderDiffuseModel(camera, batch, delta);
		});
		diffuseBuffer.setUpdated(true);
	}

	@Override
	public void renderNormalModel(IEscapyMemoCam camera, Batch batch, float delta) {

		if (normalsBuffer == null) return;

		normalsBuffer.get().begin(batch, () -> {
			batch.setProjectionMatrix(camera.update().getProjection());
			color(0.502f, 0.502f, 1f, 1f);
			for (IEscapyModel model : getNestedModels())
				model.renderNormalModel(camera, batch, delta);
		});
		normalsBuffer.setUpdated(true);
	}

	@Override
	public void renderShadowModel(IEscapyMemoCam camera, Batch batch, float delta) {
		if (shadowsBuffer == null) return;

		shadowsBuffer.get().begin(batch, () -> {
			batch.setProjectionMatrix(camera.update().getProjection());
			wipe();
			for (IEscapyModel model : getNestedModels())
				model.renderShadowModel(camera, batch, delta);
		});
		shadowsBuffer.setUpdated(true);
	}

	@Override
	public void preRender(IEscapyMemoCam camera, Batch batch, float delta) {
		if (diffuseBuffer != null) diffuseBuffer.setUpdated(false);
		if (normalsBuffer != null) normalsBuffer.setUpdated(false);
		if (shadowsBuffer != null) shadowsBuffer.setUpdated(false);
	}


	private void log() {
		log.info("***********\n\n");
		if (diffuseBuffer != null) {
			log.info("DIFFUSE BUFFER DIM: " + diffuseBuffer.get().getWidth() + " : " + diffuseBuffer.get().getHeight());
			log.info("BUFFER ID: " + diffuseBuffer.toString());
		}
		if (normalsBuffer != null) {
			log.info("NORMALS BUFFER DIM: " + normalsBuffer.get().getWidth() + " : " + normalsBuffer.get().getHeight());
			log.info("BUFFER ID: " + normalsBuffer.toString());
		}
		if (shadowsBuffer != null) {
			log.info("SHADOWS BUFFER DIM: " + shadowsBuffer.get().getWidth() + " : " + shadowsBuffer.get().getHeight());
			log.info("BUFFER ID: " + shadowsBuffer.toString());
		}
		log.info("\n\n***********");
	}

}