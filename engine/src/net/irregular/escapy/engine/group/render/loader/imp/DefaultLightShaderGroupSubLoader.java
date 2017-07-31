package net.irregular.escapy.engine.group.render.loader.imp;

import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.engine.group.render.loader.RendererVoidSubLoader;
import net.irregular.escapy.engine.group.render.loader.serial.SerializedRenderer;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultLightShaderGroupSubLoader
		implements RendererVoidSubLoader<EscapyMultiSourceShader, SerializedRenderer>{


	private final EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderLoader;
	public DefaultLightShaderGroupSubLoader(EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderLoader) {
		this.lightShaderLoader = lightShaderLoader;
	}


	@Override
	public EscapyAssociatedArray<EscapyMultiSourceShader> loadRendererPart(SerializedRenderer serialized) {

		EscapyAssociatedArray<EscapyMultiSourceShader> lightShaders = new EscapyNamedArray<>(EscapyMultiSourceShader.class);
		for (SerializedRenderer.SerializedRenderGroup renderGroup : serialized.renderGroups) {
			lightShaders.add(lightShaderLoader.loadInstance(renderGroup.lightGroup.type), renderGroup.lightGroup.type);
		}
		return lightShaders;
	}
}
