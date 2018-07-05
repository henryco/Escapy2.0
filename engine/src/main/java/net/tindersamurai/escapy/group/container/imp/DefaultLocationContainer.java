package net.tindersamurai.escapy.group.container.imp;

import net.tindersamurai.escapy.group.container.EscapyLocationContainer;
import net.tindersamurai.escapy.group.map.core.location.EscapyLocation;
import net.tindersamurai.escapy.group.map.loader.LocationLoader;
import net.tindersamurai.escapy.utils.EscapyLogger;
import net.tindersamurai.escapy.utils.proxy.EscapyProxyInstanceObserver;
import net.tindersamurai.escapy.utils.proxy.EscapyProxyListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Henry on 11/07/17.
 */
public class DefaultLocationContainer implements EscapyLocationContainer {


	private final EscapyProxyInstanceObserver locationInstanceObserver;
	private final Map<String, String> locationMap;
	private final LocationLoader locationLoader;

	private EscapyLocation location;


	protected DefaultLocationContainer(LocationLoader locationLoader,
									Collection<Map.Entry<String, String>> locations) {

		this.locationInstanceObserver = new EscapyProxyInstanceObserver();
		this.locationLoader = locationLoader;
		this.locationMap = new HashMap<>();
		this.location = null;

		for (Map.Entry<String, String> l: locations)
			locationMap.put(l.getKey(), l.getValue());
	}

	protected DefaultLocationContainer(LocationLoader locationLoader,
									Collection<Map.Entry<String, String>> locations,
									EscapyProxyListener... listeners) {
		this(locationLoader, locations);
		addLocationProxyListeners(listeners);
	}



	@Override
	public EscapyLocation switchLocation(String name) {

		try {

			EscapyLocation temp = location;
			EscapyLocation preLocation = locationLoader.loadLocation(locationMap.get(name));

			location = locationInstanceObserver.create(preLocation);

			if (location == null) {
				location = temp;
				return location;
			}

			if (temp != null) temp.dispose();
			return location;

		} catch (Exception e) {
			new EscapyLogger("LocationContainer").fileJava().log(e, true);
		}

		return null;
	}

	public void switchLocation(String locationName, String subLocationName) {
		switchLocation(locationName).switchSubLocation(subLocationName);
	}


	public void addLocationProxyListeners(EscapyProxyListener... listeners) {
		locationInstanceObserver.addProxyListeners(listeners);
	}

	public void removeLocationProxyListeners(EscapyProxyListener... listeners) {
		locationInstanceObserver.removeProxyListeners(listeners);
	}


	@Override
	public Collection<String> getLocations() {
		Collection<String> collection = new LinkedList<>();
		locationMap.forEach((key, value) -> collection.add(key));
		return collection;
	}

	@Override
	public EscapyLocation getLocation() {
		return location;
	}

}
