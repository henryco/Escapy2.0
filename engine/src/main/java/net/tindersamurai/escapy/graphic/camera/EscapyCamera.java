package net.tindersamurai.escapy.graphic.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import net.tindersamurai.escapy.graphic.screen.Resolution;

/**
 * @author Henry on 27/06/17.
 */
public class EscapyCamera implements IEscapyCamera {

	private OrthographicCamera camera;
	private Resolution resolution;


	public EscapyCamera(Resolution resolution) {
		setResolution(resolution);
	}


	@Override
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(resolution.bool, resolution.width, resolution.height);
	}

	@Override
	public Resolution getResolution() {
		return resolution;
	}

	@Override
	public Matrix4 getProjection() {
		return camera.combined;
	}

	@Override
	public EscapyCamera update() {
		camera.update();
		return this;
	}



//	------------------------- TRANSLATION -----------------------------

	@Override
	public void translateCamera(float x, float y) {
		this.camera.translate(x, y);
		this.camera.update();
	}

//	------------------------- TRANSLATION -----------------------------






//	------------------------- POSITION --------------------------------

	@Override
	public void setCameraPosition(float x, float y) {
		this.translateCamera(x - camera.position.x, y - camera.position.y);
	}

	@Override
	public float[] getPosition() {
		return new float[]{camera.position.x, camera.position.y};
	}

//	------------------------- POSITION --------------------------------






//	----------------------------- ZOOM --------------------------------

	@Override
	public void setZoom(float zoom) {
		this.camera.zoom = zoom;
	}

	@Override
	public float getZoom() {
		return camera.zoom;
	}

//	----------------------------- ZOOM --------------------------------




}
