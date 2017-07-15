package net.irregular.escapy.engine.map;

import net.irregular.escapy.engine.map.location.Location;
import net.irregular.escapy.engine.map.zloader.LocationLoader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 11/07/17.
 */
public class MapContainer {

	private final Map<String, String> locationMap;
	private final LocationLoader locationLoader;
	private Location location;


	public MapContainer(LocationLoader locationLoader) {
		this.locationLoader = locationLoader;
		locationMap = new HashMap<>();
		location = null;
	}

	public MapContainer(LocationLoader locationLoader,
						Collection<Map.Entry<String, String>> locations) {
		this(locationLoader);
		setLocations(locations);
	}

	public void switchLocation(String name) {

		Location temp = location;
		location = locationLoader.loadLocation(locationMap.get(name));

		if (location == null) {
			location = temp;
			return;
		}

		if (temp != null) temp.dispose();
	}

	public Location getLogation() {
		return location;
	}

	public void setLocations(Collection<Map.Entry<String, String>> locations) {
		for (Map.Entry<String, String> l: locations)
			addLocation(l.getKey(), l.getValue());
	}

	public void addLocation(String locationName, String locationPath) {
		locationMap.put(locationName, locationPath);
	}

}
