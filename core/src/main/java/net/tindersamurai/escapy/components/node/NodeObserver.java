package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.GrInjector;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;

import javax.inject.Singleton;

@Provide @Singleton
public final class NodeObserver implements IEscapyNodeObserver {

	@Override @SuppressWarnings("unchecked")
	public void nodeAdded(IEscapyNode parent, IEscapyNode node) {
		System.out.println("NODE ADDED");
		if (parent.get() == null) {
			parent.set(GrInjector.getComponent(NodeData.class));
			System.out.println("node created");
		}
	}

	@Override
	public void nodeRemoved(IEscapyNode parent, IEscapyNode node) {

	}

}
