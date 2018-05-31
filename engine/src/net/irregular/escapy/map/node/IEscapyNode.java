package net.irregular.escapy.map.node;

import java.util.Collection;

public interface IEscapyNode {

	<T> T get(String id);

	String getId();

	void addNode(IEscapyNode node);

	IEscapyNode getNode(String id);

	IEscapyNode getNode(String... id);

	IEscapyNode removeNode(String id);

	Collection<IEscapyNode> getNodes();
}