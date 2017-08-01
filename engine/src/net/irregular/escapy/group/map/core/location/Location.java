package net.irregular.escapy.group.map.core.location;

import net.irregular.escapy.environment.utils.array.EscapyAssociatedArray;
import net.irregular.escapy.group.map.loader.SubLocationLoader;

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


	public Location(String name,
					Collection<Map.Entry<String, String>> subLocations,
					SubLocationLoader subLocationLoader) {

		this.subLocationLoader = subLocationLoader;
		this.subLocationMap = new HashMap<>();
		this.name = name;
		last = null;
		actual = null;

		for (Map.Entry<String, String> location: subLocations) {
			subLocationMap.put(location.getKey(), location.getValue());
		}
	}



	@Override
	public EscapySubLocation switchSubLocation(String location) {

		if (!subLocationMap.containsKey(location)) return actual;
		if (last != null && location.equals(last.getName())) {
			final EscapySubLocation local = actual;
			actual = last;
			last = local;
			return actual;
		}

		if (last != null)
			last.dispose();

		last = actual;
		actual = subLocationLoader.loadSubLocation(subLocationMap.get(location));
		actual.setParentLocation(this);
		if (actual == null) {
			actual = last;
			return null;
		}

		return actual;
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
	public String getName() {
		return name;
	}

	@Override
	public void dispose() {
		if (last != null) last.dispose();
		if (actual != null) actual.dispose();
	}
}
