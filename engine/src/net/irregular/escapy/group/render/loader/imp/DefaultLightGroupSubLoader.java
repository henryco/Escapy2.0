package net.irregular.escapy.group.render.loader.imp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.environment.utils.array.EscapyAssociatedArray;
import net.irregular.escapy.environment.utils.array.EscapyNamedArray;
import net.irregular.escapy.environment.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.graphic.render.light.source.EscapyLightSource;
import net.irregular.escapy.graphic.render.light.source.LightSource;
import net.irregular.escapy.graphic.screen.Resolution;
import net.irregular.escapy.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.group.render.loader.RendererSubLoader;
import net.irregular.escapy.group.render.loader.serial.SerializedLight;
import net.irregular.escapy.group.render.loader.serial.SerializedRenderer;

import java.util.List;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultLightGroupSubLoader
		implements RendererSubLoader<LightSource[], SerializedRenderer, EscapyAssociatedArray<EscapyLayer[]>> {


	private final EscapyInstanceLoader<LightSource> lightSourceAttrInstLoader;
	public DefaultLightGroupSubLoader(EscapyInstanceLoader<LightSource> lightSourceAttrInstLoader) {
		this.lightSourceAttrInstLoader = lightSourceAttrInstLoader;
	}



	@Override
	public EscapyAssociatedArray<LightSource[]> loadRendererPart(SerializedRenderer serialized,
																	EscapyAssociatedArray<EscapyLayer[]> layerGroups) {


		EscapyAssociatedArray<LightSource[]> lightSources = new EscapyNamedArray<>(LightSource[].class);
		for (SerializedRenderer.SerializedRenderGroup renderGroup : serialized.renderGroups) {

			EscapyLayer[] layers = layerGroups.get(renderGroup.name);
			List<SerializedLight> lights = renderGroup.lightGroup.lights;

			LightSource[] lightGroup = new LightSource[lights.size()];
			for (int i = 0; i < lightGroup.length; i++) {

				SerializedLight light = lights.get(i);

				LayerShift layerShifter = null;
				if (light.shift == null) layerShifter = layers[0].getLayerShifter();
				else for (EscapyLayer layer: layers) {
					if (light.shift.equals(layer.getName())) {
						layerShifter = layer.getLayerShifter();
						break;
					}
				}



				final LayerShift finalLayerShifter = layerShifter;
				LightSource source = new LightSource(light.name, new EscapyLightSource(),
						Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
				{

					private float[] state_shift = {0, 0};

					@Override
					public void prepareBuffer(Batch batch, boolean force) {
						super.prepareBuffer(batch, force);

						float[] shift = finalLayerShifter.calculateShift();
						float tx = shift[0] - state_shift[0];
						float ty = shift[1] - state_shift[1];

						getBuffer().getSprite().translate(tx, ty);

						this.state_shift = shift;
					}

				};


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

				if (lightSourceAttrInstLoader != null)
					source = lightSourceAttrInstLoader.loadInstanceAttributes(source, light.attributes);

				lightGroup[i] = source;
			}
			lightSources.add(lightGroup, renderGroup.lightGroup.name);
		}
		return lightSources;
	}
}