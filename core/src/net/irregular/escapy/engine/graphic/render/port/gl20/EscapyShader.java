package net.irregular.escapy.engine.graphic.render.port.gl20;

import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface EscapyShader extends EscapyShaderHelper {

	final class ShaderFile {
		public final String VERTEX, FRAGMENT;
		public ShaderFile(String VERTEX, String FRAGMENT) {
			this.VERTEX = VERTEX;
			this.FRAGMENT = FRAGMENT;
		}
		@Override
		public String toString() {
			return "ShaderFile{" +
					"VERTEX='" + VERTEX + '\'' +
					", FRAGMENT='" + FRAGMENT + '\'' +
					'}';
		}
	}

	@EscapyAPI void loadProgram(ShaderFile shaderFile);

}
