package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.map.location.IEscapyLocation;
import net.tindersamurai.escapy.map.location.IEscapyLocationHandler;
import net.tindersamurai.escapy.map.node.IEscapyNode;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Provide @Singleton @Log
public class GameLocationHandler implements IEscapyLocationHandler {

	private final Map<String, IEscapyLocation> cache;
	private final Set<HandlerListener> listeners;
	private final EscapyComponentParser parser;

	private @Getter IEscapyLocation location;

	@Inject
	public GameLocationHandler(EscapyComponentParser parser) {
		this.listeners = new HashSet<>();
		this.cache = new HashMap<>();
		this.parser = parser;
	}

	@Override
	public void reset() {
		cache.clear();
	}

	@Override
	public boolean switchLocation(String file) {

		val location = cache.get(file);

		if (location == null) {
			IEscapyNode<NodeData> root = parser.parseComponent(file);
			if (root == null || root.get() == null)
				return false;
			this.location = root.get();
			cache.put(file, this.location);
		}

		else
			this.location = location;

		for (HandlerListener l : listeners)
			l.locationUpdate(file);

		return true;
	}

	@Override
	public void addListener(HandlerListener listener) {
		listeners.add(listener);
	}
}