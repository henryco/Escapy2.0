package net.irregular.escapy.engine.map.location;

import com.badlogic.gdx.utils.Disposable;
import net.irregular.escapy.engine.env.utils.Named;

import java.util.*;

/**
 * @author Henry on 11/07/17.
 */
public class Location implements Named, Disposable {

	private final String name;
	private final SubLocationLoader subLocationLoader;
	private final Map<String, String> subLocationMap;

	private SubLocation actual;
	private SubLocation last;



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



	public void switchSubLocation(String location) {

		if (!subLocationMap.containsKey(location)) return;
		if (last != null && location.equals(last.getName())) {
			final SubLocation local = actual;
			actual = last;
			last = local;
			return;
		}

		if (last != null)
			last.dispose();

		last = actual;
		actual = subLocationLoader.loadSubLocation(subLocationMap.get(location));
	}


	public SubLocation getSublocation() {
		return actual;
	}
	public List<Map.Entry<String, String>> getSublocations() {
		List<Map.Entry<String, String>> list = new LinkedList<>();
		list.addAll(subLocationMap.entrySet());
		return list;
	}
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
