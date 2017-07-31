package net.irregular.escapy.group.map.core.layer;

import net.irregular.escapy.env.utils.EscapyObject;
import net.irregular.escapy.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.group.map.core.object.EscapyGameObject;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapyLayer extends EscapyObject {

	EscapyAssociatedArray<EscapyGameObject> getGameObjects();

	LayerShift getLayerShifter();
	float getAxisZ();
}
