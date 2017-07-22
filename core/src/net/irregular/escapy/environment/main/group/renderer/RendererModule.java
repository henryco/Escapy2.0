package net.irregular.escapy.environment.main.group.renderer;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;
import net.irregular.escapy.engine.group.render.loader.imp.DefaultRendererLoader;
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
			@Named("default_camera") EscapyCamera camera) {
		return new DefaultRendererLoader(camera);
	}

}
