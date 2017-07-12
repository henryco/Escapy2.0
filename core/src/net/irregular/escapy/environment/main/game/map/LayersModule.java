package net.irregular.escapy.environment.main.game.map;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.map.layer.Layer;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 12/07/17.
 */
@Module(library = true)
public class LayersModule {


	@Provides @Singleton @Named("mockLayers")
	public Collection<Layer> mockLayers() {

		Collection<Layer> layers = new LinkedList<>();

		return layers;
	}

}