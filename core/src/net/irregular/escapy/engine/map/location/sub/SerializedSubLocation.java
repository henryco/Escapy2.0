package net.irregular.escapy.engine.map.location.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedSubLocation {

	public static final String VALID_TYPE = "SubLocation";

	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;
	@SerializedName("name") @Expose public String location;
}
