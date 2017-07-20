package net.irregular.escapy.engine.group.map.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySerialized;

import java.util.List;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedSubLocation implements EscapySerialized {

	public static final String VALID_TYPE = "SubLocation";


	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;
	@SerializedName("layers") @Expose public List<SerializedLayer> layers = null;
	@SerializedName("layerGroups") @Expose public List<SerializedLayerGroup> layerGroups = null;
	@SerializedName("attributes") @Expose public List<String> attributes = null;


	public static final class SerializedLayer implements EscapySerialized {
		@SerializedName("name") @Expose public String name;
		@SerializedName("axis_z") @Expose public float axisZ;
		@SerializedName("shift") @Expose public SerializedShift shift;
		@SerializedName("objects") @Expose public List<SerializedGameObject> objects = null;
		@SerializedName("attributes") @Expose public List<String> attributes = null;

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


	public static final class SerializedLayerGroup implements EscapySerialized {

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
