package net.irregular.escapy.engine.env.context.game;

import com.badlogic.gdx.Game;
import net.irregular.escapy.engine.env.context.annotation.meta.AnnotationProcessor;
import net.irregular.escapy.engine.env.context.game.configuration.DefaultEscapyGameContextConfiguration;
import net.irregular.escapy.engine.env.context.game.configuration.EscapyGameContextConfiguration;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.screen.EscapyScreenContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 28/06/17.
 * Game context class, for using inside main module initialuzation ONLY!
 * In other cases use it as {@link EscapyScreenContext}
 */
public abstract class EscapyGameContext extends Game implements EscapyScreenContext {

	private final Map<String, EscapyScreen> screenMap;
	private final Map<Class<? extends EscapyScreen>, EscapyScreen> classEscapyScreenMap;



	/**
	 * @author Henry on 28/06/17.
	 * Game context class, for using inside main module initialuzation ONLY!
	 * In other cases use it as {@link EscapyScreenContext}
	 */
	protected EscapyGameContext(Collection<EscapyScreen> escapyScreens) {
		this(escapyScreens, new DefaultEscapyGameContextConfiguration());
	}

	/**
	 * @author Henry on 28/06/17.
	 * Game context class, for using inside main module initialuzation ONLY!
	 * In other cases use it as {@link EscapyScreenContext}
	 */
	protected EscapyGameContext(Collection<EscapyScreen> escapyScreens,
								EscapyGameContextConfiguration contextConfiguration) {
		super();
		screenMap = new HashMap<>();
		classEscapyScreenMap = new HashMap<>();
		AnnotationProcessor processor = contextConfiguration.getAnnotationProcessor();
		escapyScreens.forEach(escapyScreen -> {
			processor.processScreenMap(escapyScreen, screenMap);
			classEscapyScreenMap.put(escapyScreen.getClass(), escapyScreen);
		});

	}


	protected abstract EscapyScreen getInitialScreen();



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
	public void setScreen(EscapyScreen screen) {
		screen.setScreenContext(this);
		super.setScreen(screen);
	}

	@Override
	public void setScreen(String name) {
		this.setScreen(this.getScreen(name));
	}

	@Override
	public void setScreen(Class<? extends EscapyScreen> screenClass) {
		this.setScreen(this.getScreen(screenClass));
	}

}
