package net.tindersamurai.escapy.map.location;

public interface IEscapyLocationHandler {

	interface HandlerListener {
		void locationUpdate(String file);
	}

	void addListener(HandlerListener listener);

	boolean switchLocation(String file);

	void reset();

	IEscapyLocation getLocation();
}