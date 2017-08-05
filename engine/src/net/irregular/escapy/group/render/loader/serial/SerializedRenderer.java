package net.irregular.escapy.group.render.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.utils.serial.EscapySimpleSerialized;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Henry on 20/07/17.
 */
public final class SerializedRenderer extends EscapySimpleSerialized {


	@Override public String getValidType() {
		return "Renderer";
	}
	@SerializedName("renderGroups") @Expose public List<SerializedRenderGroup> renderGroups = null;


	public static final class SerializedRenderGroup extends EscapySimpleSerialized {

		@Override public String getValidType() {
			return "RenderGroup";
		}
		@SerializedName("mask") @Expose public SerializedLightMask lightMask;
		@SerializedName("blend") @Expose public SerializedBlender blender = new SerializedBlender();
		@SerializedName("processor") @Expose public SerializedLightProcessor processor = new SerializedLightProcessor();
		@SerializedName("lightGroup") @Expose public SerializedLightGroup lightGroup = new SerializedLightGroup();
	}

	public static final class SerializedLightGroup extends EscapySimpleSerialized {
		@SerializedName("lights") @Expose public List<SerializedLight> lights = new LinkedList<>();
	}


}
