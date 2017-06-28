package net.irregular.escapy.environment.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.screen.EscapyScreenContext;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;

import javax.inject.Inject;


/**
 * @author Henry on 28/06/17.
 */
public class SplashScreen implements EscapyScreen {

	private final String logoUrl;
	private final EscapyCamera camera;
	private final Batch batch;
	private final float showTime;
	private final String nextScreenName;
	private final String screenName;

	private EscapyScreenContext screenContext;
	private Sprite sprite;
	private float time;


	@Inject
	public SplashScreen(String logoUrl,
						EscapyCamera camera,
						Batch batch,
						float timeSec,
						String screenName,
						String nextScreenName) {

		this.nextScreenName = nextScreenName;
		this.screenName = screenName;
		this.showTime = timeSec;
		this.logoUrl = logoUrl;
		this.camera = camera;
		this.batch = batch;
		this.time = timeSec;
	}


	@Override
	public void show() {
		sprite = new Sprite(new Texture(logoUrl));
		camera.setCameraPosition(sprite.getWidth() * .5f, sprite.getHeight() * .5f, true);
	}


	@Override
	public void render(float delta) {
		if ((time -= delta) <= 0) screenContext.setScreen(screenContext.getScreen(MenuScreen.class));
		else {
			batch.setProjectionMatrix(camera.update(camera::clear).getProjection());
			batch.begin();
			sprite.draw(batch, time / showTime);
			batch.end();
		}
	}


	@Override
	public void setScreenContext(EscapyScreenContext screenContext) {
		this.screenContext = screenContext;
	}



	@Override public void resize(int width, int height) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	@Override public void dispose() {}

}
