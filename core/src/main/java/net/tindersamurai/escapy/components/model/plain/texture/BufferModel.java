package net.tindersamurai.escapy.components.model.plain.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.EscapyUtils;

import java.util.Arrays;

@Log public class BufferModel implements IEscapyModel {

	private final @Getter UpWrapper<EscapyFBO>[] buffers;
	private final Batch postRenderBatch;

	@SafeVarargs public BufferModel(UpWrapper<EscapyFBO>... buffers) {
		this.postRenderBatch = new SpriteBatch();
		this.buffers = buffers;
		log.info("BUFFER MODEL CREATED: " + Arrays.toString(buffers));
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		// nothing
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		// nothing
	}

	@Override
	public void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		// nothing
	}

	@Override
	public void postRender(IEscapyMemoCam camera, Batch batch, float delta) {
		for (val wrapper : buffers) {
			if (wrapper.isUpdated()) {
				val buffer = wrapper.get();
				val buffPos = EscapyUtils.center (
						buffer.getSprite(),
						Gdx.graphics.getWidth(),
						Gdx.graphics.getHeight()
				);

				postRenderBatch.setProjectionMatrix(camera.update().getProjection());
				buffer.draw(postRenderBatch);
				buffer.getSprite().setPosition(buffPos[0], buffPos[1]);
			}
		}
	}

}