package net.irregular.escapy.engine.map.loader;

import net.irregular.escapy.engine.env.utils.EscapySerialized;
import net.irregular.escapy.engine.map.core.object.GameObject;

/**
 * @author Henry on 14/07/17.
 */
public interface GameObjectLoader<T extends EscapySerialized> extends LoaderUtils {

	GameObject loadGameObject(T serialized);

}