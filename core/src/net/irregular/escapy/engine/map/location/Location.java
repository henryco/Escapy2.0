package net.irregular.escapy.engine.map.location;

import net.irregular.escapy.engine.env.utils.Named;

import java.util.*;

/**
 * @author Henry on 11/07/17.
 */
public class Location implements Named {

	private final String name;
	private final Map<String, SubLocation> subLocationMap;

	private SubLocation actual;
	private SubLocation last;



	private Location(String name) {
		this.name = name;
		this.subLocationMap = new HashMap<>();
		this.actual = null;
		this.last = null;
	}

	public Location(String name, SubLocation ... subLocations) {
		this(name);
		for (SubLocation location: subLocations) addSubLocation(location);
	}

	public Location(String name, Collection<SubLocation> subLocations) {
		this(name, subLocations.toArray(new SubLocation[0]));
	}



	public void switchSubLocation(String location) {

		if (last != null && location.equals(last.getName())) {
			final SubLocation local = actual;
			actual = last;
			last = local;
			return;
		}

		last = actual;
		actual = subLocationMap.get(location);
	}


	public void addSubLocation(SubLocation location) {
		subLocationMap.put(location.getName(), location);
	}

	public SubLocation getSublocation() {
		return actual;
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
