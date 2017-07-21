package net.irregular.escapy.environment.main.menu;

import net.irregular.escapy.engine.env.context.annotation.ScreenName;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.screen.EscapyScreenContext;
import net.irregular.escapy.engine.group.container.EscapyGroupContainer;
import net.irregular.escapy.environment.main.game.GameScreen;

/**
 * @author Henry on 28/06/17.
 */
@ScreenName("menu_screen")
public class MenuScreen implements EscapyScreen {

	private final EscapyGroupContainer groupContainer;
	private EscapyScreenContext context;

	public MenuScreen(EscapyGroupContainer groupContainer) {
		this.groupContainer = groupContainer;
	}



	@Override
	public void show() {

		System.out.println("MENU SCREEN");
		System.out.println(groupContainer.getMapContainer().getLocations());
		groupContainer.getMapContainer().switchLocation("Location1");

		context.setScreen(GameScreen.class);
	}



	@Override
	public void render(float delta) {

	}



	@Override
	public void resize(int width, int height) {

	}


	@Override
	public void setScreenContext(EscapyScreenContext screenContext) {
		this.context = screenContext;
	}
}
