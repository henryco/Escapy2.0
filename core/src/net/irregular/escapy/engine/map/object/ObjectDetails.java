package net.irregular.escapy.engine.map.object;

import java.util.Arrays;

/**
 * @author Henry on 11/07/17.
 */
public class ObjectDetails {

	private final String name;
	private float scale, thickness;
	public final float[] position;

	{
		scale = 1f;
		thickness = 0f;
		position = new float[2];
	}


	public ObjectDetails() {
		this(null);
	}
	public ObjectDetails(String name) {
		this.name = name;
	}
	public ObjectDetails(String name, float scale, float thickness) {
		this(name);
		this.scale = scale;
		this.thickness = thickness;
	}
	public ObjectDetails(String name, float scale, float thickness, float[] position) {
		this(name, scale, thickness);
		setPosition(position);
	}


	@Override
	public String toString() {
		return "ObjectDetails{" +
				"name='" + name + '\'' +
				", scale=" + scale +
				", thickness=" + thickness +
				", position=" + Arrays.toString(position) +
				'}';
	}

	public ObjectDetails setScale(float scale) {
		this.scale = scale;
		return this;
	}

	public ObjectDetails setThickness(float thickness) {
		this.thickness = thickness;
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

	public float getThickness() {
		return thickness;
	}

	public float[] getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}
}
