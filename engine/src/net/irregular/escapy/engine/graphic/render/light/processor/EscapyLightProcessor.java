package net.irregular.escapy.engine.graphic.render.light.processor;

import net.irregular.escapy.engine.env.utils.EscapyObject;

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
