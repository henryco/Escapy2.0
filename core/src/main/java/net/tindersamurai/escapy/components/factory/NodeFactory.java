package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.node.NodeData;
import net.tindersamurai.escapy.components.node.NodeFactoryLogic;

import javax.inject.Inject;
import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("node")
public final class NodeFactory {

	private final NodeFactoryLogic logic;

	@Inject
	public NodeFactory(NodeFactoryLogic logic) {
		this.logic = logic;
	}

	@EscapyComponent("data")
	public final NodeData data(@Arg("args") Object ... args) {
		return logic.data(args);
	}

}