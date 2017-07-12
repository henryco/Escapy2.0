package net.irregular.escapy.map.location;

import net.irregular.escapy.engine.env.utils.Named;

import java.util.*;

/**
 * @author Henry on 11/07/17.
 */
public class Location implements Named {

	private final String name;
	private final Map<String, SubLocation> subLocationMap;




	private Location(String name) {
		this.name = name;
		this.subLocationMap = new HashMap<>();
	}

	public Location(String name, SubLocation ... subLocations) {
		this(name);
		for (SubLocation location: subLocations) addSubLocation(location);
	}

	public Location(String name, Collection<SubLocation> subLocations) {
		this(name, subLocations.toArray(new SubLocation[0]));
	}






	public void addSubLocation(SubLocation location) {
		subLocationMap.put(location.getName(), location);
	}

	public SubLocation getSublocation(String name) {
		return subLocationMap.get(name);
	}

	public List<SubLocation> getSublocations() {
		List<SubLocation> locations = new ArrayList<>();
		for (Map.Entry<String, SubLocation> entry : subLocationMap.entrySet())
			locations.add(entry.getValue());
		return locations;
	}

	@Override
	public String getName() {
		return name;
	}


}
