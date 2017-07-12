package net.irregular.escapy.map.layer.shift;

import net.irregular.escapy.engine.env.utils.Named;

/**
 * @author Henry on 12/07/17.
 */
public interface LayerShift extends Named {

	float[] getOffset();
	float[] getDirect();
	float[] getPinPoint();
}
