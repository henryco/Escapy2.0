package net.irregular.escapy.engine.map;

import net.irregular.escapy.engine.map.location.EscapyLocation;
import net.irregular.escapy.engine.map.zloader.LocationLoader;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Henry on 11/07/17.
 */
public class MapContainer {

	private final Map<String, String> locationMap;
	private final LocationLoader locationLoader;
	private EscapyLocation location;


	public MapContainer(LocationLoader locationLoader) {
		this.locationLoader = locationLoader;
		this.locationMap = new HashMap<>();
		this.location = null;
	}

	public MapContainer(LocationLoader locationLoader,
						Collection<Map.Entry<String, String>> locations) {
		this(locationLoader);
		setLocations(locations);
	}

	public void switchLocation(String name) {

		EscapyLocation temp = location;
		location = locationLoader.loadLocation(locationMap.get(name));

		if (location == null) {
			location = temp;
			return;
		}

		if (temp != null) temp.dispose();
	}


	public EscapyLocation getLogation() {
		return location;
	}




	public Collection<String> getLocations() {
		Collection<String> collection = new LinkedList<>();
		locationMap.forEach((key, value) -> collection.add(key));
		return collection;
	}

	public void setLocations(Collection<Map.Entry<String, String>> locations) {
		for (Map.Entry<String, String> l: locations)
			addLocation(l.getKey(), l.getValue());
	}

	public void addLocation(String locationName, String locationPath) {
		locationMap.put(locationName, locationPath);
	}

}
