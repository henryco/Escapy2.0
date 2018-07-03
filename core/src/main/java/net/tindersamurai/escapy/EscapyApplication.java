package net.tindersamurai.escapy;

import com.badlogic.gdx.ApplicationListener;
import com.github.henryco.injector.GrInjector;
import net.tindersamurai.escapy.context.game.EscapyGame;
import net.tindersamurai.escapy.components.MainModule;

public class EscapyApplication implements ApplicationListener {

	private EscapyGame escapyGameContext;

	@Override
	public void create() {


		GrInjector.addModules(MainModule.class);
		GrInjector.inject(this);

		escapyGameContext = GrInjector.getComponent(EscapyGame.class);
		escapyGameContext.create();
	}

	@Override
	public void resize(int width, int height) {
		escapyGameContext.resize(width, height);
	}

	@Override
	public void render() {
		escapyGameContext.render();
	}

	@Override
	public void pause() {
		escapyGameContext.pause();
	}

	@Override
	public void resume() {
		escapyGameContext.resume();
	}

	@Override
	public void dispose() {
		escapyGameContext.dispose();
	}

}