package net.irregular.escapy.engine.group.render.loader.imp;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.engine.graphic.render.program.shader.EscapyVolumeLight;
import net.irregular.escapy.engine.graphic.render.program.shader.proxy.LightSource;
import net.irregular.escapy.engine.graphic.screen.Resolution;
import net.irregular.escapy.engine.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.render.core.DefaultRenderer;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;
import net.irregular.escapy.engine.group.render.loader.RendererSubLoader;
import net.irregular.escapy.engine.group.render.loader.serial.SerializedRenderer;

import java.io.InputStreamReader;
import java.io.Reader;


/**
 * @author Henry on 20/07/17.
 */
public class DefaultRendererLoader implements RendererLoader<EscapySubLocation> {


	private final RendererSubLoader<LightMask, SerializedRenderer, Void> maskGroupSubLoader;
	private final RendererSubLoader<EscapyGLBlendRenderer, SerializedRenderer, Void> glBlenderGroupSubLoader;
	private final RendererSubLoader<EscapyMultiSourceShader, SerializedRenderer, Void> lightShaderGroupSubLoader;
	private final RendererSubLoader<EscapyRenderable, SerializedRenderer, EscapyAssociatedArray<EscapyLayer[]>> renderableGruopSubLoader;
	private final RendererSubLoader<LightSource[], SerializedRenderer, EscapyAssociatedArray<EscapyLayer[]>> lightGroupSubLoader;
	private final RendererSubLoader<EscapyVolumeLight, SerializedRenderer, Void> lightProcessorGroupSubLoader;

	private final EscapyInstanceLoader<EscapyRenderer> rendererAttrInstLoader;


	public DefaultRendererLoader(RendererSubLoader<LightMask, SerializedRenderer, Void> maskGroupSubLoader,
								 RendererSubLoader<EscapyGLBlendRenderer, SerializedRenderer, Void> glBlenderGroupSubLoader,
								 RendererSubLoader<EscapyMultiSourceShader, SerializedRenderer, Void> lightShaderGroupSubLoader,
								 RendererSubLoader<EscapyRenderable, SerializedRenderer, EscapyAssociatedArray<EscapyLayer[]>> renderableGruopSubLoader,
								 RendererSubLoader<LightSource[], SerializedRenderer, EscapyAssociatedArray<EscapyLayer[]>> lightGroupSubLoader,
								 RendererSubLoader<EscapyVolumeLight, SerializedRenderer, Void> lightProcessorGroupSubLoader,
								 EscapyInstanceLoader<EscapyRenderer> rendererAttrInstLoader) {

		this.maskGroupSubLoader = maskGroupSubLoader;
		this.glBlenderGroupSubLoader = glBlenderGroupSubLoader;
		this.lightShaderGroupSubLoader = lightShaderGroupSubLoader;
		this.renderableGruopSubLoader = renderableGruopSubLoader;
		this.lightGroupSubLoader = lightGroupSubLoader;
		this.lightProcessorGroupSubLoader = lightProcessorGroupSubLoader;
		this.rendererAttrInstLoader = rendererAttrInstLoader;
	}



	@Override
	public EscapyRenderer loadRenderer(String path, EscapySubLocation arg) {

		final SerializedRenderer serialized;
		try {
			Reader reader = new InputStreamReader(Gdx.files.internal(path).read());
			serialized = new Gson().fromJson(reader, SerializedRenderer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		final Resolution resolution = new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		final EscapyAssociatedArray<EscapyRenderable> renderGroups = loadRenderGroups(arg.getLayerGroups(), serialized);
		final EscapyAssociatedArray<EscapyGLBlendRenderer> blenders = loadBlender(serialized);
		final EscapyAssociatedArray<EscapyMultiSourceShader> lightShaders = loadLightShaders(serialized);

		final EscapyAssociatedArray<EscapyVolumeLight> volumeProcessors = loadVolumeProcessors(serialized);
		final EscapyAssociatedArray<LightSource[]> lightSources = loadLightGroups(arg.getLayerGroups(), serialized);
		final EscapyAssociatedArray<LightMask> maskGroups = loadMaskGroups(serialized);


		EscapyRenderer escapyRenderer = new DefaultRenderer(
				serialized.name, renderGroups, maskGroups,
				lightSources, volumeProcessors, blenders,
				lightShaders, resolution
		);


		if (rendererAttrInstLoader != null)
			escapyRenderer = rendererAttrInstLoader.loadInstanceAttributes(escapyRenderer, serialized.attributes);

		return escapyRenderer;
	}





	private EscapyAssociatedArray<EscapyRenderable> loadRenderGroups(EscapyAssociatedArray<EscapyLayer[]> layerGroups,
																	 SerializedRenderer serialized) {
		return renderableGruopSubLoader.loadRendererPart(serialized, layerGroups);
	}


	private EscapyAssociatedArray<LightSource[]> loadLightGroups(EscapyAssociatedArray<EscapyLayer[]> layerGroups,
																 SerializedRenderer serialized) {
		return lightGroupSubLoader.loadRendererPart(serialized, layerGroups);
	}


	private EscapyAssociatedArray<EscapyMultiSourceShader> loadLightShaders(SerializedRenderer serialized) {
		return lightShaderGroupSubLoader.loadRendererPart(serialized);
	}


	private EscapyAssociatedArray<EscapyVolumeLight> loadVolumeProcessors(SerializedRenderer serialized) {
		return lightProcessorGroupSubLoader.loadRendererPart(serialized);
	}


	private EscapyAssociatedArray<LightMask> loadMaskGroups(SerializedRenderer serialized) {
		return maskGroupSubLoader.loadRendererPart(serialized);
	}


	private EscapyAssociatedArray<EscapyGLBlendRenderer> loadBlender(SerializedRenderer serialized) {
		return glBlenderGroupSubLoader.loadRendererPart(serialized);
	}




}