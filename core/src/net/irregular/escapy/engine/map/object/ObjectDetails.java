package net.irregular.escapy.engine.map.object;

import java.util.Arrays;

/**
 * @author Henry on 11/07/17.
 */
public class ObjectDetails {

	private final String name;
	private float scale;
	public final float[] position;

	{
		scale = 1f;
		position = new float[2];
	}


	public ObjectDetails() {
		this(null);
	}
	public ObjectDetails(String name) {
		this.name = name;
	}
	public ObjectDetails(String name, float scale) {
		this(name);
		this.scale = scale;
	}
	public ObjectDetails(String name, float scale, float[] position) {
		this(name, scale);
		setPosition(position);
	}


	@Override
	public String toString() {
		return "ObjectDetails{" +
				"name='" + name + '\'' +
				", scale=" + scale +
				", position=" + Arrays.toString(position) +
				'}';
	}

	public ObjectDetails setScale(float scale) {
		this.scale = scale;
		return this;
	}


	public ObjectDetails setPosition(float[] position) {
		this.position[0] = position[0];
		this.position[1] = position[1];
		return this;
	}

	public float getScale() {
		return scale;
	}


	public float[] getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}
}
