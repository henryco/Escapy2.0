package net.irregular.escapy.engine.group.container;


import net.irregular.escapy.engine.group.map.core.location.EscapyLocation;

import java.util.Collection;

/**
 * @author Henry on 28/07/17.
 */
public interface EscapyLocationContainer {

	EscapyLocation switchLocation(String name);
	EscapyLocation getLocation();

	Collection<String> getLocations();
}
