package net.tindersamurai.escapy.map.node;

public interface IEscapyNodeObserver {

	void nodeAdded(IEscapyNode parent, IEscapyNode node);

	void nodeRemoved(IEscapyNode parent, IEscapyNode node);

}