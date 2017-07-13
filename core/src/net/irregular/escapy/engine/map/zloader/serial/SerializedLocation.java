package net.irregular.escapy.engine.map.zloader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedLocation {

	public static final String VALID_TYPE = "Location";

	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;
	@SerializedName("enter") @Expose public String enter;
	@SerializedName("subLocationsPath") @Expose
	public List<String> subLocations = null;
}
