package net.irregular.escapy.engine.map.core.layer;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.map.core.layer.shift.LayerShift;
import net.irregular.escapy.engine.map.core.object.GameObject;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapyLayer extends EscapyObject {

	EscapyAssociatedArray<GameObject> getGameObjects();

	LayerShift getLayerShifter();
	float getAxisZ();
}
