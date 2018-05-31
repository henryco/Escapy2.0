package net.irregular.escapy.map.node;

import lombok.EqualsAndHashCode;
import lombok.val;

import java.util.*;

@EqualsAndHashCode
public class EscapyNode implements IEscapyNode {

	private final Map<String, IEscapyNode> cache;
	private final Map<String, IEscapyNode> nodes;
	private final Map<IEscapyNode, String> keys;
	private final Set<IEscapyNode> supervisors;
	private final Object content;
	private final String id;

	/**
	 * @param content saved content
	 * @param id might be NULL
	 */
	public EscapyNode(Object content, String id) {
		this.id = id == null ? UUID.randomUUID().toString() : id;
		this.supervisors = new HashSet<>();
		this.nodes = new HashMap<>();
		this.cache = new HashMap<>();
		this.keys = new HashMap<>();
		this.content = content;
	}

	@Override
	public <T> T get(String id) {
		return (T) content;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void addNode(IEscapyNode node) {
		nodes.put(node.getId(), node);
	}

	@Override
	public IEscapyNode getNode(String id) {

		if (id == null) return null;

		val node = cache.get(id);
		if (node != null)
			return node;

		val ids = id.split(":");
		for (int i = 0; i < ids.length; i++) {
			ids[i] = ids[i].trim();
		}

		val result = getNode(ids);
		if (result != null) {
			cache.put(id, result);
			((EscapyNode) result).supervisors.add(this);
			((EscapyNode) result).keys.put(this, id);
		}

		return result;
	}

	@Override
	public IEscapyNode getNode(String ... id) {

		EscapyNode node = this;
		for (val i: id)
			if ((node = (EscapyNode) node.nodes.get(i)) == null)
				return null;
		return node;
	}

	@Override
	public IEscapyNode removeNode(String id) {

		val node = ((EscapyNode) nodes.remove(id));
		if (node == null) return null;

		for (val supervisor : node.supervisors) {
			val key = node.keys.get(supervisor);
			((EscapyNode) supervisor).cache.remove(key);
		}
		node.supervisors.clear();
		return node;
	}

	@Override
	public Collection<IEscapyNode> getNodes() {
		return nodes.values();
	}
}
