package net.tindersamurai.escapy.components.model.plain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.program.gl10.mask.EscapyLightMask;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.EscapyUtils;

@Log
public class MaskModel implements IEscapyModel {

	private final @Getter UpWrapper<EscapyFBO> diffuseBuffer;
	private final @Getter UpWrapper<EscapyFBO> maskBuffer;
	private final @Getter EscapyLightMask lightMask;

	public MaskModel (
			UpWrapper<EscapyFBO> diffuseBuffer,
			UpWrapper<EscapyFBO> maskBuffer,
			EscapyLightMask lightMask
	) {
		this.diffuseBuffer = diffuseBuffer;
		this.maskBuffer = maskBuffer;
		this.lightMask = lightMask;

		if (maskBuffer == null)
			log.warning("MASK BUFFER == NULL");
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		wipe();
	}

	@Override
	public void postRender(IEscapyMemoCam camera, Batch batch, float delta) {

		if (lightMask == null) throw new RuntimeException("Light mask in NULL!");
		if (diffuseBuffer == null || !diffuseBuffer.isUpdated()) return;

		if (maskBuffer == null) {
			return;
		}

		maskBuffer.get().begin(() -> {
			wipe();
			lightMask.renderMask(diffuseBuffer.get().getTexture());
		});

 		EscapyUtils.centerize(
				maskBuffer.get().getSprite(),
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()
		);

		batch.setProjectionMatrix(camera.update().getProjection());
		maskBuffer.get().renderGraphics(batch);
	}
}