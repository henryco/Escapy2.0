package net.irregular.escapy.group.map.core.object;

import java.util.Arrays;

/**
 * @author Henry on 11/07/17.
 */
public class ObjectDetails {

	public final String name;
	public final float[] position;
	public final float[] size;
	public final boolean[] flip;

	private float scale;


	{
		scale = 1f;
		position = new float[]{0, 0};
		size = new float[]{0, 0};
		flip = new boolean[]{false, false};
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

	public ObjectDetails setSize(float[] size) {
		this.size[0] = size[0];
		this.size[1] = size[1];
		return this;
	}

	public ObjectDetails setFlip(boolean[] flip) {
		this.flip[0] = flip[0];
		this.flip[1] = flip[1];
		return this;
	}

	public float[] getSize() {
		return size;
	}

	public boolean[] getFlip() {
		return flip;
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
