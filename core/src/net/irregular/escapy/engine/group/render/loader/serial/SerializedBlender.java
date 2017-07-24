package net.irregular.escapy.engine.group.render.loader.serial;

import com.badlogic.gdx.graphics.GL20;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySimpleSerialized;

/**
 * @author Henry on 23/07/17.
 */
public final class SerializedBlender extends EscapySimpleSerialized {

	@Override public String getValidType() {
		return "Blender";
	}
	@SerializedName("mode") @Expose public SerializedBlendMode blendMode = new SerializedBlendMode();


	public static final class SerializedBlendMode extends EscapySimpleSerialized {

		@SerializedName("srcRGB") @Expose public String srcRGB = "GL_SRC_ALPHA";
		@SerializedName("dstRGB") @Expose public String dstRGB = "GL_ONE";
		@SerializedName("srcALPHA") @Expose public String srcALPHA = "GL_ONE";
		@SerializedName("dstALPHA") @Expose public String dstALPHA = "GL_ONE_MINUS_SRC_COLOR";

		public int[] loadGLMode() {
			try {
				int m_src_rgb = (int) GL20.class.getDeclaredField(srcRGB).get(GL20.class);
				int m_dst_rgb = (int) GL20.class.getDeclaredField(dstRGB).get(GL20.class);
				int m_src_a = (int) GL20.class.getDeclaredField(srcALPHA).get(GL20.class);
				int m_dst_a = (int) GL20.class.getDeclaredField(dstALPHA).get(GL20.class);
				return new int[]{m_src_rgb, m_dst_rgb, m_src_a, m_dst_a};
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
