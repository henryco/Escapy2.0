package net.irregular.escapy.group.container;


import net.irregular.escapy.group.map.core.location.EscapyLocation;

import java.util.Collection;

/**
 * @author Henry on 28/07/17.
 */
public interface EscapyLocationContainer {

	EscapyLocation switchLocation(String name);
	EscapyLocation getLocation();

	Collection<String> getLocations();
}
