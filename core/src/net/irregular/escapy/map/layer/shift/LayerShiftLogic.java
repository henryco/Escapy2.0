package net.irregular.escapy.map.layer.shift;

/**
 * @author Henry on 12/07/17.
 */ @FunctionalInterface
public interface LayerShiftLogic {

	float[] calculateShift(LayerShift shift);
}
