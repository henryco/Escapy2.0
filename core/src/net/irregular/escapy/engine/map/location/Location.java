package net.irregular.escapy.engine.map.location;

import com.badlogic.gdx.utils.Disposable;
import net.irregular.escapy.engine.env.utils.Named;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Henry on 11/07/17.
 */
public class Location implements Named, Disposable {

	private final String name;
	private final List<String> subLocationList;
	private final SubLocationLoader subLocationLoader;

	private SubLocation actual;
	private SubLocation last;



	private Location(String name,
					 SubLocationLoader subLocationLoader) {
		this.name = name;
		this.subLocationList = new ArrayList<>();
		this.actual = null;
		this.last = null;
		this.subLocationLoader = subLocationLoader;
	}

	public Location(String name,
					Collection<String> subLocations,
					SubLocationLoader subLocationLoader) {
		this(name, subLocationLoader);
		for (String location: subLocations) addSubLocation(location);
	}



	public void switchSubLocation(String location) {

		if (!subLocationList.contains(location)) return;
		if (last != null && location.equals(last.getName())) {
			final SubLocation local = actual;
			actual = last;
			last = local;
			return;
		}

		if (last != null)
			last.dispose();

		last = actual;
		actual = subLocationLoader.loadSubLocation(location);
	}


	public SubLocation getSublocation() {
		return actual;
	}
	public List<String> getSublocations() {
		return subLocationList;
	}
	public void addSubLocation(String location) {
		subLocationList.add(location);
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
