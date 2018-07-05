package net.tindersamurai.escapy.deprecated.modules.menu;

import net.tindersamurai.escapy.context.annotation.ScreenName;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenContext;
import net.tindersamurai.escapy.group.container.EscapyGroupContainer;
import net.tindersamurai.escapy.deprecated.modules.game.GameScreen;

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
		System.out.println(groupContainer.getLocationContainer().getLocations());

		groupContainer.getLocationContainer()
				.switchLocation("Location1")
				.switchSubLocation("SubOne");

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
