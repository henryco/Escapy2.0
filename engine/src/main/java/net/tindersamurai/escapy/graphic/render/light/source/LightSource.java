package net.tindersamurai.escapy.graphic.render.light.source;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFrameBuffer;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.utils.EscapyObject;

/**
 * @author Henry on 23/07/17.
 */ @Log
public class LightSource implements EscapyObject, Disposable {

	private final @Getter EscapyLightSource lightSource;
	private final float[] position;

	public final @Getter String name;

	private @Getter EscapyFBO buffer;
	private @Getter float scale;
	private @Getter float alpha;
	private final Batch batch;

	private IEscapyCamera camera;
	private Texture region;
	private boolean update;


	public LightSource(String name, EscapyLightSource lightSource) {

		this.position = new float[]{0, 0};
		this.batch = new SpriteBatch();
		this.lightSource = lightSource;
		this.name = name;
		this.scale = 1f;

		setResolution(new Resolution(128, 128));
		update();
	}

	public LightSource(String name) {
		this(name, new EscapyLightSource());
	}


	public void prepareBuffer(boolean force) {
		if (update || force) {
			log.info("UPDATE LIGHT SOURCE BUFFER: [" + name + "]");
			batch.setProjectionMatrix(camera.update().getProjection());
			buffer.begin(() -> {
				buffer.wipe();
				lightSource.draw(batch, 0, 0, region, region);
			});
			update = false;
		}
	}

	public void prepareBuffer() {
		prepareBuffer(false);
	}



	public void drawBuffer(Batch batch) {
		buffer.getSprite().draw(batch);
	}

	public void draw(Batch batch) {
		prepareBuffer();
		buffer.draw(batch);
	}



	private void update(Runnable r) {
		r.run();
		update();
	}

	public void update() {
		update = true;
	}



	public void setResolution(Resolution resolution) {

		region = new Texture(resolution.width, resolution.height, Pixmap.Format.RGBA8888);
		camera = new EscapyCamera(resolution);
		lightSource.setResolution(resolution);

		if (buffer != null) buffer.dispose();
		buffer = new EscapyFrameBuffer(resolution);

		setPosition(position);
		setScale(scale);
		update();
	}


	public void setPosition(float x, float y) {

		float w = buffer.getSprite().getWidth() * scale;
		float h = buffer.getSprite().getHeight() * scale;

		position[0] = x  - 0.5f * w;
		position[1] = y - 0.5f * h;

		buffer.getSprite().setPosition(position[0], position[1]);
	}

	public void setAlpha(float alpha) {

		this.alpha = alpha;
		buffer.getSprite().setAlpha(alpha);
	}

	public void setScale(float scale) {

		this.scale = scale;
		buffer.getSprite().setScale(scale);
	}

	public void translate(float x, float y) {
		float[] position = getPosition();
		setPosition(position[0] + x, position[1] + y);
	}

	public void setPosition(float[] position2f) {
		setPosition(position2f[0], position2f[1]);
	}
	public float[] getPosition() {
		float w = buffer.getSprite().getWidth() * scale;
		float h = buffer.getSprite().getHeight() * scale;
		return new float[]{position[0] + 0.5f * w, position[1] + 0.5f * h};
	}





//	---------------------------------------- SET ---------------------------------------------
	public void setColor(Color color) {
		update(() -> lightSource.setColor(color));
	}
	public void setCorrect(float correct) {
		update(() -> lightSource.setCorrect(correct));
	}
	public void setCoeff(float coeff) {
		update(() -> lightSource.setCoeff(coeff));
	}
	public void setRadius(float radiusMin, float radiusMax) {
		update(() -> lightSource.setRadius(radiusMin, radiusMax));
	}
	public void setUmbra(float coeff, float power) {
		update(() -> lightSource.setUmbra(coeff, power));
	}
	public void setAngles(float rot, float size) {
		update(() -> lightSource.setAngles(rot, size));
	}


//	---------------------------------------- GET ---------------------------------------------
	public Color getColor() {
		return lightSource.getColor();
	}
	public float getCorrect() {
		return lightSource.getCorrect();
	}
	public float getCoeff() {
		return lightSource.getCoeff();
	}
	public Resolution getResolution() {
		return lightSource.getResolution();
	}
	public Float[] getRadius() {
		return lightSource.getRadius();
	}
	public Float[] getUmbra() {
		return lightSource.getUmbra();
	}
	public Float[] getAngles() {
		return lightSource.getAngles();
	}


	@Override
	public void dispose() {
		buffer.dispose();
	}

}
