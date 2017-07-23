package net.irregular.escapy.engine.group.render.loader.imp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.gl10.blend.NativeSeparateBlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.render.program.shader.EscapyLightSource;
import net.irregular.escapy.engine.graphic.render.program.shader.EscapyVolumeLight;
import net.irregular.escapy.engine.graphic.render.program.shader.proxy.LightSource;
import net.irregular.escapy.engine.graphic.screen.Resolution;
import net.irregular.escapy.engine.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.engine.group.render.core.DefaultRenderer;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;
import net.irregular.escapy.engine.group.render.loader.serial.*;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.function.Consumer;

import static net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray.Entry;
import static net.irregular.escapy.engine.group.render.loader.serial.SerializedRenderer.SerializedRenderGroup;


/**
 * @author Henry on 20/07/17.
 */
public class DefaultRendererLoader implements RendererLoader<EscapySubLocation> {


	private final EscapyCamera camera;
	public DefaultRendererLoader(EscapyCamera camera) {
		this.camera = camera;
	}



	@Override
	public EscapyRenderer loadRenderer(String path, EscapySubLocation arg) {

		final SerializedRenderer serialized;
		try {
			Reader reader = new InputStreamReader(Gdx.files.internal(path).read());
			serialized = new Gson().fromJson(reader, SerializedRenderer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		final Resolution resolution = new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		final EscapyAssociatedArray<EscapyRenderable> renderGroups = loadRenderGroups(arg.getLayerGroups());
		final EscapyAssociatedArray<EscapyGLBlendRenderer> blenders = loadBlender(serialized);
		final EscapyAssociatedArray<EscapyVolumeLight> volumeProcessors = loadVolumeProcessors(serialized, resolution);
		final EscapyAssociatedArray<LightSource[]> lightSources = loadLightGroups(serialized, resolution);
		final EscapyAssociatedArray<LightMask> maskGroups = loadMaskGroups(serialized);

		final Batch batch = new SpriteBatch();


		return new DefaultRenderer(
				serialized.name, renderGroups, maskGroups,
				lightSources, volumeProcessors, blenders,
				resolution, batch
		);
	}




	private EscapyAssociatedArray<LightSource[]> loadLightGroups(SerializedRenderer serialized, Resolution scrDim) {

		EscapyAssociatedArray<LightSource[]> lightSources = new EscapyNamedArray<>(LightSource[].class);
		for (SerializedRenderGroup renderGroup : serialized.renderGroups) {

			List<SerializedLight> lights = renderGroup.lights;
			LightSource[] lightGroup = new LightSource[lights.size()];
			for (int i = 0; i < lightGroup.length; i++) {

				LightSource source = new LightSource(new EscapyLightSource(), scrDim.width, scrDim.height);
				SerializedLight light = lights.get(i);

				source.setResolution(new Resolution(light.resolution2i.get(0), light.resolution2i.get(1)));
				source.setPosition(light.position2f.get(0), light.position2f.get(1));
				source.setAngles(light.angles2f.get(0), light.angles2f.get(1));
				source.setRadius(light.radius2f.get(0), light.radius2f.get(1));
				source.setUmbra(light.umbra2f.get(0), light.umbra2f.get(1));
				source.setCorrect(light.correct);
				source.setCoeff(light.coeff);
				source.setScale(light.scale);

				float r = ((float) light.colorRGB.get(0)) / 255f;
				float g = ((float) light.colorRGB.get(1)) / 255f;
				float b = ((float) light.colorRGB.get(2)) / 255f;

				source.setColor(new Color(r, g, b, 1f));
				lightGroup[i] = source;
			}
			lightSources.add(lightGroup, serialized.name + ":lights");
		}
		return lightSources;
	}





	private EscapyAssociatedArray<EscapyVolumeLight> loadVolumeProcessors(SerializedRenderer serialized, Resolution scrDim) {

		EscapyAssociatedArray<EscapyVolumeLight> volumes = new EscapyNamedArray<>(EscapyVolumeLight.class);
		for (SerializedRenderGroup renderGroup : serialized.renderGroups) {

			SerializedVolumeProcessor processor = renderGroup.processor;
			EscapyVolumeLight volumeLight = new EscapyVolumeLight();

			volumeLight.setFieldSize(scrDim.width, scrDim.height);
			volumeLight.setHeight(processor.height);
			volumeLight.setThreshold(processor.threshold);
			volumeLight.setSpriteSize(processor.spriteSize);
			volumeLight.setAmbientIntensity(processor.intensity.ambient);
			volumeLight.setDirectIntensity(processor.intensity.direct);
			volumeLight.setShadowIntensity(processor.intensity.shadow);

			volumes.add(volumeLight, processor.name);
		}
		return volumes;
	}





	private EscapyAssociatedArray<EscapyGLBlendRenderer> loadBlender(SerializedRenderer serialized) {

		EscapyAssociatedArray<EscapyGLBlendRenderer> blenders = new EscapyNamedArray<>(EscapyGLBlendRenderer.class);
		for (SerializedRenderGroup renderGroup : serialized.renderGroups) {

			SerializedBlender serializedBlender = renderGroup.blender;
			int[] glMode = serializedBlender.blendMode.loadGLMode();
			EscapyGLBlendRenderer blender = new NativeSeparateBlendRenderer(glMode);
			blenders.add(blender, serializedBlender.name);
		}
		return blenders;
	}





	private EscapyAssociatedArray<LightMask> loadMaskGroups(SerializedRenderer serialized) {

		EscapyAssociatedArray<LightMask> maskGroups = new EscapyNamedArray<>(LightMask.class);
		for (SerializedRenderGroup renderGroup : serialized.renderGroups) {
			SerializedLightMask serializedLightMask = renderGroup.lightMask;

			if (serializedLightMask != null) {
				LightMask mask = new LightMask(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				mask.setMaskFunc(serializedLightMask.mode.loadGLMode());
				mask.setColor(serializedLightMask.loadColorRGBA());
				maskGroups.add(mask, serializedLightMask.name);

			} else maskGroups.add(null, null);
		}
		return maskGroups;
	}







	private EscapyAssociatedArray<EscapyRenderable> loadRenderGroups(EscapyAssociatedArray<EscapyLayer[]> layerGroups) {

		EscapyAssociatedArray<EscapyRenderable> renderGroups = new EscapyNamedArray<>(EscapyRenderable.class);
		for (Entry<EscapyLayer[]> entry : layerGroups.getEntrySet()) {

			EscapyRenderable renderable = new EscapyRenderable() {

				@Override
				public void renderGraphics(Batch batch) {
					render(batch, r -> r.renderGraphics(batch));
				}
				@Override
				public void renderLightMap(Batch batch) {
					render(batch, r -> r.renderLightMap(batch));
				}
				@Override
				public void renderNormalsMap(Batch batch) {
					render(batch, r -> r.renderNormalsMap(batch));
				}


				private void render(Batch batch, Consumer<EscapyRenderable> renderableConsumer) {
					for (EscapyLayer layer: entry.getObject()) {
						float[] position = camera.getPosition();
						camera.update(() -> {
							float[] shift = layer.getLayerShifter().calculateShift();
							camera.translateCamera(shift);
						});
						batch.setProjectionMatrix(camera.getProjection());
						batch.begin();
						for (EscapyGameObject gameObject : layer.getGameObjects()) {
							renderableConsumer.accept(gameObject.getGameObjectRenderer().getRenderer());
						}
						batch.end();
						camera.update(() -> camera.setCameraPosition(position));
					}
				}

			};

			renderGroups.add(renderable, entry.getName());
		}

		return renderGroups;
	}

}