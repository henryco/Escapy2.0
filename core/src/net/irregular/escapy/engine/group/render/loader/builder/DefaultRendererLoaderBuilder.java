package net.irregular.escapy.engine.group.render.loader.builder;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.render.light.EscapyVolumeLight;
import net.irregular.escapy.engine.graphic.render.light.proxy.LightSource;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;
import net.irregular.escapy.engine.group.render.loader.imp.*;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultRendererLoaderBuilder implements RendererLoaderBuilder<EscapySubLocation> {


	private EscapyInstanceLoader<EscapyRenderer> rendererAttributeInstanceLoader;
	private EscapyInstanceLoader<LightMask> lightMaskAttributeInstanceLoader;
	private EscapyInstanceLoader<EscapyGLBlendRenderer> glBlenderAttributeInstanceLoader;
	private EscapyInstanceLoader<EscapyRenderable> renderGroupAttributeInstanceLoader;
	private EscapyInstanceLoader<LightSource> lightSourceAttributeInstanceLoader;
	private EscapyInstanceLoader<EscapyVolumeLight> volumeLightAttributeInstanceLoader;


	private EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderInstanceLoader;
	private EscapyCamera camera;


	@Override
	public RendererLoader<EscapySubLocation> build() {

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
			(EscapyInstanceLoader<EscapyVolumeLight> volumeLightAttributeInstanceLoader) {
		this.volumeLightAttributeInstanceLoader = volumeLightAttributeInstanceLoader;
		return this;
	}

	public DefaultRendererLoaderBuilder setCamera(EscapyCamera camera) {
		this.camera = camera;
		return this;
	}
}