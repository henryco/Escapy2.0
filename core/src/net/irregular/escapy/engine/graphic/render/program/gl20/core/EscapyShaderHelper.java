package net.irregular.escapy.engine.graphic.render.program.gl20.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.TimeUtils;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.utils.Named;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.function.BiConsumer;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface EscapyShaderHelper extends Named {

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


	default ShaderProgram createProxyProgram(ShaderFile file, BiConsumer<Method, Object[]> listener) {

		final ShaderProgram program = createProgram(file);
		final Class obClass = program.getClass();

		return (ShaderProgram) Proxy.newProxyInstance(obClass.getClassLoader(), obClass.getInterfaces(),
				(Object proxy, Method method, Object[] args) -> {
					listener.accept(method, args);
					return method.invoke(program, args);
				}
		);
	}


	default void begin(Batch batch, Runnable r) {
		ShaderProgram defaultShader = batch.getShader();
		r.run();
		batch.setShader(defaultShader);
	}
}
