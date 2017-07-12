package net.irregular.escapy.environment.main.game.map;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.map.location.Location;
import net.irregular.escapy.engine.map.location.SubLocation;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 12/07/17.
 */
@Module(library = true, includes = {SubLocationsModule.class})
public class LocationsModule {


	@Provides @Singleton @Named("mockLocations")
	public Collection<Location> provideMockLocations(

			@Named("mockActualLocation") String mockName,
			@Named("mockActualSubLocation") String actualMockName,
			@Named("mockSubLocations") Collection<SubLocation> subLocations

	) {

		Location location = new Location(mockName, subLocations);
		location.switchSubLocation(actualMockName);

		Collection<Location> locations = new LinkedList<>();
		locations.add(location);

		return locations;
	}


	@Provides @Named("mockActualLocation")
	public String provideMockActual() {
		return "testActual";
	}

}