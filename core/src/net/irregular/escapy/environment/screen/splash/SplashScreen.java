package net.irregular.escapy.environment.screen.splash;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.irregular.escapy.environment.camera.EscapyCamera;


/**
 * @author Henry on 28/06/17.
 */
public class SplashScreen implements Screen {

	private final String logoUrl;
	private final EscapyCamera camera;

	private Batch batch;
	private Sprite sprite;

	public SplashScreen(String logoUrl, EscapyCamera camera) {
		this.logoUrl = logoUrl;
		this.camera = camera;
	}

	@Override
	public void show() {

		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(logoUrl));
		camera.setCameraPosition(sprite.getWidth() * .5f, sprite.getHeight() * .5f, true);
	}

	@Override
	public void render(float delta) {

		camera.update(camera::clear);
		batch.setProjectionMatrix(camera.getProjection());
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}



	@Override public void resize(int width, int height) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	@Override public void dispose() {}
}
