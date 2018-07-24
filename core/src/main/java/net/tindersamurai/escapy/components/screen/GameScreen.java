package net.tindersamurai.escapy.components.screen;

import com.github.henryco.injector.GrInjector;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.plain.light.LightSourceModel;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.components.stage.plain.LocationSwitcher;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.graphic.render.light.source.LightSource;
import net.tindersamurai.escapy.map.location.IEscapyLocation;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.IEscapyModelRenderer;
import net.tindersamurai.escapy.map.node.IEscapyNode;

import javax.inject.Inject;

@Provide("game-screen") @Log
public class GameScreen extends EscapyScreenCore {

	private final IEscapyModelRenderer renderer;
	private final LocationSwitcher locationSetter;

	@Inject
	public GameScreen(
			IEscapyModelRenderer renderer,
			LocationSwitcher locationSetter
	) {
		this.locationSetter = locationSetter;
		this.renderer = renderer;
	}

	// todo fixme remove
	private IEscapyModel model;
	private LightSource lightSource;

	@Override
	public void show() {
		// get location directly from DI Container
		NodeData data = ((NodeData) GrInjector.getComponent(IEscapyLocation.class));
		model = data.getModel();

		//noinspection unchecked
		IEscapyNode<NodeData> node = GrInjector.getComponent(IEscapyNode.class);
		log.info("Location tree structure: \n"+node.treeView());

		IEscapyNode<NodeData> lightNode = node.getNode("LightNode:Layer0LightPack:Layer0LightPackShift:LightType0:LightSource1");
		lightSource = ((LightSourceModel) lightNode.get().getModel()).getLightSource();

	}

	@Override
	public void render(float delta) {
		renderer.render(model, delta);
		lightSource.translate(1.5f, 0);
	}

	@Override
	public void resize(int width, int height) {
		// todo
	}
}
