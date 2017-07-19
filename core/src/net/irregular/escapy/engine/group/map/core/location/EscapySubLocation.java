package net.irregular.escapy.engine.group.map.core.location;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.group.map.core.layer.EscapyLayer;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapySubLocation extends EscapyObject {

	EscapyAssociatedArray<EscapyLayer> getLayers();
	EscapyAssociatedArray<EscapyLayer[]> getLayerGroups();
}
