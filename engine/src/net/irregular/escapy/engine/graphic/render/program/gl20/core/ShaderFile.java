package net.irregular.escapy.engine.graphic.render.program.gl20.core;

/**
 * @author Henry on 30/06/17.
 */
public class ShaderFile {

	public final String VERTEX, FRAGMENT;
	public ShaderFile(String VERTEX, String FRAGMENT) {
		this.VERTEX = VERTEX;
		this.FRAGMENT = FRAGMENT;
	}
	@Override
	public String toString() {
		return "ShaderFile{" +
				"VERTEX='" + VERTEX + '\'' +
				", FRAGMENT='" + FRAGMENT + '\'' +
				'}';
	}
}
