package net.irregular.escapy.environment.main.game.map;

import net.irregular.escapy.engine.map.location.Location;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 12/07/17.
 */
//@Module(library = true)
public class LocationsModule {


//	@Provides @Singleton @Named("mockLocations")
	public Collection<Location> provideMockLocations(
			SubLocationLoader subLocationLoader
	) {

		Collection<Location> locations = new LinkedList<>();


		return locations;
	}



}