package net.tindersamurai.escapy.context.game;

import com.badlogic.gdx.Game;
import lombok.experimental.Delegate;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.context.annotation.meta.AnnotationProcessor;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 28/06/17.
 * Game context class, for using inside main module initialuzation ONLY!
 * In other cases use it as {@link EscapyScreenContext}
 */ @Log
public abstract class EscapyGame extends Game implements EscapyScreenContext, EscapyGameContext {

	private final Map<String, EscapyScreen> screenMap;
	private final Map<Class<? extends EscapyScreen>, EscapyScreen> classEscapyScreenMap;

	private final @Delegate EscapyGameContext gameContext;

	/**
	 * @author Henry on 28/06/17.
	 * Game context class, for using inside main module initialuzation ONLY!
	 * In other cases use it as {@link EscapyScreenContext}
	 */
	protected EscapyGame(Collection<EscapyScreen> escapyScreens) {
		this(escapyScreens, new EscapyGameContextConfiguration() {});
	}

	/**
	 * @author Henry on 28/06/17.
	 * Game context class, for using inside main module initialuzation ONLY!
	 * In other cases use it as {@link EscapyScreenContext}
	 */
	protected EscapyGame(Collection<EscapyScreen> escapyScreens,
						 EscapyGameContextConfiguration contextConfiguration) {
		super();
		screenMap = new HashMap<>();
		classEscapyScreenMap = new HashMap<>();
		AnnotationProcessor processor = contextConfiguration.getAnnotationProcessor();
		escapyScreens.forEach(escapyScreen -> {
			processor.processScreenMap(escapyScreen, screenMap);
			classEscapyScreenMap.put(escapyScreen.getClass(), escapyScreen);
		});

		Escapy.getInstance().setContextConfiguration(contextConfiguration);
		this.gameContext = contextConfiguration;
	}


	protected abstract EscapyScreen getInitialScreen();

	@Override
	public EscapyGame getGame() {
		return this;
	}

	@Override
	public final void create() {
		this.setScreen(getInitialScreen());
	}

	@Override
	public EscapyScreen getScreen(String name) {
		return screenMap.get(name);
	}

	@Override
	public EscapyScreen getScreen(Class<? extends EscapyScreen> screenClass) {
		return classEscapyScreenMap.get(screenClass);
	}

	@Override
	public void setScreen(String name) {
		this.setScreen(this.getScreen(name));
	}

	@Override
	public void setScreen(Class<? extends EscapyScreen> screenClass) {
		this.setScreen(this.getScreen(screenClass));
	}

	@Override
	public void setScreen(EscapyScreen screen) {
		log.info(screen.getClass().getSimpleName());
		screen.setScreenContext(this);
		super.setScreen(screen);
	}

}
