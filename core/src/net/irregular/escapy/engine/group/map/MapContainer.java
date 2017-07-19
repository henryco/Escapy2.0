package net.irregular.escapy.engine.group.map;

import net.irregular.escapy.engine.env.utils.proxy.EscapyProxyInstanceObserver;
import net.irregular.escapy.engine.env.utils.proxy.EscapyProxyListener;
import net.irregular.escapy.engine.group.map.core.location.EscapyLocation;
import net.irregular.escapy.engine.group.map.loader.LocationLoader;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Henry on 11/07/17.
 */
public class MapContainer {


	private final EscapyProxyInstanceObserver locationInstanceObserver;
	private final Map<String, String> locationMap;
	private final LocationLoader locationLoader;

	private EscapyLocation location;


	public MapContainer(LocationLoader locationLoader,
						Collection<Map.Entry<String, String>> locations) {

		this.locationInstanceObserver = new EscapyProxyInstanceObserver();
		this.locationLoader = locationLoader;
		this.locationMap = new HashMap<>();
		this.location = null;

		for (Map.Entry<String, String> l: locations)
			locationMap.put(l.getKey(), l.getValue());
	}

	public MapContainer(LocationLoader locationLoader,
						Collection<Map.Entry<String, String>> locations,
						EscapyProxyListener... listeners) {
		this(locationLoader, locations);
		addLocationProxyListeners(listeners);
	}




	public EscapyLocation switchLocation(String name) {

		EscapyLocation temp = location;
		EscapyLocation preLocation = locationLoader.loadLocation(locationMap.get(name));

		location = locationInstanceObserver.proxyObservedInstance(preLocation);

		if (location == null) {
			location = temp;
			return location;
		}

		if (temp != null) temp.dispose();
		return location;
	}




	public void addLocationProxyListeners(EscapyProxyListener... listeners) {
		locationInstanceObserver.addProxyListeners(listeners);
	}

	public void removeLocationProxyListeners(EscapyProxyListener... listeners) {
		locationInstanceObserver.removeProxyListeners(listeners);
	}

	public Collection<String> getLocations() {
		Collection<String> collection = new LinkedList<>();
		locationMap.forEach((key, value) -> collection.add(key));
		return collection;
	}

	public EscapyLocation getLogation() {
		return location;
	}

}
