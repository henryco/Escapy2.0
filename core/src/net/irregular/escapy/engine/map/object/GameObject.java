package net.irregular.escapy.engine.map.object;

import com.badlogic.gdx.utils.Disposable;
import net.irregular.escapy.engine.env.utils.Named;

/**
 * @author Henry on 11/07/17.
 */
public interface GameObject extends Named, Disposable {

	ObjectDetails getObjectDetails();
	GameObjectRenderer getGameObjectRenderer();

	default String getName() {
		return getObjectDetails().getName();
	}
	default void dispose() {
		getGameObjectRenderer().dispose();
	}
}
