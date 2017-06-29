package net.irregular.escapy.engine.graphic.render.port.gl20.shader.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.TimeUtils;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.utils.Named;

import java.util.Date;

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

	default void begin(Batch batch, Runnable r) {
		ShaderProgram defaultShader = batch.getShader();
		r.run();
		batch.setShader(defaultShader);
	}
}
