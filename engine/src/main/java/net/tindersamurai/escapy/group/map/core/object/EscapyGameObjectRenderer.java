package net.tindersamurai.escapy.group.map.core.object;

import com.badlogic.gdx.utils.Disposable;
import net.tindersamurai.escapy.graphic.render.mapping.EscapyRenderable;

/**
 * @author Henry on 12/07/17.
 */
public interface EscapyGameObjectRenderer<T extends EscapyGameObject> extends Disposable {

	void bindGameObject(T gameObject);
	EscapyRenderable getRenderer();

	float getRenderedWidth();
	float getRenderedHeight();

	void setPosition(float x, float y);
	void setScale(float scale);
	void setFlip(boolean x, boolean y);
	void setSize(float w, float h);
}