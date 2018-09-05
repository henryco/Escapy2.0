package net.tindersamurai.escapy.graphic.render.program.gl20.shader.single;

import net.tindersamurai.escapy.context.annotation.Dante;
import net.tindersamurai.escapy.context.annotation.EscapyAPI;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.uniform.Uniform;

import java.util.Collection;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI @Dante
public class SingleRendererExtended extends SingleRenderer implements EscapyUniformSingle {

	public final StandardUniforms uniformProvider = new StandardUniforms();

	@EscapyAPI public SingleRendererExtended() {}
	@EscapyAPI public SingleRendererExtended(ShaderFile shaderFile) {
		super(shaderFile);
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
