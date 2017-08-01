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


	private EscapyInstanceLoader<EscapyRenderer> rendererAttributeInstanceLoader;
	private EscapyInstanceLoader<LightMask> lightMaskAttributeInstanceLoader;
	private EscapyInstanceLoader<EscapyGLBlendRenderer> glBlenderAttributeInstanceLoader;
	private EscapyInstanceLoader<EscapyRenderable> renderGroupAttributeInstanceLoader;
	private EscapyInstanceLoader<LightSource> lightSourceAttributeInstanceLoader;
	private EscapyInstanceLoader<EscapyLightProcessor> volumeLightAttributeInstanceLoader;


	private EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderInstanceLoader;
	private EscapyCamera camera;


	@Override
	public DefaultRendererLoader build() {

		if (camera == null) throw new RuntimeException("EscapyCamera cannot be NULL");
		if (lightShaderInstanceLoader == null) throw new RuntimeException("LightShaderInstanceLoader cannot be NULL");

		return new DefaultRendererLoader(
				new DefaultMaskGroupSubLoader(lightMaskAttributeInstanceLoader),
				new DefaultGLBlenderGroupSubLoader(glBlenderAttributeInstanceLoader, camera),
				new DefaultLightShaderGroupSubLoader(lightShaderInstanceLoader),
				new DefaultRenderableGroupSubLoader(camera, renderGroupAttributeInstanceLoader),
				new DefaultLightGroupSubLoader(lightSourceAttributeInstanceLoader),
				new DefaultLightProcessorGroupSubLoader(volumeLightAttributeInstanceLoader),
				rendererAttributeInstanceLoader
		);
	}


	public DefaultRendererLoaderBuilder setRendererAttributeInstanceLoader
			(EscapyInstanceLoader<EscapyRenderer> rendererAttributeInstanceLoader) {
		this.rendererAttributeInstanceLoader = rendererAttributeInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setLightMaskAttributeInstanceLoader
			(EscapyInstanceLoader<LightMask> lightMaskAttributeInstanceLoader) {
		this.lightMaskAttributeInstanceLoader = lightMaskAttributeInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setGlBlenderAttributeInstanceLoader
			(EscapyInstanceLoader<EscapyGLBlendRenderer> glBlenderAttributeInstanceLoader) {
		this.glBlenderAttributeInstanceLoader = glBlenderAttributeInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setLightShaderInstanceLoader
			(EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderInstanceLoader) {
		this.lightShaderInstanceLoader = lightShaderInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setRenderGroupAttributeInstanceLoader
			(EscapyInstanceLoader<EscapyRenderable> renderGroupAttributeInstanceLoader) {
		this.renderGroupAttributeInstanceLoader = renderGroupAttributeInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setLightSourceAttributeInstanceLoader
			(EscapyInstanceLoader<LightSource> lightSourceAttributeInstanceLoader) {
		this.lightSourceAttributeInstanceLoader = lightSourceAttributeInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setVolumeLightAttributeInstanceLoader
			(EscapyInstanceLoader<EscapyLightProcessor> volumeLightAttributeInstanceLoader) {
		this.volumeLightAttributeInstanceLoader = volumeLightAttributeInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setCamera(EscapyCamera camera) {
		this.camera = camera;
		return this;
	}
}