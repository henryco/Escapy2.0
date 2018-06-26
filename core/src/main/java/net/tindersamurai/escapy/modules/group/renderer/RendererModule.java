package net.tindersamurai.escapy.modules.group.renderer;

import dagger.Module;
import dagger.Provides;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.tindersamurai.escapy.group.render.loader.builder.RendererLoaderBuilder;
import net.tindersamurai.escapy.group.render.loader.imp.DefaultRendererLoader;
import net.tindersamurai.escapy.modules.group.renderer.dep.LightShaderAttrLoader;
import net.tindersamurai.escapy.modules.group.util.CameraModule;
import net.tindersamurai.escapy.utils.EscapyLogger;
import net.tindersamurai.escapy.utils.loader.EscapyInstanceLoader;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 21/07/17.
 */
@Module(library = true, includes = {
		CameraModule.class
})
public class RendererModule {



	@Provides @Singleton
	public DefaultRendererLoader provideRendererLoader(
			@Named("default_camera") EscapyCamera camera,
			EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderLoader) {

		try {
			return RendererLoaderBuilder.Default()
					.setCamera(camera)
					.setLightShaderAttributeLoader(lightShaderLoader)
			.build();
		} catch (Exception e) {
			new EscapyLogger("RenderLoaderProvider").fileJava().log(e, true);
			return null;
		}
	}



	@Provides @Singleton
	protected EscapyInstanceLoader<EscapyMultiSourceShader> provideLightShaderLoader() {
		return new LightShaderAttrLoader();
	}

}