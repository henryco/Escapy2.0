package net.irregular.escapy.engine.group.container.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySimpleSerialized;

import java.util.List;

/**
 * @author Henry on 21/07/17.
 */
public final class SerializedGroup extends EscapySimpleSerialized {

	@Override public String getValidType() {
		return "GameConfiguration";
	}

	@SerializedName("locations") @Expose public List<SerializedPath> locations = null;
	@SerializedName("renderers") @Expose public List<SerializedPath> renderers = null;

	public static final class SerializedPath extends EscapySimpleSerialized {
		@SerializedName("path") @Expose public String path;
	}
}
