package net.irregular.escapy.group.map.loader;

import net.irregular.escapy.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.utils.serial.EscapySerialized;

/**
 * @author Henry on 14/07/17.
 */
public interface GameObjectLoader<T extends EscapySerialized> extends EscapyLoaderUtils {

	EscapyGameObject loadGameObject(String path, T serialized);

}