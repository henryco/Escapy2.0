package net.irregular.escapy.engine.group.map.loader;

import net.irregular.escapy.engine.env.utils.EscapySerialized;
import net.irregular.escapy.engine.group.map.core.object.GameObject;

/**
 * @author Henry on 14/07/17.
 */
public interface GameObjectLoader<T extends EscapySerialized> extends LoaderUtils {

	GameObject loadGameObject(T serialized);

}