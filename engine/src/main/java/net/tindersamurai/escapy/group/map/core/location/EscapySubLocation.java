package net.tindersamurai.escapy.group.map.core.location;

import net.tindersamurai.escapy.group.map.core.layer.EscapyLayer;
import net.tindersamurai.escapy.utils.EscapyObject;
import net.tindersamurai.escapy.utils.array.EscapyAssociatedArray;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapySubLocation extends EscapyObject {

	EscapyLocation getParentLocation();
	void setParentLocation(EscapyLocation parentLocation);

	EscapyAssociatedArray<EscapyLayer> getLayers();
	EscapyAssociatedArray<EscapyLayer[]> getLayerGroups();
}
