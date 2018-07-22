package net.tindersamurai.escapy.graphic.render.program.gl20.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.tindersamurai.escapy.context.annotation.EscapyAPI;
import net.tindersamurai.escapy.graphic.render.program.gl20.proxy.EscapyProxyShaderProgram;
import net.tindersamurai.escapy.graphic.render.program.gl20.proxy.ProxyShaderProgram;
import net.tindersamurai.escapy.utils.EscapyLogger;
import net.tindersamurai.escapy.utils.EscapyObject;
import net.tindersamurai.escapy.utils.proxy.EscapyProxyInstanceObserver;

import java.util.Arrays;
import java.util.logging.Logger;


/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface EscapyShaderHelper extends EscapyObject {


	@EscapyAPI
	default void checkStatus(ShaderProgram program) {
		Logger log = Logger.getLogger(this.getName());
		log.info(program.isCompiled() ? "SHADER COMPILED: "+this.getName() : "ERROR: "+this.getName()+"\n"+program.getLog()+"\n");
		if (!program.isCompiled()) {
			log.warning("ERROR WHILE COMPILING SHADER: "+this.getName()+"\n"+program.getLog());
			new EscapyLogger().fileGL().name("SHADER ERROR "+this.getName()).log(program.getLog());
		}
	}


	default ShaderProgram createProgram(ShaderFile file) {
		ShaderProgram.pedantic = false;
		ShaderProgram shaderProgram = new ShaderProgram(file.VERTEX, file.FRAGMENT);
		checkStatus(shaderProgram);
		return shaderProgram;
	}


	default EscapyProxyShaderProgram createProxyProgram(ShaderFile file, boolean debug) {

		EscapyProxyShaderProgram program = new ProxyShaderProgram(createProgram(file));
		if (!debug) return program;
		Logger log = Logger.getLogger(this.getName());
		return new EscapyProxyInstanceObserver((methodResult, methodName, args) ->
				log.info("SHADER_SHELL["+this.hashCode()+"]: "+methodName + " : "+ Arrays.toString(args))
		).create(program);
	}


	default void begin(Batch batch, Runnable r) {
		final ShaderProgram defaultShader = batch.getShader();
		r.run();
		batch.setShader(defaultShader);
	}
}