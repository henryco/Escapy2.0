package net.irregular.escapy.engine.map.layer;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.map.layer.shift.LayerShift;
import net.irregular.escapy.engine.map.object.GameObject;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapyLayer extends EscapyObject {

	EscapyAssociatedArray<GameObject> getGameObjects();

	LayerShift getLayerShifter();
	float getAxisZ();
}
