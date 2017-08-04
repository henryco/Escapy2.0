package net.irregular.escapy.group.map.core.layer.shift;


import net.irregular.escapy.utils.EscapyNamed;

/**
 * @author Henry on 12/07/17.
 */
public interface LayerShift extends EscapyNamed {

	float[] getOffset();
	float[] getDirect();
	float[] getPinPoint();

	LayerShiftLogic getLayerShiftLogic();

	default float[] calculateShift() {
		if (getLayerShiftLogic() == null) return new float[]{0,0};
		return getLayerShiftLogic().calculateShift(this);
	}
}