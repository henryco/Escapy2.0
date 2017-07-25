package net.irregular.escapy.engine.group.render.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.irregular.escapy.engine.env.utils.Named;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFBO;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFrameBuffer;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.EscapyMultiSourceShader;
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

	private final EscapyAssociatedArray<EscapyMultiSourceShader> lightBlenders;
	private final EscapyAssociatedArray<EscapyGLBlendRenderer> blenders;
	private final EscapyAssociatedArray<EscapyRenderable> renderGroups;
	private final EscapyAssociatedArray<LightMask> lightMasks;
	private final EscapyAssociatedArray<LightSource[]> lightSources;
	private final EscapyAssociatedArray<EscapyVolumeLight> volumeProcessors;

	private final String name;

	private final EscapyFBO[] fboGroup;
	private final Batch[] batchGroup;

	public DefaultRenderer(String name,
						   EscapyAssociatedArray<EscapyRenderable> renderGroups,
						   EscapyAssociatedArray<LightMask> lightMasks,
						   EscapyAssociatedArray<LightSource[]> lightSources,
						   EscapyAssociatedArray<EscapyVolumeLight> volumeProcessors,
						   EscapyAssociatedArray<EscapyGLBlendRenderer> blenders,
						   EscapyAssociatedArray<EscapyMultiSourceShader> lightBlenders,
						   Resolution resolution) {

		this.namedGroups = new LinkedList<>();
		this.fboGroup = new EscapyFBO[5];
		this.batchGroup = new Batch[]{
				new SpriteBatch(),
				new SpriteBatch(),
				new SpriteBatch()
		};

		this.volumeProcessors = volumeProcessors;
		this.lightBlenders = lightBlenders;
		this.renderGroups = renderGroups;
		this.lightSources = lightSources;
		this.lightMasks = lightMasks;
		this.blenders = blenders;
		this.name = name;

		resize(resolution.width, resolution.height);

		namedGroups.add(this.volumeProcessors);
		namedGroups.add(this.lightBlenders);
		namedGroups.add(this.renderGroups);
		namedGroups.add(this.lightSources);
		namedGroups.add(this.lightMasks);
		namedGroups.add(this.blenders);
	}


	@Override
	public void render(float delta) {

		wipe();

		for (int i = 0; i < renderGroups.size(); i++) {

			final EscapyMultiSourceShader lightBlender = lightBlenders.asArray()[i];
			final EscapyGLBlendRenderer blender = blenders.asArray()[i];
			final EscapyRenderable renderer = renderGroups.asArray()[i];

			final EscapyVolumeLight volume = volumeProcessors.asArray()[i];
			final LightSource[] lightSource = lightSources.asArray()[i];
			final LightMask mask = lightMasks.asArray()[i];


			EscapyFBO mainFBO = fboGroup[0];
			EscapyFBO maskFBO = fboGroup[1];
			EscapyFBO lightFBO = fboGroup[2];
			EscapyFBO normalFBO = fboGroup[3];
			EscapyFBO colorFBO = fboGroup[4];

			Batch batch_pre = batchGroup[0];
			Batch batch_blend = batchGroup[1];
			Batch batch_post = batchGroup[2];


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


			maskFBO.renderGraphics(batch_post);


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


				colorFBO.begin(() -> {
					wipe();
					lightBlender.draw(batch_post, mainFBO.getSprite(), lightFBO.getSprite());
				});


				volume.draw(batch_post, colorFBO.getSprite(), normalFBO.getSprite(), maskFBO.getSprite());
			}
		}
	}


	@Override @SuppressWarnings("unchecked")
	public <T> T getRendererAttribute(String name) {

		String[] names = name.split(":");
		if (names != null && !names[0].equals(name)) {

			Object parent = getRendererAttribute(names[0]);
			for (int i = 1; i < names.length; i++) {

				if (parent instanceof Object[]) {
					for (Object o: ((Object[]) parent)) {
						if (o instanceof Named) {
							if (((Named) o).getName().equals(names[i]))
								parent = o;
						} else break;
					}
				}

				else if (parent instanceof Iterable) {
					for (Object o : ((Iterable) parent)) {
						if (o instanceof Named) {
							if (((Named) o).getName().equals(names[i]))
								parent = o;
						} else break;
					}
				}

				else return null;
			}
			return (T) parent;
		}

		Object attribute;
		for (EscapyAssociatedArray array: namedGroups)
			if ((attribute = array.get(name)) != null)
				return (T) attribute;
		return null;
	}


	@Override
	public void resize(int width, int height) {

		final Resolution resolution = new Resolution(width, height);

		for (int i = 0; i < fboGroup.length; i++) fboGroup[i] = new EscapyFrameBuffer(resolution);
		for (EscapyVolumeLight v: volumeProcessors) v.setFieldSize(width, height);

		for (LightSource[] sources: lightSources) {
			for (LightSource s : sources) s.resize(width, height);
		}
	}

	@Override
	public void dispose() {
		for (EscapyFBO fbo: fboGroup) fbo.dispose();
		for (EscapyVolumeLight v: volumeProcessors) v.dispose();
		for (LightSource[] sources: lightSources) {
			for (LightSource s : sources) s.dispose();
		}
	}

	@Override
	public String getName() {
		return name;
	}
}