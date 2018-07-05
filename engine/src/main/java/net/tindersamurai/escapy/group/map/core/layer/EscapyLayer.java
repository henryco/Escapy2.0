package net.tindersamurai.escapy.group.map.core.layer;

import net.tindersamurai.escapy.group.map.core.layer.shift.LayerShift;
import net.tindersamurai.escapy.group.map.core.object.EscapyGameObject;
import net.tindersamurai.escapy.utils.EscapyObject;
import net.tindersamurai.escapy.utils.array.EscapyAssociatedArray;

/**
 * @author Henry on 17/07/17.
 */
public interface EscapyLayer extends EscapyObject {

	EscapyAssociatedArray<EscapyGameObject> getGameObjects();

	LayerShift getLayerShifter();
	float getAxisZ();
}
