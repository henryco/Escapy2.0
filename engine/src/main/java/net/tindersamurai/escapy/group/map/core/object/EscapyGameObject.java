package net.tindersamurai.escapy.group.map.core.object;

import net.tindersamurai.escapy.utils.EscapyObject;

/**
 * @author Henry on 11/07/17.
 */
public interface EscapyGameObject extends EscapyObject {

	ObjectDetails getObjectDetails();
	EscapyGameObjectRenderer getGameObjectRenderer();

	default String getName() {
		return getObjectDetails().getName();
	}
	default void dispose() {
		getGameObjectRenderer().dispose();
	}
}
