package net.irregular.escapy.engine.map.zloader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Henry on 13/07/17.
 */
public final class SerializedSubLocation {

	public static final String VALID_TYPE = "SubLocation";



	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;
	@SerializedName("location") @Expose public String location;
	@SerializedName("layers") @Expose public List<SerializedLayer> layers = null;


	public static final class SerializedDetails {
		@SerializedName("name") @Expose public String name;
		@SerializedName("scale") @Expose public float scale;
		@SerializedName("thickness") @Expose public float thickness;
		@SerializedName("position2f") @Expose public List<Float> position = null;
	}


	public static final class SerializedLayer {
		@SerializedName("name") @Expose public String name;
		@SerializedName("axis_z") @Expose public float axisZ;
		@SerializedName("shift") @Expose public SerializedShift shift;
		@SerializedName("objects") @Expose public List<SerializedObject> objects = null;
	}


	public static final class SerializedObject {
		@SerializedName("details") @Expose public SerializedDetails details;
		@SerializedName("static") @Expose public SerializedStatic serializedStatic;
		//TODO MORE OBJECT TYPES
	}


	public static final class SerializedShift {
		@SerializedName("logic") @Expose public String logic;
		@SerializedName("offset2f") @Expose public List<Float> offset = null;
		@SerializedName("pinPoint2f") @Expose public List<Float> pinPoint = null;
		@SerializedName("directVec2f") @Expose public List<Float> directVec = null;
	}


	public static final class SerializedStatic {
		@SerializedName("textureNormal") @Expose public String textureNormal;
		@SerializedName("textureLight") @Expose public String textureLight;
		@SerializedName("texture") @Expose public String texture;
	}
}
