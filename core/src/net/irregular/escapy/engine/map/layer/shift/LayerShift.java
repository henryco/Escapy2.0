package net.irregular.escapy.engine.map.layer.shift;


/**
 * @author Henry on 12/07/17.
 */
public interface LayerShift {

	float[] getOffset();
	float[] getDirect();
	float[] getPinPoint();

	LayerShiftLogic getLayerShiftLogic();

	default float[] calculateShift() {
		if (getLayerShiftLogic() == null) return new float[]{0,0};
		return getLayerShiftLogic().calculateShift(this);
	}
}