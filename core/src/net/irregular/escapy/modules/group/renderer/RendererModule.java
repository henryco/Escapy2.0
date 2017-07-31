package net.irregular.escapy.modules.group.renderer;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.environment.utils.EscapyLogger;
import net.irregular.escapy.environment.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.group.render.loader.builder.RendererLoaderBuilder;
import net.irregular.escapy.group.render.loader.imp.DefaultRendererLoader;
import net.irregular.escapy.modules.group.renderer.dep.LightShaderLoader;
import net.irregular.escapy.modules.group.util.CameraModule;

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
					.setLightShaderInstanceLoader(lightShaderLoader)
			.build();
		} catch (Exception e) {
			new EscapyLogger("RenderLoaderProvider").fileJava().log(e, true);
			return null;
		}
	}



	@Provides @Singleton
	protected EscapyInstanceLoader<EscapyMultiSourceShader> provideLightShaderLoader() {
		return new LightShaderLoader();
	}

}