package net.irregular.escapy.environment.main.game.map;

import net.irregular.escapy.engine.map.MapContainer;
import net.irregular.escapy.engine.map.core.location.Location;

import javax.inject.Named;
import java.util.Collection;

/**
 * @author Henry on 12/07/17.
 */
//@Module(library = true, includes = {LocationsModule.class})
public class ContainerModule {


//	@Provides @Singleton
	public MapContainer mapContainer(
			@Named("mockLocations") Collection<Location> locations) {

		return null;
	}

}