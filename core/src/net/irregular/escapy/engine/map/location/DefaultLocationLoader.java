package net.irregular.escapy.engine.map.location;

import net.irregular.escapy.engine.map.location.sub.SubLocationLoader;

/**
 * @author Henry on 13/07/17.
 */
public class DefaultLocationLoader implements LocationLoader {

	private final SubLocationLoader subLocationLoader;

	public DefaultLocationLoader(SubLocationLoader subLocationLoader) {
		this.subLocationLoader = subLocationLoader;
	}

	@Override
	public Location loadLocation(String name) {

		Location location = new Location("", null, subLocationLoader);

		return location;
	}
}
