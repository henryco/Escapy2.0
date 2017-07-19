package net.irregular.escapy.engine.map.loader;

import net.irregular.escapy.engine.map.core.location.EscapyLocation;

/**
 * @author Henry on 12/07/17.
 */
public interface LocationLoader extends LoaderUtils {

	EscapyLocation loadLocation(String path);
}
