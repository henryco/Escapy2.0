package net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.irregular.escapy.engine.graphic.render.program.gl20.proxy.EscapyProxyShaderProgram;

import java.util.Collection;

/**
 * @author Henry on 29/06/17.
 */
public interface UniformsProvider {

	default Collection<Uniform> getUniforms() {
		return null;
	}

	default void provideUniforms(final ShaderProgram shaderProgram) {
		if (getUniforms() != null && shaderProgram != null) {
			getUniforms().forEach(uniform -> uniform.loadUniforms(shaderProgram));
		}
	}

	default void provideUniforms(final EscapyProxyShaderProgram shaderProgram) {
		if (getUniforms() != null && shaderProgram != null) {
			getUniforms().forEach(uniform -> uniform.loadUniforms(shaderProgram));
		}
	}

}
