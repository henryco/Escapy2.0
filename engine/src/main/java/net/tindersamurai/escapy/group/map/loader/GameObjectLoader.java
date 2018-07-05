package net.tindersamurai.escapy.group.map.loader;

import net.tindersamurai.escapy.group.map.core.object.EscapyGameObject;
import net.tindersamurai.escapy.utils.loader.EscapyLoaderUtils;
import net.tindersamurai.escapy.utils.serial.EscapySerialized;

/**
 * @author Henry on 14/07/17.
 */
public interface GameObjectLoader<T extends EscapySerialized> extends EscapyLoaderUtils {

	EscapyGameObject loadGameObject(String path, T serialized);

}