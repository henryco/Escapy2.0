package net.irregular.escapy.engine.map.location;

import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;

import java.util.*;

/**
 * @author Henry on 11/07/17.
 */
public class Location implements EscapyLocation {

	public final String name;
	public final SubLocationLoader subLocationLoader;
	public final Map<String, String> subLocationMap;

	private EscapySubLocation actual;
	private EscapySubLocation last;



	private Location(String name,
					 SubLocationLoader subLocationLoader) {
		this.name = name;
		this.subLocationMap = new HashMap<>();
		this.actual = null;
		this.last = null;
		this.subLocationLoader = subLocationLoader;
	}

	public Location(String name,
					Collection<Map.Entry<String, String>> subLocations,
					SubLocationLoader subLocationLoader) {
		this(name, subLocationLoader);
		for (Map.Entry<String, String> location: subLocations)
			addSubLocation(location.getKey(), location.getValue());
	}



	@Override
	public void switchSubLocation(String location) {

		if (!subLocationMap.containsKey(location)) return;
		if (last != null && location.equals(last.getName())) {
			final EscapySubLocation local = actual;
			actual = last;
			last = local;
			return;
		}

		if (last != null)
			last.dispose();

		last = actual;
		actual = subLocationLoader.loadSubLocation(subLocationMap.get(location));
		if (actual == null) {
			actual = last;
		}
	}


	@Override
	public EscapySubLocation getSubLocation() {
		return actual;
	}

	@Override
	public Collection<EscapyAssociatedArray.Entry<String>> getSubLocations() {
		List<EscapyAssociatedArray.Entry<String>> list = new LinkedList<>();
		subLocationMap.forEach((key, value) -> list.add(new EscapyAssociatedArray.Entry<>(key, value)));
		return list;
	}

	@Override
	public void addSubLocation(String locationName, String locationPath) {
		subLocationMap.put(locationName, locationPath);
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void dispose() {
		if (last != null) last.dispose();
		if (actual != null) actual.dispose();
	}
}
