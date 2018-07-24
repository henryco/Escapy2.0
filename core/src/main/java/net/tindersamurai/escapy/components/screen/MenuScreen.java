package net.tindersamurai.escapy.components.screen;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.components.stage.plain.LocationSwitcher;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;

import javax.inject.Inject;

@Provide("menu-screen")
public class MenuScreen extends EscapyScreenCore {

	private final LocationSwitcher locationSetter;

	@Inject
	public MenuScreen(LocationSwitcher locationSetter) {
		this.locationSetter = locationSetter;
	}

	@Override
	public void show() {
		// todo
		locationSetter.reset();
		System.out.println(locationSetter.getStageInfo());
		setScreen(LoadingScreen.class);
	}

	@Override
	public void render(float delta) {
		// todo SOME MENU
	}

}
