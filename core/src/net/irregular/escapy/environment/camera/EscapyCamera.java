package net.irregular.escapy.environment.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import net.irregular.escapy.utils.TransVec;

import java.util.function.Function;

/**
 * @author Henry on 27/06/17.
 */
public class EscapyCamera {

	private OrthographicCamera camera;
	private Resolution resolution;


	public EscapyCamera(Resolution resolution) {
		setResolution(resolution);
	}



	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(resolution.flip, resolution.width, resolution.height);
	}

	public Resolution getResolution() {
		return resolution;
	}

	public Matrix4 getProjection() {
		return camera.combined;
	}

	public void update() {
		camera.update();
	}

	public void update(Runnable runnable) {
		runnable.run();
		update();
	}




//	------------------------- TRANSLATION -----------------------------
	public void translateCamera(float[] translation) {
		this.translateCamera(translation[0], translation[1]);
	}

	public void translateCamera(float x, float y) {
		this.camera.translate(x, y);
		this.camera.update();
	}

	public void translateCamera(TransVec translationVec) {
		translateCamera(translationVec.x, translationVec.y);
	}
//	------------------------- TRANSLATION -----------------------------







//	------------------------- POSITION --------------------------------
	public void setCameraPosition(float[] position, boolean absolute) {
		this.setCameraPosition(position[0], position[1], absolute);
	}

	private void setCameraPosition(float x, float y) {
		this.translateCamera(x - camera.position.x, y - camera.position.y);
	}

	public void setCameraPosition(float x, float y, boolean absolute) {
		if (absolute) setCameraPosition(x, y);
		else setCameraPosition(x + .5f * resolution.width, y + .5f * resolution.height);
	}
	public void setCameraPosition(TransVec position, boolean absolute) {
		this.setCameraPosition(position.x, position.y, absolute);
	}

	public float[] getPosition() {
		return new float[]{camera.position.x, camera.position.y};
	}

	public TransVec getPositionVec() {
		return new TransVec(getPosition());
	}
//	------------------------- POSITION --------------------------------






//	----------------------------- ZOOM --------------------------------
	public void setZoom(float zoom) {
		this.camera.zoom = zoom;
	}

	public void zoom(Function<Float, Float> zoom) {
		camera.zoom = zoom.apply(camera.zoom);
	}

	public float getZoom() {
		return camera.zoom;
	}
//	----------------------------- ZOOM --------------------------------





//	------------------------- GL BUFFER -------------------------------
	/**
	 * Clear current GL buffer.
	 */
	public void clear() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Wipe current GL buffer.
	 */
	public void wipe() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
//	------------------------- GL BUFFER -------------------------------

}
