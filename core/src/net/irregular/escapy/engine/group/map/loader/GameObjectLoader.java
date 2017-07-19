package net.irregular.escapy.engine.group.map.loader;

import net.irregular.escapy.engine.env.utils.EscapySerialized;
import net.irregular.escapy.engine.env.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.engine.group.map.core.object.GameObject;

/**
 * @author Henry on 14/07/17.
 */
public interface GameObjectLoader<T extends EscapySerialized> extends EscapyLoaderUtils {

	GameObject loadGameObject(T serialized);

}