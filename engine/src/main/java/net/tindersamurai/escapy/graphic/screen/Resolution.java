package net.tindersamurai.escapy.graphic.screen;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Henry on 27/06/17.
 */
@Data @AllArgsConstructor
public final class Resolution {

	public final int width, height;
	public final boolean bool;

	public Resolution(int width, int height) {
		this(width, height, false);
	}

}
