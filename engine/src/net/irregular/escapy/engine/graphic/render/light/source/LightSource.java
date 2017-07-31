package net.irregular.escapy.engine.graphic.render.light.source;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFBO;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFrameBuffer;
import net.irregular.escapy.engine.graphic.screen.Resolution;

/**
 * @author Henry on 23/07/17.
 */
public class LightSource implements EscapyObject {

	public final EscapyLightSource lightSource;
	public final float[] position;
	public final String name;

	private Texture region;

	private EscapyFBO buffer;
	private boolean update;
	private float scale;
	private float alpha;


	public LightSource(String name, int scrW, int scrH) {
		this(name, new EscapyLightSource(), scrW, scrH);
	}
	public LightSource(String name, EscapyLightSource lightSource, int scrW, int scrH) {

		this.lightSource = lightSource;
		this.position = new float[]{0, 0};
		this.name = name;
		this.scale = 1f;

		setResolution(new Resolution(128, 128));
		resize(scrW, scrH);
		update();
	}




	public void prepareBuffer(Batch batch, boolean force) {
		if (update || force) {
			buffer.begin(() -> {
				buffer.wipe();
				lightSource.draw(batch, 0, 0, region, region);
			});
			update = false;
		}
	}

	public void prepareBuffer(Batch batch) {
		prepareBuffer(batch, false);
	}



	public void drawBuffer(Batch batch) {
		buffer.getSprite().draw(batch);
	}

	public void draw(Batch batch) {
		prepareBuffer(batch);
		buffer.renderGraphics(batch);
	}



	private void update(Runnable r) {
		r.run();
		update();
	}

	public void update() {
		update = true;
	}




	public void resize(int w, int h) {
		update(() -> this.region = new Texture(w, h, Pixmap.Format.RGBA8888));
	}


	public void setResolution(Resolution resolution) {

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


	public float getScale() {
		return scale;
	}
	public float getAlpha() {
		return alpha;
	}
	public EscapyFBO getBuffer() {
		return buffer;
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

	@Override
	public String getName() {
		return name;
	}
}
