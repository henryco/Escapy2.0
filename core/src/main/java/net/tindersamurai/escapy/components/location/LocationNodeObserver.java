package net.tindersamurai.escapy.components.location;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;

import javax.inject.Singleton;

@Provide @Singleton
public class LocationNodeObserver implements IEscapyNodeObserver {

	@Override
	public void nodeAdded(IEscapyNode parent, IEscapyNode node) {
		System.out.println("NODE ADDED");
	}

	@Override
	public void nodeRemoved(IEscapyNode parent, IEscapyNode node) {

	}

}
