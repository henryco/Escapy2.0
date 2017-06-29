package net.irregular.escapy.engine.graphic.screen;

/**
 * @author Henry on 27/06/17.
 */
public class Resolution {

	public static final int DEFAULT_WIDTH = 1280;
	public static final int DEFAULT_HEIGHT = 720;

	public final int width, height;
	public final boolean flip;

	public Resolution(int width, int height, boolean flip) {
		this.width = width;
		this.height = height;
		this.flip = flip;
	}

	public Resolution(int width, int height) {
		this(width, height, false);
	}

	@Override
	public String toString() {
		return "Resolution{" +
				"width=" + width +
				", height=" + height +
				", flip=" + flip +
				'}';
	}
}
