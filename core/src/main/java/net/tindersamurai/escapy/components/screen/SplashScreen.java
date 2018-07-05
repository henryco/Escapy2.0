package net.tindersamurai.escapy.components.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
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

	@Override
	public void show() {
		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(logoUrl));
		camera.setCameraPosition(sprite.getWidth() * .5f, sprite.getHeight() * .5f, true);

		// todo INITIALIZATION
		this.initialized.set(true);
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if ((time -= delta) <= 0 && initialized.get())
			setScreen(MenuScreen.class);

		else {
			batch.setProjectionMatrix(camera.update(camera::clear).getProjection());
			batch.begin();
			sprite.draw(batch, Math.abs(time / showTime));
			batch.end();
		}
	}

}
