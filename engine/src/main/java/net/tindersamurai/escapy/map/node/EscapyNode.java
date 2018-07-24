package net.tindersamurai.escapy.map.node;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import java.util.*;

public class EscapyNode<T> implements IEscapyNode<T> {

	private final Map<String, IEscapyNode<T>> cache;
	private final Map<String, IEscapyNode<T>> nodes;
	private final Map<IEscapyNode<T>, String> keys;
	private final Set<IEscapyNode<T>> supervisors;
	private final Set<IEscapyNode<T>> cached;
	private final String id;

	private @Setter IEscapyNodeObserver observer;
	private @Getter IEscapyNode<T> parent;
	private T content;

	/**
	 * @param content saved content
	 * @param id might be NULL
	 */
	public EscapyNode(T content, String id) {
		this.id = id == null ? UUID.randomUUID().toString() : id;
		this.supervisors = new HashSet<>();
		this.cached = new HashSet<>();
		this.nodes = new HashMap<>();
		this.cache = new HashMap<>();
		this.keys = new HashMap<>();
		this.content = content;
	}

	public EscapyNode(T content) {
		this(content, null);
	}

	public EscapyNode() {
		this(null);
	}

	@Override
	public <Z extends T> Z get() {
		//noinspection unchecked
		return (Z) content;
	}

	@Override
	public IEscapyNode<T> set(T content) {
		this.content = content;
		return this;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public IEscapyNode<T> addNode(IEscapyNode<T> node) {
		((EscapyNode) node).parent = this;
		node.setObserver(this.observer);
		nodes.put(node.getId(), node);

		if (observer != null)
			this.observer.nodeAdded(this, node);

		return this;
	}

	@Override
	public IEscapyNode<T> getNodeDirectly(String id) {
		if (id == null) return null;
		return nodes.get(id);
	}

	@Override
	public IEscapyNode<T> getNode(String id) {

		if (id == null) return null;
		id = id.replace(" ", "");

		val node = cache.get(id);
		if (node != null)
			return node;

		val ids = id.split(":");

		val result = getNode(ids);
		saveToCache(id, (EscapyNode<T>) result);

		return result;
	}

	@Override
	public IEscapyNode<T> getNode(String ... id) {

		EscapyNode<T> node = this;
		for (val i: id)
			if ((node = (EscapyNode<T>) node.nodes.get(i)) == null)
				return null;
		return node;
	}

	@Override
	public IEscapyNode<T> removeNode(String id) {

		if (id == null) return null;
		id = id.replace(" ", "");

		EscapyNode<T> node = ((EscapyNode<T>) nodes.remove(id));
		if (node == null) {

			val ids = id.split(":");
			if (ids.length <= 1) return null;

			val search_set = new String[ids.length - 1];
			val search_id = ids[ids.length - 1];

			System.arraycopy(ids, 0, search_set, 0, ids.length - 1);

			val from = getNode(search_set);
			node = (EscapyNode<T>) from.removeNode(search_id);

			if (node == null) return null;
		}

		for (val c : node.cached) {
			if (((EscapyNode<T>) c).parent != null)
				((EscapyNode<T>) c).parent.removeNode(c.getId());
		}
		node.cached.clear();

		for (val supervisor : node.supervisors) {
			val key = node.keys.get(supervisor);
			((EscapyNode<T>) supervisor).cache.remove(key);
		}
		node.supervisors.clear();
		node.parent = null;

		if (observer != null)
			observer.nodeRemoved(this, node);

		return node;
	}

	@Override
	public Collection<IEscapyNode<T>> getNodes() {
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
				", observer=" + observer +
				", id='" + id + '\'' +
				'}';
	}

	private void saveToCache(String id, EscapyNode<T> result) {
		if (result == null) return;

		cache.put(id, result);
		result.supervisors.add(this);
		result.keys.put(this, id);

		EscapyNode<T> parent = result;
		while ((parent = (EscapyNode<T>) parent.parent) != null) {
			parent.cached.add(result);
		}
	}
}