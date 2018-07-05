package net.tindersamurai.escapy.deprecated.modules.group.location;

import dagger.Module;
import dagger.Provides;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.group.map.core.layer.shift.LayerShift;
import net.tindersamurai.escapy.group.map.core.object.EscapyGameObject;
import net.tindersamurai.escapy.group.map.loader.builder.LocationLoaderBuilder;
import net.tindersamurai.escapy.group.map.loader.imp.DefaultLocationLoader;
import net.tindersamurai.escapy.deprecated.modules.group.location.dep.GameObjAttrInstLoader;
import net.tindersamurai.escapy.deprecated.modules.group.location.dep.SimpleShiftAttrInstLoader;
import net.tindersamurai.escapy.deprecated.modules.group.util.CameraModule;
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
public class LocationsModule {



	@Provides @Singleton
	public DefaultLocationLoader provideLocationLoader(
			EscapyInstanceLoader<LayerShift> shiftLogic,
			EscapyInstanceLoader<EscapyGameObject> gameObjectInstanceAttributeLoader) {

		try {
			return LocationLoaderBuilder.Default()
					.setSubLocationLayerShiftAttributeLoader(shiftLogic)
					.setGameObjectAttributeLoader(gameObjectInstanceAttributeLoader)
			.build();
		} catch (Exception e) {
			new EscapyLogger("LocationLoaderProvider").fileJava().log(e, true);
			return null;
		}
	}



	@Provides @Singleton
	protected EscapyInstanceLoader<LayerShift> provideLayerShiftLogic(
			@Named("default_camera") EscapyCamera camera) {
		return new SimpleShiftAttrInstLoader(camera);
	}


	@Provides @Singleton
	protected EscapyInstanceLoader<EscapyGameObject> provideGameObjInstanceAttrLoader() {
		return new GameObjAttrInstLoader();
	}

}