package net.irregular.escapy.engine.group.map.core.object;

import net.irregular.escapy.engine.env.utils.EscapyObject;

/**
 * @author Henry on 11/07/17.
 */
public interface GameObject extends EscapyObject {

	ObjectDetails getObjectDetails();
	GameObjectRenderer getGameObjectRenderer();

	default String getName() {
		return getObjectDetails().getName();
	}
	default void dispose() {
		getGameObjectRenderer().dispose();
	}
}
