package net.irregular.escapy.group.map.loader.builder;

import net.irregular.escapy.group.map.loader.LocationLoader;

/**
 * @author Henry on 15/07/17.
 */
public interface LocationLoaderBuilder {

	LocationLoader build();

	static DefaultLocationLoaderBuilder Default() {
		return new DefaultLocationLoaderBuilder();
	}
}
