package net.tindersamurai.escapy.graphic.camera;

import com.badlogic.gdx.math.Matrix4;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.graphic.screen.Wipeable;

public interface IEscapyCamera extends Wipeable {

	void setResolution(Resolution resolution);

	void setCameraPosition(float x, float y);

	void setZoom(float zoom);

	Resolution getResolution();

	Matrix4 getProjection();

	float[] getPosition();

	float getZoom();

	void update();

	void translateCamera(float x, float y);

	default IEscapyCamera update(Runnable runnable) {
		runnable.run();
		this.update();
		return this;
	}

	default void translateCamera(float[] translation) {
		this.translateCamera(translation[0], translation[1]);
	}

	default void setCameraPosition(float x, float y, boolean absolute) {
		if (absolute) setCameraPosition(x, y);
		else setCameraPosition(x + .5f * getResolution().width, y + .5f * getResolution().height);
	}

	default void setCameraPosition(float[] position, boolean absolute) {
		this.setCameraPosition(position[0], position[1], absolute);
	}

	default void setCameraPosition(float[] position) {
		setCameraPosition(position, true);
	}
}
