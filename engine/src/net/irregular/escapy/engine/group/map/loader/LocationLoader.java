package net.irregular.escapy.engine.group.map.loader;

import net.irregular.escapy.engine.env.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.engine.group.map.core.location.EscapyLocation;

/**
 * @author Henry on 12/07/17.
 */
public interface LocationLoader extends EscapyLoaderUtils {

	EscapyLocation loadLocation(String path);
}
