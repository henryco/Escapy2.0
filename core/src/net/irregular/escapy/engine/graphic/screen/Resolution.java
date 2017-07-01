package net.irregular.escapy.engine.graphic.screen;

/**
 * @author Henry on 27/06/17.
 */
public class Resolution {

	public final int width, height;
	public final boolean bool;

	public Resolution(int width, int height, boolean bool) {
		this.width = width;
		this.height = height;
		this.bool = bool;
	}

	public Resolution(int width, int height) {
		this(width, height, false);
	}

	@Override
	public String toString() {
		return "Resolution{" +
				"width=" + width +
				", height=" + height +
				", bool=" + bool +
				'}';
	}
}
