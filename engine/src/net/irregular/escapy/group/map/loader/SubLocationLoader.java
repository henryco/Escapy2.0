package net.irregular.escapy.group.map.loader;

import net.irregular.escapy.env.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.group.map.core.location.EscapySubLocation;

/**
 * @author Henry on 12/07/17.
 */
public interface SubLocationLoader extends EscapyLoaderUtils {

	EscapySubLocation loadSubLocation(String path);


}
