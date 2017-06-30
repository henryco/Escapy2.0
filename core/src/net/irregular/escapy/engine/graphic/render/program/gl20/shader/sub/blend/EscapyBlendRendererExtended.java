package net.irregular.escapy.engine.graphic.render.program.gl20.shader.sub.blend;

import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.uniform.StandardUniforms;
import net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.uniform.Uniforms;

import java.util.Collection;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI @Dante
public class EscapyBlendRendererExtended extends EscapyBlendRenderer {

	public final StandardUniforms uniformProvider = new StandardUniforms();

	@EscapyAPI @Dante public EscapyBlendRendererExtended() {}
	@EscapyAPI @Dante public EscapyBlendRendererExtended(ShaderFile shaderFile) {
		super(shaderFile);
	}
	@EscapyAPI @Dante public EscapyBlendRendererExtended(ShaderFile shaderFile, String... sourcesNames) {
		super(shaderFile, sourcesNames);
	}


	@Override
	public Collection<Uniforms> getUniforms() {
		return uniformProvider.getUniforms();
	}


}
