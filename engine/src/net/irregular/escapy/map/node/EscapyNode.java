package net.irregular.escapy.map.node;

import lombok.val;
import java.util.*;

public class EscapyNode implements IEscapyNode {

	private final Map<String, IEscapyNode> cache;
	private final Map<String, IEscapyNode> nodes;
	private final Map<IEscapyNode, String> keys;
	private final Set<IEscapyNode> supervisors;
	private final Set<IEscapyNode> cached;
	private final String id;

	private IEscapyNode parent;
	private Object content;

	/**
	 * @param content saved content
	 * @param id might be NULL
	 */
	public EscapyNode(Object content, String id) {
		this.id = id == null ? UUID.randomUUID().toString() : id;
		this.supervisors = new HashSet<>();
		this.cached = new HashSet<>();
		this.nodes = new HashMap<>();
		this.cache = new HashMap<>();
		this.keys = new HashMap<>();
		this.content = content;
	}

	public EscapyNode(Object content) {
		this(content, null);
	}

	public EscapyNode() {
		this(null);
	}

	@Override
	public <T> T get(String id) {
		//noinspection unchecked
		return (T) content;
	}

	@Override
	public IEscapyNode set(Object content) {
		this.content = content;
		return this;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public IEscapyNode addNode(IEscapyNode node) {
		((EscapyNode) node).parent = this;
		nodes.put(node.getId(), node);
		return this;
	}

	@Override
	public IEscapyNode getNodeDirectly(String id) {
		if (id == null) return null;
		return nodes.get(id);
	}

	@Override
	public IEscapyNode getNode(String id) {

		if (id == null) return null;
		id = id.replace(" ", "");

		val node = cache.get(id);
		if (node != null)
			return node;

		val ids = id.split(":");

		val result = getNode(ids);
		saveToCache(id, (EscapyNode) result);

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

		if (id == null) return null;
		id = id.replace(" ", "");

		EscapyNode node = ((EscapyNode) nodes.remove(id));
		if (node == null) {

			val ids = id.split(":");
			if (ids.length <= 1) return null;

			val search_set = new String[ids.length - 1];
			val search_id = ids[ids.length - 1];

			for (int i = 0; i < ids.length - 1; i++)
				search_set[i] = ids[i];

			val from = getNode(search_set);
			node = (EscapyNode) from.removeNode(search_id);

			if (node == null) return null;
		}

		for (val c : node.cached) {
			if (((EscapyNode) c).parent != null)
				((EscapyNode) c).parent.removeNode(c.getId());
		}
		node.cached.clear();

		for (val supervisor : node.supervisors) {
			val key = node.keys.get(supervisor);
			((EscapyNode) supervisor).cache.remove(key);
		}
		node.supervisors.clear();
		node.parent = null;
		return node;
	}

	@Override
	public IEscapyNode getParent() {
		return parent;
	}

	@Override
	public Collection<IEscapyNode> getNodes() {
		return nodes.values();
	}

	@Override
	public int hashCode() {
		return super.hashCode() + id.hashCode();
	}

	@Override
	public String toString() {
		return "EscapyNode{" +
				" keys=" + keys +
				", content=" + content +
				", parent=" + (parent == null ? null : parent.getId()) +
				", id='" + id + '\'' +
				'}';
	}

	private void saveToCache(String id, EscapyNode result) {
		if (result == null) return;

		cache.put(id, result);
		result.supervisors.add(this);
		result.keys.put(this, id);

		EscapyNode parent = result;
		while ((parent = (EscapyNode) parent.parent) != null) {
			parent.cached.add(result);
		}
	}
}