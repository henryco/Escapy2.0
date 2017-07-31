package net.irregular.escapy.group.render.loader.imp;

import com.badlogic.gdx.Gdx;
import net.irregular.escapy.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.graphic.render.light.processor.EscapyFlatLight;
import net.irregular.escapy.graphic.render.light.processor.EscapyLightProcessor;
import net.irregular.escapy.graphic.render.light.processor.EscapyVolumeLight;
import net.irregular.escapy.group.render.loader.RendererVoidSubLoader;
import net.irregular.escapy.group.render.loader.serial.SerializedLightProcessor;
import net.irregular.escapy.group.render.loader.serial.SerializedRenderer;

import static net.irregular.escapy.group.render.loader.serial.SerializedRenderer.SerializedRenderGroup;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultLightProcessorGroupSubLoader
		implements RendererVoidSubLoader<EscapyLightProcessor, SerializedRenderer> {


	private final EscapyInstanceLoader<EscapyLightProcessor> volumeLightAttrInstLoader;
	public DefaultLightProcessorGroupSubLoader(EscapyInstanceLoader<EscapyLightProcessor> volumeLightAttrInstLoader) {
		this.volumeLightAttrInstLoader = volumeLightAttrInstLoader;
	}


	@Override
	public EscapyAssociatedArray<EscapyLightProcessor> loadRendererPart(SerializedRenderer serialized) {

		EscapyAssociatedArray<EscapyLightProcessor> volumes = new EscapyNamedArray<>(EscapyLightProcessor.class);
		for (SerializedRenderGroup renderGroup : serialized.renderGroups) {

			final SerializedLightProcessor processor = renderGroup.processor;

			EscapyLightProcessor lightProcessor = loadLightProcessor(processor);
			lightProcessor.setFieldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			lightProcessor.setThreshold(processor.threshold);
			lightProcessor.setEnable(processor.enable);

			if (volumeLightAttrInstLoader != null)
				lightProcessor = volumeLightAttrInstLoader.loadInstanceAttributes(lightProcessor, processor.attributes);

			volumes.add(lightProcessor, processor.name);
		}

		return volumes;
	}



	private static EscapyLightProcessor loadLightProcessor(SerializedLightProcessor processor) {

		final String type = processor.type;
		if (type.equals(SerializedLightProcessor.TYPE_FLAT))
			return loadFlatProcessor(processor);
		if (type.equals(SerializedLightProcessor.TYPE_VOLUMETRIC))
			return loadVolumetricProcessor(processor);
		return null;
	}



	private static EscapyVolumeLight loadVolumetricProcessor(SerializedLightProcessor processor) {

		final EscapyVolumeLight volumeLight = new EscapyVolumeLight(processor.name);
		volumeLight.setAmbientIntensity(processor.intensity.ambient);
		volumeLight.setDirectIntensity(processor.intensity.direct);
		volumeLight.setShadowIntensity(processor.intensity.shadow);
		volumeLight.setSpriteSize(processor.spriteSize);
		volumeLight.setHeight(processor.height);
		return volumeLight;
	}

	private static EscapyFlatLight loadFlatProcessor(SerializedLightProcessor processor) {
		return new EscapyFlatLight(processor.name);
	}


}