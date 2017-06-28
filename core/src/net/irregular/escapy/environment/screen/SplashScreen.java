package net.irregular.escapy.environment.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.irregular.escapy.engine.env.EscapyScreen;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;


/**
 * @author Henry on 28/06/17.
 */
public class SplashScreen implements EscapyScreen {

	private final String logoUrl;
	private final EscapyCamera camera;
	private final Batch batch;


	private Sprite sprite;
	private Game game;



	public SplashScreen(String logoUrl, EscapyCamera camera, Batch batch) {
		this.logoUrl = logoUrl;
		this.camera = camera;
		this.batch = batch;
	}

	@Override
	public void show() {

		sprite = new Sprite(new Texture(logoUrl));
		camera.setCameraPosition(sprite.getWidth() * .5f, sprite.getHeight() * .5f, true);
	}

	@Override
	public void render(float delta) {

		batch.setProjectionMatrix(camera.update(camera::clear).getProjection());
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}



	@Override public void resize(int width, int height) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
	@Override public void dispose() {}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}
}
