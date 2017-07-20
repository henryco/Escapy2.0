package net.irregular.escapy.engine.group.map.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySerialized;

import java.util.List;

/**
 * @author Henry on 14/07/17.
 */
public final class SerializedGameObject implements EscapySerialized {


	@SerializedName("details") @Expose public SerializedDetails details;
	@SerializedName("attributes") @Expose public List<String> attributes = null;

	@SerializedName("static") @Expose public SerializedStatic staticObject;
	//TODO MORE OBJECT TYPES


	public static final class SerializedDetails implements EscapySerialized {
		@SerializedName("name") @Expose public String name;
		@SerializedName("scale") @Expose public float scale;
		@SerializedName("position2f") @Expose public List<Float> position = null;

		@Override public String getName() {
			return name;
		}
	}


	public static final class SerializedStatic implements EscapySerialized {
		@SerializedName("textureNormal") @Expose public String textureNormal;
		@SerializedName("textureLight") @Expose public String textureLight;
		@SerializedName("texture") @Expose public String texture;
	}



	@Override public String getName() {
		return details.getName();
	}
}
