package net.tindersamurai.escapy.components.screen;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.components.stage.StageContainer;
import net.tindersamurai.escapy.context.game.Escapy;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.IEscapyModelRenderer;
import net.tindersamurai.escapy.map.node.IEscapyNode;

import javax.inject.Inject;

@Provide("game-screen") @Log
public class GameScreen extends EscapyScreenCore {

	{ log.info("instance: " + this.hashCode()); }

	private final IEscapyModelRenderer renderer;
	private final EscapyComponentParser parser;
	private final StageContainer container;

	@Inject
	public GameScreen(
			IEscapyModelRenderer renderer,
			EscapyComponentParser parser,
			StageContainer container
	) {
		this.renderer = renderer;
		this.parser = parser;
		this.container = container;
	}

	IEscapyModel model;

	@Override
	public void show() {
		System.out.println(Escapy.getGameContext().getConfigsFilePath());
		val file = container.getDefaultStage().getDefaultLocation().getFile().getUrl();
		System.out.println("FILE: " + file);
		IEscapyNode<NodeData> root = parser.parseComponent(file);
		model = root.get().getModel();
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
