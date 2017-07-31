package net.irregular.escapy.graphic.render.program.gl20.core;

import net.irregular.escapy.environment.context.annotation.EscapyAPI;
import net.irregular.escapy.graphic.render.program.gl20.core.uniform.UniformsProvider;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface EscapyShader extends EscapyShaderHelper, UniformsProvider {

	@EscapyAPI void loadProgram(ShaderFile shaderFile);

	default String getName() {
		return this.getClass().getSimpleName()+"[" + hashCode() + "]";
	}
}
