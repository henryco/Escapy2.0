package net.irregular.escapy.engine.group.render.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySimpleSerialized;

import java.util.List;

/**
 * @author Henry on 23/07/17.
 */
public final class SerializedLight extends EscapySimpleSerialized {

	@SerializedName("colorRGB") @Expose public List<Integer> colorRGB = null;
	@SerializedName("resolution2i") @Expose public List<Integer> resolution2i = null;
	@SerializedName("position2f") @Expose public List<Float> position2f = null;
	@SerializedName("angles2f") @Expose public List<Float> angles2f = null;
	@SerializedName("radius2f") @Expose public List<Float> radius2f = null;
	@SerializedName("umbra2f") @Expose public List<Float> umbra2f = null;
	@SerializedName("correct") @Expose public int correct;
	@SerializedName("coeff") @Expose public int coeff;
}
