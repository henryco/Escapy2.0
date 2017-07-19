package net.irregular.escapy.engine.map.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.EscapySerialized;

import java.util.List;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedLocation implements EscapySerialized {

	public static final String VALID_TYPE = "Location";

	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;
	@SerializedName("enter") @Expose public String enter;
	@SerializedName("subLocations") @Expose public List<SerializedSubLocationUnit> subLocations = null;
	@SerializedName("attributes") @Expose public List<String> attributes = null;



	public static final class SerializedSubLocationUnit implements EscapySerialized {
		@SerializedName("name") @Expose public String name;
		@SerializedName("path") @Expose public String path;
		@Override public String getName() {
			return name;
		}
	}



	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValidType() {
		return VALID_TYPE;
	}

	@Override
	public String getType() {
		return type;
	}
}
