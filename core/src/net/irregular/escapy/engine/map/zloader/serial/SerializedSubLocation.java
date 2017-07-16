package net.irregular.escapy.engine.map.zloader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.EscapySerialized;

import java.util.List;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedSubLocation implements EscapySerialized {

	public static final String VALID_TYPE = "SubLocation";


	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;
	@SerializedName("location") @Expose public String location;
	@SerializedName("layers") @Expose public List<SerializedLayer> layers = null;
	@SerializedName("renderContainer") @Expose public List<SerializedRenderContainer> renderContainers = null;


	public static final class SerializedLayer implements EscapySerialized {
		@SerializedName("name") @Expose public String name;
		@SerializedName("axis_z") @Expose public float axisZ;
		@SerializedName("shift") @Expose public SerializedShift shift;
		@SerializedName("objects") @Expose public List<SerializedGameObject> objects = null;

		@Override public String getName() {
			return name;
		}
	}


	public static final class SerializedShift implements EscapySerialized {
		@SerializedName("name") @Expose public String name;
		@SerializedName("offset2f") @Expose public List<Float> offset = null;
		@SerializedName("pinPoint2f") @Expose public List<Float> pinPoint = null;
		@SerializedName("directVec2f") @Expose public List<Float> directVec = null;

		@Override public String getName() {
			return name;
		}
	}


	public static final class SerializedRenderContainer implements EscapySerialized {

		@SerializedName("name") @Expose public String name;
		@SerializedName("layers") @Expose public List<String> layers = null;

		@Override public String getName() {
			return name;
		}
	}



	@Override public String getName() {
		return name;
	}
	@Override public String getValidType() {
		return VALID_TYPE;
	}
	@Override public String getType() {
		return type;
	}

}
