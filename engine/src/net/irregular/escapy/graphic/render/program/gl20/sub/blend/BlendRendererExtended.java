package net.irregular.escapy.graphic.render.program.gl20.sub.blend;

import net.irregular.escapy.environment.context.annotation.Dante;
import net.irregular.escapy.environment.context.annotation.EscapyAPI;
import net.irregular.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.irregular.escapy.graphic.render.program.gl20.core.uniform.Uniform;

import java.util.Collection;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
@Dante
public class BlendRendererExtended extends BlendRenderer implements EscapyUniformBlender {

	public final StandardUniforms uniformProvider = new StandardUniforms();

	@EscapyAPI @Dante public BlendRendererExtended() {}
	@EscapyAPI @Dante public BlendRendererExtended(ShaderFile shaderFile) {
		super(shaderFile);
	}
	@EscapyAPI @Dante public BlendRendererExtended(ShaderFile shaderFile, String... sourcesNames) {
		super(shaderFile, sourcesNames);
	}


	public BlendRendererExtended setDebug(boolean debug) {
		super.setDebug(debug);
		return this;
	}

	@Override
	public Collection<Uniform> getUniforms() {
		return uniformProvider.getUniforms();
	}

	@Override
	public StandardUniforms getStandardUniforms() {
		return uniformProvider;
	}

}
