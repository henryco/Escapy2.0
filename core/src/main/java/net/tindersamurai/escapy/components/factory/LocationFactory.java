package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.RootModel;
import net.tindersamurai.escapy.components.node.NodeFactory;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.map.node.EscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;

import javax.inject.Inject;
import javax.inject.Singleton;

@Provide @Singleton @Log
@EscapyComponentFactory("location")
public class LocationFactory  {

	private final IEscapyNodeObserver nodeObserver;
	private final NodeFactory nodeFactory;

	@Inject
	public LocationFactory(
			IEscapyNodeObserver nodeObserver,
			NodeFactory nodeFactory
	) {
		this.nodeObserver = nodeObserver;
		this.nodeFactory = nodeFactory;
	}

	@SafeVarargs @EscapyComponent("root")
	public final IEscapyNode<NodeData> root (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return new EscapyNode<NodeData> (
				nodeFactory.data(new RootModel()), "root"
		) {{
			setObserver(nodeObserver);
			for (IEscapyNode<NodeData> node : nodes)
				if (node != null)
					addNode(node);
		}};
	}

	@EscapyComponent("constructor")
	public final void constructor(Object ... nothing) {
		log.info("Constructor call");
	}

	@SafeVarargs @EscapyComponent("node")
	public final IEscapyNode<NodeData> node (
			@Arg("id") String id,
			@Arg("data") NodeData data,
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return new EscapyNode<NodeData> (
				data == null ? nodeFactory.data() : data, id
		) {{
			setObserver(nodeObserver);
			for (IEscapyNode<NodeData> node : nodes)
				addNode(node);
		}};
	}

	/**
	 *	This is just wrapper
	 */ @SafeVarargs @EscapyComponent("main")
	public final IEscapyNode<NodeData> mainNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return node("MainNode", null, nodes);
	}

	/**
	 *	This is just wrapper
	 */ @SafeVarargs @EscapyComponent("masks")
	public final IEscapyNode<NodeData> maskNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
	 	return node("MasksNode", null, nodes);
	}

	/**
	 *	This is just wrapper
	 */ @SafeVarargs @EscapyComponent("lights")
	public final IEscapyNode<NodeData> lightNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return node("LightsNode", null, nodes);
	}

	@SafeVarargs @EscapyComponent("physics")
	public final IEscapyNode<NodeData> physicsNode (
			@Arg("data") NodeData data,
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return node("PhysicsNode", data, nodes);
	}
}