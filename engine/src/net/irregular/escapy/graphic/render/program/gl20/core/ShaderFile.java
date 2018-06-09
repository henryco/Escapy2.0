package net.irregular.escapy.graphic.render.program.gl20.core;

import lombok.Value;

/**
 * @author Henry on 30/06/17.
 */
@Value public class ShaderFile {

	public final String VERTEX, FRAGMENT;

	@Override
	public String toString() {
		return "ShaderFile{" +
				"VERTEX='" + VERTEX + '\'' +
				", FRAGMENT='" + FRAGMENT + '\'' +
				'}';
	}
}
