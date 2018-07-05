package net.tindersamurai.escapy.group.map.core.location;

import net.tindersamurai.escapy.utils.EscapyObject;
import net.tindersamurai.escapy.utils.array.EscapyAssociatedArray;

import java.util.Collection;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapyLocation extends EscapyObject {

	EscapySubLocation switchSubLocation(String location);

	EscapySubLocation getSubLocation();

	Collection<EscapyAssociatedArray.Entry<String>> getSubLocations();

}
