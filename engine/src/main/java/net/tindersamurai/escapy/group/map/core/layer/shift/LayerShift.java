package net.tindersamurai.escapy.group.map.core.layer.shift;


import net.tindersamurai.escapy.utils.EscapyNamed;

/**
 * @author Henry on 12/07/17.
 */
public interface LayerShift extends EscapyNamed {

	float[] getOffset();
	float[] getDirect();
	float[] getPinPoint();

	LayerShiftLogic getLayerShiftLogic();
	void setLayerShiftLogic(LayerShiftLogic layerShiftLogic);

	default float[] calculateShift() {
		if (getLayerShiftLogic() == null) return new float[]{0,0};
		return getLayerShiftLogic().calculateShift(this);
	}
}