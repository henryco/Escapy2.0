package net.irregular.escapy.engine.group.render.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFBO;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFrameBuffer;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.screen.Resolution;

/**
 * @author Henry on 20/07/17.
 */
public class DefaultRenderer implements EscapyRenderer {


	private final EscapyAssociatedArray<EscapyRenderable> renderGroups;
	private final EscapyAssociatedArray<LightMask> lightMasks;

	private final String name;
	private final Batch batch;

	private final EscapyFBO[] fboRenderGroup;
	private final EscapyFBO[] fboMaskGroup;



	public DefaultRenderer(String name,
						   EscapyAssociatedArray<EscapyRenderable> renderGroups,
						   EscapyAssociatedArray<LightMask> lightMasks,
						   Batch batch,
						   int scrWidth,
						   int scrHeight) {

		this.fboRenderGroup = new EscapyFBO[renderGroups.size()];
		this.fboMaskGroup = new EscapyFBO[lightMasks.size()];

		this.renderGroups = renderGroups;
		this.lightMasks = lightMasks;
		this.batch = batch;
		this.name = name;

		resize(scrWidth, scrHeight);
	}




	@Override
	public void render(float delta) {

		wipe();

		for (int i = 0; i < renderGroups.size(); i++) {

			final LightMask mask = lightMasks.asArray()[i];

			final EscapyRenderable renderer = renderGroups.asArray()[i];
			final EscapyFBO mainFBO = fboRenderGroup[i];
			final EscapyFBO maskFBO = fboMaskGroup[i];


			mainFBO.begin(() -> {
				mainFBO.wipe();
				renderer.renderGraphics(batch);
			});

			maskFBO.begin(() -> {
				maskFBO.wipe();
				mask.renderMask(mainFBO.getTexture());
			});

			maskFBO.renderGraphics(batch);
		}


	}




	@Override
	public void resize(int width, int height) {

		final Resolution resolution = new Resolution(width, height);

		for (int i = 0; i < fboRenderGroup.length; i++)
			fboRenderGroup[i] = new EscapyFrameBuffer(resolution);
		for (int i = 0; i < fboMaskGroup.length; i++)
			fboMaskGroup[i] = new EscapyFrameBuffer(resolution);
	}


	@Override
	public String getName() {
		return name;
	}
}