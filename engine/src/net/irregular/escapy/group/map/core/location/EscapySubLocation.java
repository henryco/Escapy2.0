package net.irregular.escapy.group.map.core.location;

import net.irregular.escapy.environment.utils.EscapyObject;
import net.irregular.escapy.environment.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.group.map.core.layer.EscapyLayer;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapySubLocation extends EscapyObject {

	EscapyLocation getParentLocation();
	void setParentLocation(EscapyLocation parentLocation);

	EscapyAssociatedArray<EscapyLayer> getLayers();
	EscapyAssociatedArray<EscapyLayer[]> getLayerGroups();
}
