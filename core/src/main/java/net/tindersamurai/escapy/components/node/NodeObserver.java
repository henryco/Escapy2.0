package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.model.plain.EmptyModel;
import net.tindersamurai.escapy.components.node.plain.merger.INodeDataMerger;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;

import javax.inject.Inject;
import javax.inject.Singleton;

@Provide @Singleton @Log
public class NodeObserver implements IEscapyNodeObserver {

	private final INodeDataMerger merger;

	@Inject
	public NodeObserver(INodeDataMerger merger) {
		this.merger = merger;
	}

	@Override @SuppressWarnings("unchecked")
	public void nodeAdded(IEscapyNode parent, IEscapyNode node) {

		log.info("NODE ADDED: " + parent.getId() + " -> " + node.getId());
		fillMissingParents(parent);

		val nodeData = ((IEscapyNode<NodeData>) node).get();

		addChildModel(parent, nodeData);
		merger.mergeNodeData(nodeData);
	}

	@Override @SuppressWarnings("unchecked")
	public void nodeRemoved(IEscapyNode parent, IEscapyNode node) {

		log.info("NODE REMOVED: " + parent.get() + " -> " + node.getId());
		val nodeData = (NodeData) node.get();
		nodeData.dispose();

		val phys = nodeData.getPhys();
		if (phys == null) return;

		phys.setPhysListener(null);

		log.info("REMOVE FIXTURE FROM WORLD: " + phys.getFixture());
		val world = phys.getFixture().getBody().getWorld();
		world.destroyBody(phys.getFixture().getBody());
	}


	private static void fillMissingParents(IEscapyNode<NodeData> parent) {
		while (parent != null) {
			if (parent.get().getModel() != null)
				break;
			log.info("FILLING MISSING MODELS: " + parent);
			parent.get().setModel(new EmptyModel());
			parent = parent.getParent();
			log.info("MODEL FILLED: " + parent);
		}
	}

	private static void addChildModel(IEscapyNode<NodeData> parent, NodeData nodeData) {
		log.info("" + nodeData.getModel());
		parent.get().getModel().getNestedModels().add(nodeData.getModel());
	}
}