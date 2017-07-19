package net.irregular.escapy.engine.map.loader.builder;

import net.irregular.escapy.engine.map.loader.LocationLoader;

/**
 * @author Henry on 15/07/17.
 */
public interface LocationLoaderBuilder {

	LocationLoader build();

	static LocationLoaderBuilder Default() {
		return new DefaultLocationLoaderBuilder();
	}
}
