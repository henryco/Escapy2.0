package net.tindersamurai.escapy.graphic.render.program.gl20.proxy;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import lombok.Value;
import lombok.experimental.Delegate;

/**
 * @author Henry on 22/07/17.
 */
@Value public class ProxyShaderProgram implements EscapyProxyShaderProgram {
	public final @Delegate ShaderProgram shaderProgram;
}