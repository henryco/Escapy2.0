package net.tindersamurai.escapy.group.map.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.tindersamurai.escapy.utils.serial.EscapySimpleSerialized;

import java.util.List;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedLocation extends EscapySimpleSerialized {

	public static final String VALID_TYPE = "Location";
	@Override public String getValidType() {
		return VALID_TYPE;
	}

	@SerializedName("subLocations") @Expose public List<SerializedSubLocationUnit> subLocations = null;


	public static final class SerializedSubLocationUnit extends EscapySimpleSerialized {
		@SerializedName("path") @Expose public String path;
	}

}
