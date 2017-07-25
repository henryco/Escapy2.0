package net.irregular.escapy.engine.graphic.render.program.gl20.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.TimeUtils;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.env.utils.proxy.EscapyProxyInstanceObserver;
import net.irregular.escapy.engine.graphic.render.program.gl20.proxy.EscapyProxyShaderProgram;
import net.irregular.escapy.engine.graphic.render.program.gl20.proxy.ProxyShaderProgram;

import java.util.Arrays;
import java.util.Date;


/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface EscapyShaderHelper extends EscapyObject {


	@EscapyAPI
	default void checkStatus(ShaderProgram program) {
		System.err.println(program.isCompiled() ? "COMPILED: "+this.getName() : "ERROR: "+this.getName()+"\n"+program.getLog()+"\n");
		if (!program.isCompiled()) {
			FileHandle file = Gdx.files.local("error_log.txt");
			file.writeString(new Date(TimeUtils.millis()).toString()+"\nERROR: "+this.getName()+"\n"+program.getLog()+"\n", true);
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

		return new EscapyProxyInstanceObserver((methodResult, methodName, args) ->
				System.out.println("SHADER_SHELL["+this.hashCode()+"]: "+methodName + " : "+ Arrays.toString(args))
		).create(program);
	}


	default void begin(Batch batch, Runnable r) {
		ShaderProgram defaultShader = batch.getShader();
		r.run();
		batch.setShader(defaultShader);
	}
}
