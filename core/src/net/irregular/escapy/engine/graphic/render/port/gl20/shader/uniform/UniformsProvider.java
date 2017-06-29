package net.irregular.escapy.engine.graphic.render.port.gl20.shader.uniform;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.Collection;

/**
 * @author Henry on 29/06/17.
 */
public interface UniformsProvider {

	default void provideUniforms(
			final Collection<Uniforms> uniforms,
			final ShaderProgram shaderProgram)
	{
		if (uniforms != null && shaderProgram != null)
			uniforms.forEach(uniform -> uniform.loadUniforms(shaderProgram));
	}

}
