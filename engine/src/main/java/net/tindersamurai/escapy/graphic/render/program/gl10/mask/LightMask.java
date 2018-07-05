package net.tindersamurai.escapy.graphic.render.program.gl10.mask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import net.tindersamurai.escapy.context.annotation.Dante;
import net.tindersamurai.escapy.context.annotation.EscapyAPI;

/**
 * @author Henry on 23/09/16.
 */
@EscapyAPI @Dante
public class LightMask {

	@EscapyAPI
	public static final int[] MULTIPLY = new int[]{GL30.GL_DST_COLOR, GL30.GL_ONE_MINUS_SRC_ALPHA};
	@EscapyAPI
	public static final int[] SEPIA = new int[]{GL30.GL_SRC_ALPHA, GL30.GL_DST_COLOR};
	@EscapyAPI
	public static final int[] SCREEN = new int[]{GL30.GL_ONE_MINUS_DST_COLOR, GL30.GL_ONE};
	@EscapyAPI
	public static final int[] LINEAR_DODGE = new int[]{GL30.GL_ONE, GL30.GL_ONE};


	private final OrthographicCamera camera;
	private final int width, height, x, y;
	private final int[] blendFunc;
	private final Color color;
	private final Batch batch;

	private Texture maskTexture;


	@EscapyAPI
	public LightMask(int x, int y, int width, int height) {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(true, width, height);
		this.batch = new SpriteBatch();
		this.color = new Color();
		this.blendFunc = new int[2];
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		setMaskFunc(MULTIPLY);
		setColor((60f / 255f), (60f / 255f), (60f / 255f), 1f);
	}

	@EscapyAPI
	public LightMask(int[] dim) {
		this(dim[0], dim[1], dim[2], dim[3]);
	}

	@EscapyAPI
	public LightMask(int width, int height) {
		this(0, 0, width, height);
	}


	public void initMaskTexture() {

		FrameBuffer tmp = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
		tmp.begin();
		Gdx.gl.glClearColor(this.color.r, this.color.g, this.color.b, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tmp.end();
		maskTexture = tmp.getColorBufferTexture();
	}


	public void setMaskFunc(int src, int dst) {
		blendFunc[0] = src;
		blendFunc[1] = dst;
	}

	public void setMaskFunc(int[] func) {
		setMaskFunc(func[0], func[1]);
	}

	public void setColor(float r, float g, float b, float a) {
		color.set(r, g, b, a);
		initMaskTexture();
	}

	public void setColor(int r, int g, int b, int a) {
		setColor(r / 255f, g / 255f, b / 255f, a / 255f);
	}

	@EscapyAPI
	public void setColor(int[] color) {
		setColor(color[0], color[1], color[2], color[3]);
	}

	@EscapyAPI
	public void renderMask(Texture target) {
		batch.setProjectionMatrix(camera.combined);

		int srcFunc = batch.getBlendSrcFunc();
		int dstFunc = batch.getBlendDstFunc();

		batch.begin();
		batch.draw(target, 0, 0);
		batch.end();

		batch.begin();
		batch.enableBlending();
		batch.setBlendFunction(blendFunc[0], blendFunc[1]);
		batch.draw(maskTexture, x, y);
		batch.disableBlending();
		batch.end();

		batch.begin();
		batch.setBlendFunction(srcFunc, dstFunc);
		batch.end();
	}

}
