package net.tindersamurai.escapy.components.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFrameBuffer;
import net.tindersamurai.escapy.graphic.screen.Resolution;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.atomic.AtomicBoolean;

@Provide("splash-screen")
public class SplashScreen extends EscapyScreenCore {

	private final AtomicBoolean initialized;
	private final IEscapyCamera camera;
	private final String logoUrl;
	private final float showTime;

	private Sprite sprite;
	private Batch batch;
	private float time;

	@Inject
	public SplashScreen(@Named("logo_splash") String logoUrl,
						IEscapyCamera camera,
						float showTime
	) {
		this.initialized = new AtomicBoolean(false);
		this.showTime = showTime;
		this.logoUrl = logoUrl;
		this.camera = camera;
		this.time = showTime;
	}

	private EscapyFBO fbo;
	private IEscapyCamera cam;

	@Override
	public void show() {
		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(logoUrl));
		camera.setCameraPosition(sprite.getWidth() * .5f, sprite.getHeight() * .5f, true);

		// todo INITIALIZATION
		this.initialized.set(true);
		int w = 1280;
		int h = 720;

		float ch = h;
		float dif = ch / ((float) Gdx.graphics.getHeight());
		float cw = ((float) Gdx.graphics.getWidth()) * dif;

		System.out.println("--" + cw + "-" + ch);

		fbo = new EscapyFrameBuffer(new Resolution(w, h));
		cam = new EscapyCamera(new Resolution((int)cw, (int)ch));
		cam.setCameraPosition(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.5f);
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		if ((time -= delta) <= 0 && initialized.get())
//			setScreen(MenuScreen.class);
//		else {
			fbo.begin(() -> {
				batch.setProjectionMatrix(camera.update(camera::clear).getProjection());
				batch.begin();
				Gdx.gl.glClearColor(1,0,0,0.5f);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				sprite.draw(batch, Math.abs(time / showTime));
				batch.end();
			});

			batch.setProjectionMatrix(cam.getProjection());
			batch.begin();
			float sx = (((float) Gdx.graphics.getWidth()) - fbo.getSprite().getWidth()) * 0.5f;
			float sy = (((float) Gdx.graphics.getHeight()) - fbo.getSprite().getHeight()) * 0.5f;
			fbo.getSprite().setPosition(sx, sy);
			fbo.getSprite().draw(batch);
			batch.end();
//		}
	}

}
