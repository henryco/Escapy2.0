package net.irregular.escapy.engine.map.zloader;

import net.irregular.escapy.engine.map.location.Location;

/**
 * @author Henry on 12/07/17.
 */
public interface LocationLoader extends LoaderUtils {

	Location loadLocation(String path);
}
