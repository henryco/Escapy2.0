package net.tindersamurai.escapy.graphic.screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

/**
 * @author Henry on 27/06/17.
 */
@Value @AllArgsConstructor
public final class Resolution {

	public float width, height;
	public boolean bool;

	public Resolution(float width, float height) {
		this(width, height, false);
	}

}
