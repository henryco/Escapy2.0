package net.irregular.escapy.map;

import net.irregular.escapy.map.location.Location;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 11/07/17.
 */
public class MapContainer {

	private Map<String, Location> locationMap;
	private Location locationActual;
	private Location locationLast;



	public MapContainer() {
		locationMap = new HashMap<>();
		locationLast = null;
		locationActual = null;
	}

	public MapContainer(Collection<Location> locations) {
		this();
		setLocations(locations);
	}

	public MapContainer(Location... locations) {
		this();
		setLocations(locations);
	}


	public Location getLocationActual() {
		return locationActual;
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

	public void switchLocation(String location) {

		if (locationLast != null && location.equals(locationLast.getName())) {
			final Location local = locationActual;
			locationActual = locationLast;
			locationLast = local;
			return;
		}

		locationLast = locationActual;
		locationActual = locationMap.get(location);
	}



}
