package net.irregular.escapy.engine.group.render.loader.imp;

import com.badlogic.gdx.Gdx;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.graphic.render.light.EscapyVolumeLight;
import net.irregular.escapy.engine.group.render.loader.RendererVoidSubLoader;
import net.irregular.escapy.engine.group.render.loader.serial.SerializedRenderer;
import net.irregular.escapy.engine.group.render.loader.serial.SerializedVolumeProcessor;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultLightProcessorGroupSubLoader
		implements RendererVoidSubLoader<EscapyVolumeLight, SerializedRenderer> {


	private final EscapyInstanceLoader<EscapyVolumeLight> volumeLightAttrInstLoader;
	public DefaultLightProcessorGroupSubLoader(EscapyInstanceLoader<EscapyVolumeLight> volumeLightAttrInstLoader) {
		this.volumeLightAttrInstLoader = volumeLightAttrInstLoader;
	}


	@Override
	public EscapyAssociatedArray<EscapyVolumeLight> loadRendererPart(SerializedRenderer serialized) {

		EscapyAssociatedArray<EscapyVolumeLight> volumes = new EscapyNamedArray<>(EscapyVolumeLight.class);
		for (SerializedRenderer.SerializedRenderGroup renderGroup : serialized.renderGroups) {

			SerializedVolumeProcessor processor = renderGroup.processor;
			EscapyVolumeLight volumeLight = new EscapyVolumeLight(processor.name);

			volumeLight.setFieldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			volumeLight.setHeight(processor.height);
			volumeLight.setThreshold(processor.threshold);
			volumeLight.setSpriteSize(processor.spriteSize);
			volumeLight.setAmbientIntensity(processor.intensity.ambient);
			volumeLight.setDirectIntensity(processor.intensity.direct);
			volumeLight.setShadowIntensity(processor.intensity.shadow);

			if (volumeLightAttrInstLoader != null)
				volumeLight = volumeLightAttrInstLoader.loadInstanceAttributes(volumeLight, processor.attributes);

			volumes.add(volumeLight, processor.name);
		}
		return volumes;
	}
}
