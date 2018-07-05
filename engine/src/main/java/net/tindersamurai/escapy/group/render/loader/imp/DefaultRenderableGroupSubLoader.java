package net.tindersamurai.escapy.group.render.loader.imp;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.render.mapping.EscapyRenderable;
import net.tindersamurai.escapy.group.map.core.layer.EscapyLayer;
import net.tindersamurai.escapy.group.map.core.object.EscapyGameObject;
import net.tindersamurai.escapy.group.render.loader.RendererSubLoader;
import net.tindersamurai.escapy.group.render.loader.serial.SerializedRenderer;
import net.tindersamurai.escapy.utils.array.EscapyAssociatedArray;
import net.tindersamurai.escapy.utils.array.EscapyNamedArray;
import net.tindersamurai.escapy.utils.loader.EscapyInstanceLoader;

import java.util.function.Consumer;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultRenderableGroupSubLoader
		implements RendererSubLoader<EscapyRenderable, SerializedRenderer, EscapyAssociatedArray<EscapyLayer[]>> {


	private final EscapyCamera camera;
	private final EscapyInstanceLoader<EscapyRenderable> renderGroupAttrInstLoader;


	public DefaultRenderableGroupSubLoader(EscapyCamera camera,
										   EscapyInstanceLoader<EscapyRenderable> renderGroupAttrInstLoader) {
		this.camera = camera;
		this.renderGroupAttrInstLoader = renderGroupAttrInstLoader;
	}



	@Override
	public EscapyAssociatedArray<EscapyRenderable> loadRendererPart(SerializedRenderer serialized,
																	EscapyAssociatedArray<EscapyLayer[]> layerGroups) {

		EscapyAssociatedArray<EscapyRenderable> renderGroups = new EscapyNamedArray<>(EscapyRenderable.class);
		for (SerializedRenderer.SerializedRenderGroup renderGroup : serialized.renderGroups) {

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
					for (EscapyLayer layer: layerGroups.get(renderGroup.name)) {

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

			if (renderGroupAttrInstLoader != null)
				renderable = renderGroupAttrInstLoader.loadInstanceAttributes(renderable, renderGroup.attributes);

			renderGroups.add(renderable, renderGroup.name);
		}

		return renderGroups;
	}
}
