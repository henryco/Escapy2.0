package net.irregular.escapy.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Date;

/**
 * @author Henry on 26/07/17.
 */
public class EscapyLogger {

	private String suffix;
	private String file;

	public EscapyLogger() {
		this("Log");
	}

	public EscapyLogger(String name) {
		fileJava().name(name);
	}

	public EscapyLogger fileGL() {
		return file("error_gl_log.txt");
	}

	public EscapyLogger fileJava() {
		return file("error_java_log.txt");
	}

	public EscapyLogger file(String file) {
		this.file = file;
		return this;
	}

	public EscapyLogger name(String name) {
		this.suffix = name;
		return this;
	}


	public void log(Throwable e){
		log(e, false);
	}


	public void log(Throwable e, boolean printStackTrace) {
		if (printStackTrace) e.printStackTrace();

		StringBuilder builder = new StringBuilder();
		builder.append(e.toString()).append("\n");
		for (StackTraceElement traceElement : e.getStackTrace())
			builder.append(traceElement.toString()).append("\n");

		log(builder.toString());
	}


	public void log(String msg) {
		FileHandle fileHandle = Gdx.files.local(file);
		fileHandle.writeString(new Date(TimeUtils.millis()).toString()+
				"\n["+suffix+"]:\n"+ msg + "\n\n", true);
	}
}
