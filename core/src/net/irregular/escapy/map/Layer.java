package net.irregular.escapy.map;

import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;


/**
 * @author Henry on 11/07/17.
 */
public class Layer {

	private String name;
	private float axisZ = 0;
	private final float[] offset = {0,0};
	private final float[] pinPoint = {0,0};

	private EscapyNamedArray<GameObject> objectsArray;


	public Layer() {
		objectsArray = new EscapyNamedArray<>(GameObject.class);
		objectsArray.
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAxisZ() {
		return axisZ;
	}

	public void setAxisZ(float axisZ) {
		this.axisZ = axisZ;
	}

	public float[] getOffset() {
		return offset;
	}

	public void setOffset(float[] offset) {
		this.offset[0] = offset[0];
		this.offset[1] = offset[1];
	}

	public float[] getPinPoint() {
		return pinPoint;
	}

	public void setPinPoint(float[] pinPoint) {
		this.pinPoint[0] = pinPoint[0];
		this.pinPoint[1] = pinPoint[1];
	}
}
