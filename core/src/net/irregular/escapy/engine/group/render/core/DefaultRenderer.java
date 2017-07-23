package net.irregular.escapy.engine.group.render.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFBO;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFrameBuffer;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.render.program.shader.AbsLightSource;
import net.irregular.escapy.engine.graphic.screen.Resolution;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 20/07/17.
 */
public class DefaultRenderer implements EscapyRenderer {

	private final Collection<EscapyAssociatedArray> namedGroups;

	private final EscapyAssociatedArray<EscapyRenderable> renderGroups;
	private final EscapyAssociatedArray<LightMask> lightMasks;

	private final String name;
	private final Batch batch;

	private final EscapyFBO[] fboRenderGroup;
	private final EscapyFBO[] fboMaskGroup;
	private final EscapyFBO[] fboLightGroup;

	AbsLightSource lightSource;
	EscapyFBO tempFbo;

	public DefaultRenderer(String name,
						   EscapyAssociatedArray<EscapyRenderable> renderGroups,
						   EscapyAssociatedArray<LightMask> lightMasks,
						   Batch batch,
						   int scrWidth,
						   int scrHeight) {

		this.namedGroups = new LinkedList<>();

		this.fboRenderGroup = new EscapyFBO[renderGroups.size()];
		this.fboMaskGroup = new EscapyFBO[lightMasks.size()];
		this.fboLightGroup = new EscapyFBO[renderGroups.size()];

		this.renderGroups = renderGroups;
		this.lightMasks = lightMasks;
		this.batch = batch;
		this.name = name;

		resize(scrWidth, scrHeight);

		namedGroups.add(renderGroups);
		namedGroups.add(lightMasks);

		lightSource = new AbsLightSource();
		lightSource.setAngles(0f, 0f);
		lightSource.setColor(new Color(Color.FIREBRICK));
		lightSource.setCorrect(0.0f);
		lightSource.setRadius(0, 1);
		lightSource.setCoeff(1);
		lightSource.setUmbra(0, 1);
		lightSource.setResolution(new Resolution(256, 256));

		tempFbo = new EscapyFrameBuffer(new Resolution(256, 256), true);
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
				if (mask != null) mask.renderMask(mainFBO.getTexture());
				else mainFBO.renderGraphics(batch);
			});
//			maskFBO.renderGraphics(batch);
		}

		fboLightGroup[0].begin(() -> {
			fboLightGroup[0].wipe();
			lightSource.draw(batch, tempFbo.getSprite(), tempFbo.getSprite());
		});

		fboLightGroup[0].renderGraphics(batch);
//		for (EscapyFBO fbo: fboRenderGroup) fbo.renderGraphics(batch);
	}


	@Override @SuppressWarnings("unchecked")
	public <T> T getRendererAttribute(String name) {

		Object attribute;
		for (EscapyAssociatedArray array: namedGroups)
			if ((attribute = array.get(name)) != null)
				return (T) attribute;
		return null;
	}


	@Override
	public void resize(int width, int height) {

		final Resolution resolution = new Resolution(width, height);

		for (int i = 0; i < fboRenderGroup.length; i++) {
			fboRenderGroup[i] = new EscapyFrameBuffer(resolution);
			fboRenderGroup[i].setFlip(false, true);
		}
		for (int i = 0; i < fboMaskGroup.length; i++) {
			fboMaskGroup[i] = new EscapyFrameBuffer(resolution);
			fboMaskGroup[i].setFlip(false, true);
		}
		for (int i = 0; i < fboLightGroup.length; i++) {
			fboLightGroup[i] = new EscapyFrameBuffer(resolution);
			fboLightGroup[i].setFlip(false, true);
		}
	}


	@Override
	public String getName() {
		return name;
	}
}