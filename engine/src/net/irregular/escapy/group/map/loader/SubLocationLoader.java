package net.irregular.escapy.group.map.loader;

import net.irregular.escapy.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.utils.loader.EscapyLoaderUtils;

/**
 * @author Henry on 12/07/17.
 */
public interface SubLocationLoader extends EscapyLoaderUtils {

	EscapySubLocation loadSubLocation(String path);


}
