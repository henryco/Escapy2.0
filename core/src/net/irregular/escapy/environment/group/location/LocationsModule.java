package net.irregular.escapy.environment.group.location;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.env.utils.EscapyLogger;
import net.irregular.escapy.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.environment.group.location.dep.GameObjAttrInstLoader;
import net.irregular.escapy.environment.group.location.dep.SimpleShiftLogic;
import net.irregular.escapy.environment.group.util.CameraModule;
import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.group.map.core.layer.shift.LayerShiftLogic;
import net.irregular.escapy.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.group.map.loader.builder.LocationLoaderBuilder;
import net.irregular.escapy.group.map.loader.imp.DefaultLocationLoader;

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
			EscapyInstanceLoader<LayerShiftLogic> shiftLogic,
			EscapyInstanceLoader<EscapyGameObject> gameObjectInstanceAttributeLoader) {

		try {
			return LocationLoaderBuilder.Default()
					.setSubLocationLayerShiftLogicInstanceLoader(shiftLogic)
					.setGameObjectInstanceAttributeLoader(gameObjectInstanceAttributeLoader)
			.build();
		} catch (Exception e) {
			new EscapyLogger("LocationLoaderProvider").fileJava().log(e, true);
			return null;
		}
	}



	@Provides @Singleton
	protected EscapyInstanceLoader<LayerShiftLogic> provideLayerShiftLogic(
			@Named("default_camera") EscapyCamera camera) {
		return new SimpleShiftLogic(camera);
	}


	@Provides @Singleton
	protected EscapyInstanceLoader<EscapyGameObject> provideGameObjInstanceAttrLoader() {
		return new GameObjAttrInstLoader();
	}

}