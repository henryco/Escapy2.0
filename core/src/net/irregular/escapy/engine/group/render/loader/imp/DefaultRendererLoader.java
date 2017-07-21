package net.irregular.escapy.engine.group.render.loader.imp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.map.core.object.GameObject;
import net.irregular.escapy.engine.group.render.core.DefaultRenderer;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;
import net.irregular.escapy.engine.group.render.loader.serial.SerializedRenderer;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Consumer;

import static net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray.Entry;
import static net.irregular.escapy.engine.group.render.loader.serial.SerializedRenderer.SerializedLightMask;
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
		} catch (Exception ignored) {return null;}

		final EscapyAssociatedArray<EscapyRenderable> renderGroups = loadRenderGroups(arg.getLayerGroups());
		final EscapyAssociatedArray<LightMask> maskGroups = loadMaskGroups(serialized);
		final Batch batch = new SpriteBatch();

		return new DefaultRenderer(
				serialized.name, renderGroups, maskGroups, batch,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
		);
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

						for (GameObject gameObject : layer.getGameObjects()) {
							renderableConsumer.accept(gameObject.getGameObjectRenderer().getRenderer());
						}

						camera.update(() -> camera.setCameraPosition(position));
					}
				}

			};

			renderGroups.add(renderable, entry.getName());
		}

		return renderGroups;
	}

}