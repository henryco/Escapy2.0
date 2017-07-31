package net.irregular.escapy.group.render.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.environment.utils.serial.EscapySimpleSerialized;

import java.util.Arrays;
import java.util.List;

/**
 * @author Henry on 23/07/17.
 */
public final class SerializedLight extends EscapySimpleSerialized {

	@SerializedName("colorRGB") @Expose public List<Integer> colorRGB = Arrays.asList(255, 255, 255);
	@SerializedName("resolution2i") @Expose public List<Integer> resolution2i = Arrays.asList(128, 128);
	@SerializedName("position2f") @Expose public List<Float> position2f = Arrays.asList(0f, 0f);
	@SerializedName("angles2f") @Expose public List<Float> angles2f =  Arrays.asList(0f, 0f);
	@SerializedName("radius2f") @Expose public List<Float> radius2f = Arrays.asList(0f, 1f);
	@SerializedName("umbra2f") @Expose public List<Float> umbra2f = Arrays.asList(0f, 1f);
	@SerializedName("correct") @Expose public float correct = 0f;
	@SerializedName("alpha") @Expose public float alpha = 1f;
	@SerializedName("coeff") @Expose public float coeff = 1f;
	@SerializedName("scale") @Expose public float scale = 1f;
	@SerializedName("shift") @Expose public String shift = null;
}
