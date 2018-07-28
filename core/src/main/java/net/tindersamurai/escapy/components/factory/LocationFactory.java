package net.tindersamurai.escapy.components.factory;

import com.badlogic.gdx.math.Vector2;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.RootModel;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.map.node.EscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;
import net.tindersamurai.escapy.physics.EscapyPhysWorld;

import javax.inject.Inject;
import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("location")
public class LocationFactory  {

	private final IEscapyNodeObserver nodeObserver;

	@Inject
	public LocationFactory(IEscapyNodeObserver nodeObserver) {
		this.nodeObserver = nodeObserver;
	}

	@SafeVarargs @EscapyComponent("root")
	public final IEscapyNode<NodeData> root (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return new EscapyNode<NodeData> (new NodeData() {{
			setModel(new RootModel());
		}}, "root" ) {{
			setObserver(nodeObserver);
			for (IEscapyNode<NodeData> node : nodes)
				addNode(node);
		}};
	}

	@SafeVarargs @EscapyComponent("node")
	public final IEscapyNode<NodeData> node (
			@Arg("id") String id,
			@Arg("data") NodeData data,
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return new EscapyNode<NodeData> (
				data == null ? new NodeData() : data, id
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
	 	return node("MaskNode", null, nodes);
	}

	/**
	 *	This is just wrapper
	 */ @SafeVarargs @EscapyComponent("lights")
	public final IEscapyNode<NodeData> lightNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return node("LightNode", null, nodes);
	}

	@SafeVarargs @EscapyComponent("physics")
	public final IEscapyNode<NodeData> physicsNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return node("PhysicsNode", null, nodes);
	}
}