package net.irregular.escapy.engine.graphic.render.program.shader.proxy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.irregular.escapy.engine.graphic.render.program.shader.AbsLightSource;
import net.irregular.escapy.engine.graphic.screen.Resolution;

/**
 * @author Henry on 23/07/17.
 */
public class LightSource {

	public final AbsLightSource lightSource;
	public final float[] position;

	private final Sprite region;
	private float scale;

	public LightSource() {
		this(new AbsLightSource());
	}
	public LightSource(AbsLightSource lightSource) {
		this.lightSource = lightSource;
		this.position = new float[]{0, 0};
		this.region = new Sprite();
		this.scale = 1f;
		rebind();
	}



	public void rebind() {
		region.setPosition(position[0], position[1]);
		region.setScale(scale);
	}



	public void draw(Batch batch) {
		lightSource.draw(batch, region, region);
	}



	public void setResolution(Resolution resolution) {
		lightSource.setResolution(resolution);
		region.setTexture(new Texture(resolution.width, resolution.height, Pixmap.Format.RGBA8888));
		rebind();
	}
	public void setPosition(float x, float y) {
		this.position[0] = x;
		this.position[1] = y;
		rebind();
	}
	public void setScale(float scale) {
		this.scale = scale;
		rebind();
	}
	public void setPosition(float[] position2f) {
		setPosition(position2f[0], position2f[1]);
	}
	public float[] getPosition() {
		return position.clone();
	}
	public float getScale() {
		return scale;
	}



//	---------------------------------------- SET ---------------------------------------------
	public void setColor(Color color) {
		lightSource.setColor(color);
	}
	public void setCorrect(float correct) {
		lightSource.setCorrect(correct);
	}
	public void setCoeff(float coeff) {
		lightSource.setCoeff(coeff);
	}
	public void setRadius(float radiusMin, float radiusMax) {
		lightSource.setRadius(radiusMin, radiusMax);
	}
	public void setUmbra(float coeff, float power) {
		lightSource.setUmbra(coeff, power);
	}
	public void setAngles(float rot, float size) {
		lightSource.setAngles(rot, size);
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

}
