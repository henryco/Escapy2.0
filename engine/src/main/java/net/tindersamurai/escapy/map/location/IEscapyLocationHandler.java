package net.tindersamurai.escapy.map.location;

import net.tindersamurai.escapy.map.node.IEscapyNode;

public interface IEscapyLocationHandler {

	interface HandlerListener {
		void locationUpdate(String file);
	}

	void addListener(HandlerListener listener);

	boolean switchLocation(String file);

	void reset();

	IEscapyLocation getLocation();
	IEscapyNode getLocationNode();
}