package net.irregular.escapy.engine.graphic.render.program.gl20.sub.single;

import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform.Uniform;

import java.util.Collection;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI @Dante
public class EscapySingleRendererExtended extends EscapySingleRenderer implements EscapyUniformSingle {

	public final StandardUniforms uniformProvider = new StandardUniforms();

	@EscapyAPI public EscapySingleRendererExtended() {}
	@EscapyAPI public EscapySingleRendererExtended(ShaderFile shaderFile) {
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
