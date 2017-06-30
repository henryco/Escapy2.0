package net.irregular.escapy.engine.graphic.render.program.gl20.shader.core;

import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.uniform.UniformsProvider;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface EscapyShader extends EscapyShaderHelper, UniformsProvider {

	@EscapyAPI void loadProgram(ShaderFile shaderFile);

	default String getName() {
		return this.getClass().getSimpleName()+"[" + hashCode() + "]";
	}
}
