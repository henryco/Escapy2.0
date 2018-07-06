package net.tindersamurai.escapy.components.screen;

import com.github.henryco.injector.GrInjector;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.components.stage.plain.LocationSetter;
import net.tindersamurai.escapy.components.stage.plain.StageInfo;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.map.location.IEscapyLocation;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.IEscapyModelRenderer;

import javax.inject.Inject;

@Provide("game-screen") @Log
public class GameScreen extends EscapyScreenCore {

	{ log.info("instance: " + this.hashCode()); }

	private final IEscapyModelRenderer renderer;
	private final LocationSetter locationSetter;

	@Inject
	public GameScreen(
			IEscapyModelRenderer renderer,
			LocationSetter locationSetter
	) {
		this.locationSetter = locationSetter;
		this.renderer = renderer;
	}

	// todo fixme remove
	private IEscapyModel model;

	@Override
	public void show() {
		// get location directly from DI Container
		NodeData data = ((NodeData) GrInjector.getComponent(IEscapyLocation.class));
		model = data.getModel();
	}

	@Override
	public void render(float delta) {
		renderer.render(model, delta);
	}

	@Override
	public void resize(int width, int height) {
		// todo
	}
}
