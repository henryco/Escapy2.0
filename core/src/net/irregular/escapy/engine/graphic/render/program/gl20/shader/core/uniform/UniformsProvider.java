package net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.uniform;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.Collection;

/**
 * @author Henry on 29/06/17.
 */
public interface UniformsProvider {

	default Collection<Uniforms> getUniforms() {
		return null;
	}

	default void provideUniforms(final ShaderProgram shaderProgram) {
		if (getUniforms() != null && shaderProgram != null)
			getUniforms().forEach(uniform -> uniform.loadUniforms(shaderProgram));
	}

}
