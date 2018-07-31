package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
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

	private final Map<String, IEscapyNode<NodeData>> cache;
	private final Set<HandlerListener> listeners;
	private final EscapyComponentParser parser;

	private @Getter IEscapyLocation location;
	private @Getter IEscapyNode locationNode;

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

		val locationNode = cache.get(file);

		if (locationNode == null) {
			IEscapyNode<NodeData> root = parser.parseComponent(file);
			if (root == null || root.get() == null)
				return false;
			this.locationNode = root;
			this.location = root.get();
			cache.put(file, root);
		}

		else {
			this.location = locationNode.get();
			this.locationNode = locationNode;
		}

		for (HandlerListener l : listeners)
			l.locationUpdate(file);

		return true;
	}

	@Override
	public void addListener(HandlerListener listener) {
		listeners.add(listener);
	}
}