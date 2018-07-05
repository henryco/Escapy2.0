package net.tindersamurai.escapy.graphic.render.light.processor;

import net.tindersamurai.escapy.utils.EscapyObject;

/**
 * @author Henry on 27/07/17.
 */
public interface EscapyLightProcessor extends EscapyObject {

	void setFieldSize(float width, float height);
	Float[] getFieldSize();

	void setThreshold(float threshold);
	void setEnable(boolean enable);

	float getThreshold();
	boolean isEnable();
}
