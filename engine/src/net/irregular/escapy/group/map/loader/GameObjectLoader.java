package net.irregular.escapy.group.map.loader;

import net.irregular.escapy.env.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.env.utils.serial.EscapySerialized;
import net.irregular.escapy.group.map.core.object.EscapyGameObject;

/**
 * @author Henry on 14/07/17.
 */
public interface GameObjectLoader<T extends EscapySerialized> extends EscapyLoaderUtils {

	EscapyGameObject loadGameObject(String path, T serialized);

}