package net.irregular.escapy.group.render.loader.serial;

import com.badlogic.gdx.graphics.GL20;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.environment.utils.serial.EscapySimpleSerialized;

import java.util.List;

/**
 * @author Henry on 23/07/17.
 */
public final class SerializedLightMask extends EscapySimpleSerialized{


	@Override public String getValidType() {
		return "LightMask";
	}

	@SerializedName("colorRGBA") @Expose public List<Integer> colorRGBA = null;
	@SerializedName("mode") @Expose public SerializedMaskMode mode = new SerializedMaskMode();


	public int[] loadColorRGBA() {
		if (colorRGBA == null) return null;
		return new int[]{colorRGBA.get(0), colorRGBA.get(1), colorRGBA.get(2), colorRGBA.get(3)};
	}

	public static final class SerializedMaskMode extends EscapySimpleSerialized {
		@SerializedName("src") @Expose public String src = "GL_DST_COLOR";
		@SerializedName("dst") @Expose public String dst = "GL_ONE_MINUS_SRC_ALPHA";

		public int[] loadGLMode() {
			try {
				int m_src = (int) GL20.class.getDeclaredField(src).get(GL20.class);
				int m_dst = (int) GL20.class.getDeclaredField(dst).get(GL20.class);
				return new int[]{m_src, m_dst};
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}