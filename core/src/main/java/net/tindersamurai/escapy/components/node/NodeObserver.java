package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.plain.EmptyModel;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;

import javax.inject.Singleton;

@Provide @Singleton @Log
public class NodeObserver implements IEscapyNodeObserver {


	@Override @SuppressWarnings("unchecked")
	public void nodeAdded(IEscapyNode parent, IEscapyNode node) {

		log.info("NODE ADDED: " + parent.getId() + " -> " + node.getId());
		IEscapyNode<NodeData> cParent = parent;
		while (cParent != null) {
			if (cParent.get().getModel() != null)
				break;
			cParent.get().setModel(new EmptyModel());
			cParent = cParent.getParent();
		}

		((IEscapyNode<NodeData>) parent)
				.get().getModel().getNestedModels()
				.add(((IEscapyNode<NodeData>) node).get().getModel());
	}

	@Override @SuppressWarnings("unchecked")
	public void nodeRemoved(IEscapyNode parent, IEscapyNode node) {
		((IEscapyNode<NodeData>) node).get().getModel().dispose();
		// todo
	}

}