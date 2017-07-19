package net.irregular.escapy.engine.group.map.loader;

import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;

/**
 * @author Henry on 12/07/17.
 */
public interface SubLocationLoader extends LoaderUtils {

	EscapySubLocation loadSubLocation(String path);


}
