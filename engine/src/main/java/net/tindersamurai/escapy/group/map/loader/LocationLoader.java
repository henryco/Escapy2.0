package net.tindersamurai.escapy.group.map.loader;

import net.tindersamurai.escapy.group.map.core.location.EscapyLocation;
import net.tindersamurai.escapy.utils.loader.EscapyLoaderUtils;

/**
 * @author Henry on 12/07/17.
 */
public interface LocationLoader extends EscapyLoaderUtils {

	EscapyLocation loadLocation(String path);
}
