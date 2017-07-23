package net.irregular.escapy.engine.group.render.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFBO;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFrameBuffer;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.render.program.shader.EscapyVolumeLight;
import net.irregular.escapy.engine.graphic.render.program.shader.proxy.LightSource;
import net.irregular.escapy.engine.graphic.screen.Resolution;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 20/07/17.
 */
public class DefaultRenderer implements EscapyRenderer {

	private final Collection<EscapyAssociatedArray> namedGroups;

	private final EscapyAssociatedArray<EscapyGLBlendRenderer> blenders;
	private final EscapyAssociatedArray<EscapyRenderable> renderGroups;
	private final EscapyAssociatedArray<LightMask> lightMasks;
	private final EscapyAssociatedArray<LightSource[]> lightSources;
	private final EscapyAssociatedArray<EscapyVolumeLight> volumeProcessors;

	private final String name;
	private final Batch batch;

	private final EscapyFBO[] fboRenderGroup;
	private final EscapyFBO[] fboMaskGroup;
	private final EscapyFBO[] fboLightGroup;
	private final EscapyFBO[] fboNormalGroup;


	public DefaultRenderer(String name,
						   EscapyAssociatedArray<EscapyRenderable> renderGroups,
						   EscapyAssociatedArray<LightMask> lightMasks,
						   EscapyAssociatedArray<LightSource[]> lightSources,
						   EscapyAssociatedArray<EscapyVolumeLight> volumeProcessors,
						   EscapyAssociatedArray<EscapyGLBlendRenderer> blenders,

						   Resolution resolution,
						   Batch batch) {

		this.namedGroups = new LinkedList<>();

		this.fboNormalGroup = new EscapyFBO[renderGroups.size()];
		this.fboRenderGroup = new EscapyFBO[renderGroups.size()];
		this.fboLightGroup = new EscapyFBO[lightSources.size()];
		this.fboMaskGroup = new EscapyFBO[lightMasks.size()];

		this.volumeProcessors = volumeProcessors;
		this.renderGroups = renderGroups;
		this.lightSources = lightSources;
		this.lightMasks = lightMasks;
		this.blenders = blenders;

		this.batch = batch;
		this.name = name;

		resize(resolution.width, resolution.height);

		namedGroups.add(this.volumeProcessors);
		namedGroups.add(this.renderGroups);
		namedGroups.add(this.lightSources);
		namedGroups.add(this.lightMasks);
		namedGroups.add(this.blenders);

	}


	@Override
	public void render(float delta) {

		wipe();

		for (int i = 0; i < renderGroups.size(); i++) {

			final EscapyGLBlendRenderer blender = blenders.asArray()[i];
			final EscapyRenderable renderer = renderGroups.asArray()[i];

			final EscapyVolumeLight volume = volumeProcessors.asArray()[i];
			final LightSource[] lightSource = lightSources.asArray()[i];
			final LightMask mask = lightMasks.asArray()[i];

			final EscapyFBO normalFBO = fboNormalGroup[i];
			final EscapyFBO lightFBO = fboLightGroup[i];
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

			if (lightSource != null) {
				for (LightSource source: lightSource)
					source.prepareBuffer(batch);

				lightFBO.begin(() -> {
					lightFBO.wipe();
					blender.blend(b -> {
						mainFBO.renderGraphics(b);
						for (LightSource source: lightSource)
							source.drawBuffer(b);
					});
				});

				normalFBO.begin(() -> {
					normalFBO.wipe();
					renderer.renderNormalsMap(batch);
				});

				volume.draw(batch, lightFBO.getSprite(), normalFBO.getSprite(), maskFBO.getSprite());
			} else {
				maskFBO.renderGraphics(batch);
			}
		}
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
		}
		for (int i = 0; i < fboMaskGroup.length; i++) {
			fboMaskGroup[i] = new EscapyFrameBuffer(resolution);
		}
		for (int i = 0; i < fboLightGroup.length; i++) {
			fboLightGroup[i] = new EscapyFrameBuffer(resolution);
		}
		for (int i = 0; i < fboNormalGroup.length; i++) {
			fboNormalGroup[i] = new EscapyFrameBuffer(resolution);
		}
	}


	@Override
	public String getName() {
		return name;
	}
}