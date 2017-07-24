package net.irregular.escapy.environment.main.group.renderer;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;
import net.irregular.escapy.engine.group.render.loader.imp.DefaultRendererLoader;
import net.irregular.escapy.environment.main.group.renderer.dep.LightShaderLoader;
import net.irregular.escapy.environment.main.group.util.CameraModule;

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
	public RendererLoader<EscapySubLocation> provideRendererLoader(
			@Named("default_camera") EscapyCamera camera,
			EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderLoader) {

		return new DefaultRendererLoader(lightShaderLoader, camera);
	}



	@Provides @Singleton
	protected EscapyInstanceLoader<EscapyMultiSourceShader> provideLightShaderLoader() {
		return new LightShaderLoader();
	}

}