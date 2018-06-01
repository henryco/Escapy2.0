package net.irregular.escapy.map.node;

import lombok.val;

import java.util.Collection;

public interface IEscapyNode {

	String getId();

	<T> T get(String id);

	IEscapyNode set(Object content);

	IEscapyNode addNode(IEscapyNode node);

	IEscapyNode getNodeDirectly(String id);

	IEscapyNode getNode(String id);

	IEscapyNode getNode(String... id);

	IEscapyNode removeNode(String id);

	IEscapyNode getParent();

	Collection<IEscapyNode> getNodes();


	default String treeView() {
		return treeView("-- ");
	}

	default String treeView(String prefix) {
		return Visualizer.treeView(prefix, 1, this);
	}

	final class Visualizer {
		private static String treeView(String prefix, int pSize, IEscapyNode node) {

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