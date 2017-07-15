package net.irregular.escapy.engine.map.zloader;

import net.irregular.escapy.engine.env.utils.EscapySerialized;
import net.irregular.escapy.engine.map.object.GameObject;

/**
 * @author Henry on 14/07/17.
 */
public interface GameObjectLoader<T extends EscapySerialized> extends LoaderUtils {

	GameObject loadGameObject(T serialized);

}