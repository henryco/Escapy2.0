package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.RootModel;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.node.EscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("location")
public class LocationFactory  {

	private final IEscapyNodeObserver nodeObserver;
	private final IEscapyCamera viewPortCamera;

	@Inject
	public LocationFactory(
			@Named("final-camera") IEscapyCamera viewPortCamera,
			IEscapyNodeObserver nodeObserver
	) {
		this.nodeObserver = nodeObserver;
		this.viewPortCamera = viewPortCamera;
	}

	@SafeVarargs @EscapyComponent("root")
	public final IEscapyNode<NodeData> root (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return new EscapyNode<NodeData> (new NodeData() {{
			setModel(new RootModel(viewPortCamera));
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
	 */ @SafeVarargs @EscapyComponent("main-node")
	public final IEscapyNode<NodeData> mainNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return node("MainNode", null, nodes);
	}

	/**
	 *	This is just wrapper
	 */ @SafeVarargs @EscapyComponent("mask-node")
	public final IEscapyNode<NodeData> maskNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
	 	return node("MaskNode", null, nodes);
	}

	/**
	 *	This is just wrapper
	 */ @SafeVarargs @EscapyComponent("light-node")
	public final IEscapyNode<NodeData> lightNode (
			@Arg("nodes") IEscapyNode<NodeData> ... nodes
	) {
		return node("LightNode", null, nodes);
	}
}