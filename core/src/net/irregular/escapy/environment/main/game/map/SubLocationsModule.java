package net.irregular.escapy.environment.main.game.map;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.layer.LayerComparator;
import net.irregular.escapy.engine.map.location.SubLocation;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 12/07/17.
 */
@Module(library = true, includes = {LayersModule.class})
public class SubLocationsModule {


	@Provides @Singleton @Named("mockSubLocations")
	public Collection<SubLocation> mockSubLocations(

			@Named("mockActualSubLocation") String name,
			@Named("mockLayers") Collection<Layer> layers

	) {

		Collection<SubLocation> subLocations = new LinkedList<>();
		subLocations.add(new SubLocation(name, layers, new LayerComparator()));

		return subLocations;
	}


	@Provides @Named("mockActualSubLocation")
	public String actualSubLocation() {
		return "sub_zero";
	}
}