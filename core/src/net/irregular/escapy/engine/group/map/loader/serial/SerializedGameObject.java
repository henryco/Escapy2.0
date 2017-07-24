package net.irregular.escapy.engine.group.map.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySerialized;
import net.irregular.escapy.engine.env.utils.serial.EscapySimpleSerialized;

import java.util.Arrays;
import java.util.List;

/**
 * @author Henry on 14/07/17.
 */
public final class SerializedGameObject extends EscapySimpleSerialized {


	@SerializedName("details") @Expose public SerializedDetails details = new SerializedDetails();
	@SerializedName("static") @Expose public SerializedStatic staticObject;
	//TODO MORE OBJECT TYPES


	public static final class SerializedDetails extends EscapySimpleSerialized {
		@SerializedName("scale") @Expose public float scale = 1f;
		@SerializedName("position2f") @Expose public List<Float> position = Arrays.asList(0f,0f);
		@SerializedName("size2f") @Expose public List<Float> size = Arrays.asList(0f, 0f);
		@SerializedName("flip2b") @Expose public List<Boolean> flip = Arrays.asList(false, false);
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
