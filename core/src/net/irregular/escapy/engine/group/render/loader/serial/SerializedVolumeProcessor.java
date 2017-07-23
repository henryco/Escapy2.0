package net.irregular.escapy.engine.group.render.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySimpleSerialized;

/**
 * @author Henry on 23/07/17.
 */
public class SerializedVolumeProcessor extends EscapySimpleSerialized {


	@Override public String getValidType() {
		return "VolumeProcessor";
	}

	@SerializedName("spriteSize") @Expose public float spriteSize = 0;
	@SerializedName("threshold") @Expose public float threshold = 0;
	@SerializedName("height") @Expose public float height = 0;
	@SerializedName("intensity") @Expose public SerializedVolumeIntensity intensity = new SerializedVolumeIntensity();

	public static final class SerializedVolumeIntensity extends EscapySimpleSerialized {
		@SerializedName("ambient") @Expose public float ambient = 0;
		@SerializedName("direct") @Expose public float direct = 0;
		@SerializedName("shadow") @Expose public float shadow = 0;
	}

}
