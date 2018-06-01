package net.irregular.escapy.map.node;

import lombok.val;

import java.util.Collection;

public interface IEscapyNode<P> {

	String getId();

	<T extends P> T get(String id);

	IEscapyNode<P> set(P content);

	IEscapyNode<P> addNode(IEscapyNode<P> node);

	IEscapyNode<P> getNodeDirectly(String id);

	IEscapyNode<P> getNode(String id);

	IEscapyNode<P> getNode(String ... id);

	IEscapyNode<P> removeNode(String id);

	IEscapyNode<P> getParent();

	Collection<IEscapyNode<P>> getNodes();

	void setObserver(IEscapyNodeObserver observer);


	default String treeView() {
		return treeView("-- ");
	}

	default String treeView(String prefix) {
		return Visualizer.treeView(prefix, 1, this);
	}

	final class Visualizer {
		private static String treeView(String prefix, int pSize, IEscapyNode<?> node) {

			val pr = new StringBuilder();
			for (int i = 0; i < pSize - 1; i++)
				pr.append(prefix);

			val id = new StringBuilder(prefix + pr +  ": " + node.getId());
			for (val n : node.getNodes())
				id.append("\n").append(treeView(prefix, pSize + 1, n));

			return id.toString();
		}
	}


}