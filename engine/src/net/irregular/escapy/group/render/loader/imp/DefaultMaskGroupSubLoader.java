package net.irregular.escapy.group.render.loader.imp;

import com.badlogic.gdx.Gdx;
import net.irregular.escapy.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.group.render.loader.RendererVoidSubLoader;
import net.irregular.escapy.group.render.loader.serial.SerializedLightMask;
import net.irregular.escapy.group.render.loader.serial.SerializedRenderer;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultMaskGroupSubLoader implements RendererVoidSubLoader<LightMask, SerializedRenderer> {


	private final EscapyInstanceLoader<LightMask> lightMaskAttrInstLoader;

	public DefaultMaskGroupSubLoader(EscapyInstanceLoader<LightMask> lightMaskAttrInstLoader) {
		this.lightMaskAttrInstLoader = lightMaskAttrInstLoader;
	}


	@Override
	public EscapyAssociatedArray<LightMask> loadRendererPart(SerializedRenderer serialized) {

		EscapyAssociatedArray<LightMask> maskGroups = new EscapyNamedArray<>(LightMask.class);
		for (SerializedRenderer.SerializedRenderGroup renderGroup : serialized.renderGroups) {
			SerializedLightMask serializedLightMask = renderGroup.lightMask;

			if (serializedLightMask != null) {
				LightMask mask = new LightMask(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				mask.setMaskFunc(serializedLightMask.mode.loadGLMode());
				mask.setColor(serializedLightMask.loadColorRGBA());

				if (lightMaskAttrInstLoader != null)
					mask = lightMaskAttrInstLoader.loadInstanceAttributes(mask, serializedLightMask.attributes);

				maskGroups.add(mask, serializedLightMask.name);
			} else maskGroups.add(null, null);
		}
		return maskGroups;
	}
}