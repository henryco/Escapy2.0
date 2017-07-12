package net.irregular.escapy.engine.map;

import net.irregular.escapy.engine.map.location.Location;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 11/07/17.
 */
public class MapContainer {

	private Map<String, Location> locationMap;
	private Location location;


	public MapContainer() {
		locationMap = new HashMap<>();
		location = null;
	}

	public MapContainer(Collection<Location> locations) {
		this();
		setLocations(locations);
	}

	public MapContainer(Location... locations) {
		this();
		setLocations(locations);
	}


	public void switchLocation(String name) {
		this.location = locationMap.get(name);
	}

	public Location getLogation() {
		return location;
	}

	public void setLocations(Location... locations) {
		for (Location l : locations) addLocation(l);
	}

	public void setLocations(Collection<Location> locations) {
		for (Location l: locations) addLocation(l);
	}

	public void addLocation(Location location) {
		locationMap.put(location.getName(), location);
	}

}
