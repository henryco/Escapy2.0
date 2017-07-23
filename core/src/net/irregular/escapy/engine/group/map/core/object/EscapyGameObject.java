package net.irregular.escapy.engine.group.map.core.object;

import net.irregular.escapy.engine.env.utils.EscapyObject;

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
