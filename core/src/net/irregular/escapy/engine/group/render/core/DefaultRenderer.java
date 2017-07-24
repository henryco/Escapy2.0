package net.irregular.escapy.engine.group.render.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.irregular.escapy.engine.env.context.game.Escapy;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFBO;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFrameBuffer;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.BlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.shader.EscapyVolumeLight;
import net.irregular.escapy.engine.graphic.render.program.shader.proxy.LightSource;
import net.irregular.escapy.engine.graphic.screen.Resolution;

import java.util.Collection;
import java.util.LinkedList;

import static java.io.File.separator;

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

	private final Batch batch_pre;
	private final Batch batch_blend;
	private final Batch batch_post;

	private final EscapyFBO[] fboRenderGroup;
	private final EscapyFBO[] fboMaskGroup;
	private final EscapyFBO[] fboLightGroup;
	private final EscapyFBO[] fboNormalGroup;


	private final EscapyMultiSourceShader blendShader;

	public DefaultRenderer(String name,
						   EscapyAssociatedArray<EscapyRenderable> renderGroups,
						   EscapyAssociatedArray<LightMask> lightMasks,
						   EscapyAssociatedArray<LightSource[]> lightSources,
						   EscapyAssociatedArray<EscapyVolumeLight> volumeProcessors,
						   EscapyAssociatedArray<EscapyGLBlendRenderer> blenders,

						   Resolution resolution) {

		this.namedGroups = new LinkedList<>();

		this.fboNormalGroup = new EscapyFBO[renderGroups.size()];
		this.fboRenderGroup = new EscapyFBO[renderGroups.size()];
		this.fboLightGroup = new EscapyFBO[lightSources.size()];
		this.fboMaskGroup = new EscapyFBO[renderGroups.size()];

		this.volumeProcessors = volumeProcessors;
		this.renderGroups = renderGroups;
		this.lightSources = lightSources;
		this.lightMasks = lightMasks;
		this.blenders = blenders;

		this.batch_pre = new SpriteBatch();
		this.batch_blend = new SpriteBatch();
		this.batch_post = new SpriteBatch();

		this.name = name;

		resize(resolution.width, resolution.height);

		namedGroups.add(this.volumeProcessors);
		namedGroups.add(this.renderGroups);
		namedGroups.add(this.lightSources);
		namedGroups.add(this.lightMasks);
		namedGroups.add(this.blenders);

		String DIR_PATH = Escapy.getWorkDir() + separator + "shaders" + separator
				+ "blend" + separator + "ADD_OVERLAY_STRONG" + separator + "ADD_OVERLAY_STRONG";

		String vert = Gdx.files.internal(DIR_PATH + ".vert").readString();
		String frag = Gdx.files.internal(DIR_PATH + ".frag").readString();

		ShaderFile file = new ShaderFile(vert, frag);
		blendShader = new BlendRenderer(file, "targetMap", "blendMap");
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

			EscapyFBO normalFBO = fboNormalGroup[i];
			EscapyFBO lightFBO = fboLightGroup[i];
			EscapyFBO mainFBO = fboRenderGroup[i];
			EscapyFBO maskFBO = fboMaskGroup[i];



			mainFBO.begin(() -> {
				mainFBO.wipe();
				renderer.renderGraphics(batch_pre);
			});



			if (mask == null) maskFBO = mainFBO;
			else {
				maskFBO.begin(() -> {
					wipe();
					mask.renderMask(mainFBO.getTexture());
				});
			}




			if (lightSource != null && lightSource.length != 0) {

				for (LightSource source: lightSource)
					source.prepareBuffer(batch_pre);


				normalFBO.begin(() -> {
					normalFBO.color(0.502f, 0.502f, 1f, 1f);
					renderer.renderNormalsMap(batch_pre);
				});


				lightFBO.begin(() -> {
					lightFBO.wipe();
					blender.blend(batch_blend, () -> {
						for (LightSource source: lightSource) {
							source.drawBuffer(batch_blend);
						}
					});
				});


				normalFBO.begin(() -> {
					wipe();
					blendShader.draw(batch_post, mainFBO.getSprite(), lightFBO.getSprite());
				}).renderGraphics(batch_post);


//				volume.draw(batch_post, 0, 0, lightFBO.getTexture(), normalFBO.getTexture(), maskFBO.getTexture());

			} else {
				maskFBO.renderGraphics(batch_post);
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

		for (int i = 0; i < fboRenderGroup.length; i++) fboRenderGroup[i] = new EscapyFrameBuffer(resolution);
		for (int i = 0; i < fboMaskGroup.length; i++) fboMaskGroup[i] = new EscapyFrameBuffer(resolution);
		for (int i = 0; i < fboLightGroup.length; i++) fboLightGroup[i] = new EscapyFrameBuffer(resolution);
		for (int i = 0; i < fboNormalGroup.length; i++) fboNormalGroup[i] = new EscapyFrameBuffer(resolution);

		for (EscapyVolumeLight v: volumeProcessors) {
			v.setFieldSize(width, height);
		}
		for (LightSource[] sources: lightSources) {
			for (LightSource s : sources) s.resize(width, height);
		}
	}


	@Override
	public String getName() {
		return name;
	}
}