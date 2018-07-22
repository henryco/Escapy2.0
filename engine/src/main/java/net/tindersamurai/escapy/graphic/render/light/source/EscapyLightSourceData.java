package net.tindersamurai.escapy.graphic.render.light.source;

import com.badlogic.gdx.graphics.Color;
import lombok.Data;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.screen.Resolution;

@Data @Log public class EscapyLightSourceData {

	private Integer[] resolution; // w, h

	private Float[] position = { 0f, 0f }; // x, y
	private Float[] radius = { 0f, 1f }; // min, max
	private Float[] umbra = { 0f, 1f }; // coeff, power
	private Float[] angle = { 0f, 0f }; // rot, size

	private Float coefficient = 1f;
	private Float correction = 0f;
	private Float alpha = 1f;
	private Float scale = 1f;
	private Color color = Color.WHITE;

	public LightSource initialize (LightSource l) {

		if (resolution != null) {
			l.setResolution(new Resolution(
					resolution[0], resolution[1]
			));
		}

		if (position != null) l.setPosition(position[0], position[1]);
		if (radius != null) l.setRadius(radius[0], radius[1]);
		if (umbra != null) l.setUmbra(umbra[0], umbra[1]);
		if (angle != null) l.setAngles(angle[0], angle[1]);

		if (coefficient != null) l.setCoeff(coefficient);
		if (correction != null) l.setCorrect(correction);
		if (alpha != null) l.setAlpha(alpha);
		if (scale != null) l.setScale(scale);
		if (color != null) l.setColor(color);

		log.info(this.toString());
		return l;
	}

}