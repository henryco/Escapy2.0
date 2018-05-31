package net.irregular.escapy.map.node;

public interface EscapyNode {

	<T> T get(String id);

	void addChild(EscapyNode node);

	EscapyNode getChild(String id);

	EscapyNode removeChild(String id);
}