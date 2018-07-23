package net.tindersamurai.escapy.components.model.plain.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.EscapyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log public class LightTypeModel implements IEscapyModel {

	private final @Getter EscapyMultiSourceShader lightBlender;
	private final @Getter EscapyGLBlendRenderer blender;

	private final @Getter UpWrapper<EscapyFBO> lightColorFbo;
	private final @Getter UpWrapper<EscapyFBO> lightTypeFbo;
	private final @Getter UpWrapper<EscapyFBO> diffuseFbo;
	private final @Getter List<IEscapyModel> nestedModels;

	private final Batch batch;
	private final Batch post;

	private IEscapyMemoCam commonCamera;


	public LightTypeModel (
			EscapyMultiSourceShader lightBlender,
			UpWrapper<EscapyFBO> lightColorFbo,
			UpWrapper<EscapyFBO> lightTypeFbo,
			UpWrapper<EscapyFBO> diffuseFbo,
			EscapyGLBlendRenderer blender,
			IEscapyModel ... nested
	) {
		if (lightColorFbo == null)
			throw new RuntimeException("LightColor FBO == NULL");
		if (lightTypeFbo == null)
			throw new RuntimeException("LightType FBO == NULL");
		if (diffuseFbo == null)
			throw new RuntimeException("Diffuse FBO == NULL");
		if (blender == null)
			throw new RuntimeException("LightGLBlender == NULL");
		if (lightBlender == null)
			throw new RuntimeException("LightBlender == NULL");

		this.lightBlender = lightBlender;
		this.blender = blender;

		this.diffuseFbo = diffuseFbo;
		this.lightTypeFbo = lightTypeFbo;
		this.lightColorFbo = lightColorFbo;
		this.nestedModels = new ArrayList<>();
		Collections.addAll(this.nestedModels, nested);

		this.batch = new SpriteBatch();
		this.post = new SpriteBatch();
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch _batch, float delta) {
		wipe();
	}

	@Override
	public void renderDiffuseModel(IEscapyMemoCam camera, Batch _batch, float delta) {
		lightTypeFbo.get().begin(() -> {
			batch.setProjectionMatrix(camera.update().getProjection());
			renderDiffuseMap(camera, batch, delta);
			blender.blend(batch, () -> {
				for (IEscapyModel model : getNestedModels())
					model.renderDiffuseModel(camera, batch, delta);
			});
		});
		lightTypeFbo.setUpdated(true);
		commonCamera = camera;
	}

	@Override
	public void preRender(IEscapyMemoCam camera, Batch _batch, float delta) {
		lightTypeFbo.setUpdated(false);
		lightColorFbo.setUpdated(false);
	}

	@Override
	public void postRender(IEscapyMemoCam camera, Batch _batch, float delta) {
		if (!diffuseFbo.isUpdated()) return;

//		EscapyUtils.centerize (
//				diffuseFbo.get().getSprite(),
//				Gdx.graphics.getWidth(),
//				Gdx.graphics.getHeight()
//		);
//
//		EscapyUtils.centerize (
//				lightTypeFbo.get().getSprite(),
//				Gdx.graphics.getWidth(),
//				Gdx.graphics.getHeight()
//		);

		commonCamera.save();
		commonCamera.setCameraPosition(lightColorFbo.get().getWidth() * .5f, lightColorFbo.get().getHeight() * .5f, true);
		post.setProjectionMatrix(commonCamera.update().getProjection());
		lightColorFbo.get().begin(() -> {
			if (!lightColorFbo.isUpdated()) {
				wipe();
				lightBlender.draw (
						post, diffuseFbo.get().getSprite(), lightTypeFbo.get().getSprite()
				);
			} else lightBlender.draw (
					post, lightColorFbo.get().getSprite(), lightTypeFbo.get().getSprite()
			);
		});
		lightColorFbo.setUpdated(true);
		commonCamera.revert();

//		_batch.setProjectionMatrix(camera.getProjection());

//		diffuseFbo.get().draw(_batch);
//		lightTypeFbo.get().draw(_batch);
//		lightColorFbo.get().draw(_batch);
	}

}