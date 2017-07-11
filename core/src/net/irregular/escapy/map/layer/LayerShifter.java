package net.irregular.escapy.map.layer;

import net.irregular.escapy.engine.env.utils.Named;

/**
 * @author Henry on 11/07/17.
 */
public class LayerShifter implements Named {

	public final String name;
	public final float[] direct;
	public final float[] offset;
	public final float[] pinPoint;

	{
		direct = new float[]{-1,0};
		pinPoint = new float[2];
		offset = new float[2];
	}


	public LayerShifter(String name) {
		this.name = name;
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

	public float[] getDirect() {
		return direct;
	}

	public void setDirect(float[] direct) {
		this.direct[0] = direct[0];
		this.direct[1] = direct[1];
	}

	@Override
	public String getName() {
		return name;
	}
}
