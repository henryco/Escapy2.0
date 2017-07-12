package net.irregular.escapy.map.object;

import net.irregular.escapy.engine.env.utils.Named;

/**
 * @author Henry on 11/07/17.
 */
public interface GameObject extends Named {

	ObjectDetails getObjectDetails();
	GameObjectRenderer getGameObjectRenderer();

	default String getName() {
		return getObjectDetails().getName();
	}
}
