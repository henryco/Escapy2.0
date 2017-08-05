package net.irregular.escapy.group.render.loader.builder;

import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.graphic.render.light.processor.EscapyLightProcessor;
import net.irregular.escapy.graphic.render.light.source.LightSource;
import net.irregular.escapy.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.group.render.core.EscapyRenderer;
import net.irregular.escapy.group.render.loader.imp.*;
import net.irregular.escapy.utils.loader.EscapyInstanceLoader;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultRendererLoaderBuilder implements RendererLoaderBuilder<EscapySubLocation> {


	private EscapyInstanceLoader<EscapyRenderer> rendererAttributeLoader;
	private EscapyInstanceLoader<LightMask> lightMaskAttributeLoader;
	private EscapyInstanceLoader<EscapyGLBlendRenderer> glBlenderAttributeLoader;
	private EscapyInstanceLoader<EscapyRenderable> renderGroupAttributeLoader;
	private EscapyInstanceLoader<LightSource> lightSourceAttributeLoader;
	private EscapyInstanceLoader<EscapyLightProcessor> volumeLightAttributeLoader;
	private EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderAttributeLoader;

	private EscapyCamera camera;


	@Override
	public DefaultRendererLoader build() {

		if (camera == null) throw new RuntimeException("EscapyCamera cannot be NULL");

		return new DefaultRendererLoader(
				new DefaultMaskGroupSubLoader(lightMaskAttributeLoader),
				new DefaultGLBlenderGroupSubLoader(glBlenderAttributeLoader, camera),
				new DefaultLightShaderGroupSubLoader(lightShaderAttributeLoader),
				new DefaultRenderableGroupSubLoader(camera, renderGroupAttributeLoader),
				new DefaultLightGroupSubLoader(lightSourceAttributeLoader),
				new DefaultLightProcessorGroupSubLoader(volumeLightAttributeLoader),
				rendererAttributeLoader
		);
	}


	public DefaultRendererLoaderBuilder setRendererAttributeLoader
			(EscapyInstanceLoader<EscapyRenderer> rendererAttributeLoader) {
		this.rendererAttributeLoader = rendererAttributeLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setLightMaskAttributeLoader
			(EscapyInstanceLoader<LightMask> lightMaskAttributeLoader) {
		this.lightMaskAttributeLoader = lightMaskAttributeLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setGlBlenderAttributeLoader
			(EscapyInstanceLoader<EscapyGLBlendRenderer> glBlenderAttributeLoader) {
		this.glBlenderAttributeLoader = glBlenderAttributeLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setLightShaderAttributeLoader
			(EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderAttributeLoader) {
		this.lightShaderAttributeLoader = lightShaderAttributeLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setRenderGroupAttributeLoader
			(EscapyInstanceLoader<EscapyRenderable> renderGroupAttributeLoader) {
		this.renderGroupAttributeLoader = renderGroupAttributeLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setLightSourceAttributeLoader
			(EscapyInstanceLoader<LightSource> lightSourceAttributeLoader) {
		this.lightSourceAttributeLoader = lightSourceAttributeLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setVolumeLightAttributeLoader
			(EscapyInstanceLoader<EscapyLightProcessor> volumeLightAttributeLoader) {
		this.volumeLightAttributeLoader = volumeLightAttributeLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setCamera(EscapyCamera camera) {
		this.camera = camera;
		return this;
	}
}