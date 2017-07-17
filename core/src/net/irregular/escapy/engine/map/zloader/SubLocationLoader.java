package net.irregular.escapy.engine.map.zloader;

import net.irregular.escapy.engine.map.location.EscapySubLocation;

/**
 * @author Henry on 12/07/17.
 */
public interface SubLocationLoader extends LoaderUtils {

	EscapySubLocation loadSubLocation(String path);


}
