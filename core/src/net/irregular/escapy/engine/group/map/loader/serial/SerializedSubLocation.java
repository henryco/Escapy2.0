package net.irregular.escapy.engine.group.map.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySimpleSerialized;

import java.util.List;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedSubLocation extends EscapySimpleSerialized {

	public static final String VALID_TYPE = "SubLocation";
	@Override public String getValidType() {
		return VALID_TYPE;
	}

	@SerializedName("layers") @Expose public List<SerializedLayer> layers = null;
	@SerializedName("renderGroups") @Expose public List<SerializedLayerGroup> layerGroups = null;

	public static final class SerializedLayer extends EscapySimpleSerialized {

		@SerializedName("axis_z") @Expose public float axisZ;
		@SerializedName("shift") @Expose public SerializedShift shift;
		@SerializedName("objects") @Expose public List<SerializedGameObject> objects = null;
	}


	public static final class SerializedShift extends EscapySimpleSerialized {

		@SerializedName("offset2f") @Expose public List<Float> offset = null;
		@SerializedName("pinPoint2f") @Expose public List<Float> pinPoint = null;
		@SerializedName("directVec2f") @Expose public List<Float> directVec = null;
	}


	public static final class SerializedLayerGroup extends EscapySimpleSerialized {
		@SerializedName("layers") @Expose public List<String> layers = null;
	}


}
