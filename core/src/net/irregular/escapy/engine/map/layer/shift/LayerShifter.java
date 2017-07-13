package net.irregular.escapy.engine.map.layer.shift;


/**
 * @author Henry on 11/07/17.
 */
public class LayerShifter implements LayerShift {


	public final float[] offset = new float[2];
	public float[] getOffset() {
		return offset;
	}
	public void setOffset(float[] offset) {
		this.offset[0] = offset[0];
		this.offset[1] = offset[1];
	}

	public final float[] pinPoint = new float[2];
	public float[] getPinPoint() {
		return pinPoint;
	}
	public void setPinPoint(float[] pinPoint) {
		this.pinPoint[0] = pinPoint[0];
		this.pinPoint[1] = pinPoint[1];
	}

	public final float[] direct = new float[]{-1,0};
	public float[] getDirect() {
		return direct;
	}
	public void setDirect(float[] direct) {
		this.direct[0] = direct[0];
		this.direct[1] = direct[1];
	}

	private LayerShiftLogic shiftLogic;
	public LayerShiftLogic getLayerShiftLogic() {
		return shiftLogic;
	}
	public void setLayerShiftLogic(LayerShiftLogic shiftLogic) {
		this.shiftLogic = shiftLogic;
	}



	public LayerShifter(LayerShiftLogic shiftLogic) {
		setLayerShiftLogic(shiftLogic);
	}


}
