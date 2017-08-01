package net.irregular.escapy.group.map.core.location;

import net.irregular.escapy.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.utils.EscapyObject;
import net.irregular.escapy.utils.array.EscapyAssociatedArray;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapySubLocation extends EscapyObject {

	EscapyLocation getParentLocation();
	void setParentLocation(EscapyLocation parentLocation);

	EscapyAssociatedArray<EscapyLayer> getLayers();
	EscapyAssociatedArray<EscapyLayer[]> getLayerGroups();
}
