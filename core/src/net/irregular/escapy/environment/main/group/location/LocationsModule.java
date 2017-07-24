package net.irregular.escapy.environment.main.group.location;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.group.map.core.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.engine.group.map.loader.LocationLoader;
import net.irregular.escapy.engine.group.map.loader.builder.DefaultLocationLoaderBuilder;
import net.irregular.escapy.engine.group.map.loader.builder.LocationLoaderBuilder;
import net.irregular.escapy.environment.main.group.location.dep.GameObjAttrInstLoader;
import net.irregular.escapy.environment.main.group.location.dep.SimpleShiftLogic;
import net.irregular.escapy.environment.main.group.util.CameraModule;

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
	public LocationLoader provideLocationLoader(
			EscapyInstanceLoader<LayerShiftLogic> shiftLogic,
			EscapyInstanceLoader<EscapyGameObject> gameObjectInstanceAttributeLoader) {

		DefaultLocationLoaderBuilder builder = LocationLoaderBuilder.Default();
		builder.setSubLocationLayerShiftLogicInstanceLoader(shiftLogic);
		builder.setGameObjectInstanceAttributeLoader(gameObjectInstanceAttributeLoader);
		return builder.build();
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