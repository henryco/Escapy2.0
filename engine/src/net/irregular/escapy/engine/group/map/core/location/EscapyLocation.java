package net.irregular.escapy.engine.group.map.core.location;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;

import java.util.Collection;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapyLocation extends EscapyObject {

	EscapySubLocation switchSubLocation(String location);

	EscapySubLocation getSubLocation();

	Collection<EscapyAssociatedArray.Entry<String>> getSubLocations();

}
