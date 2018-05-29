package net.irregular.escapy.utils.memento;

public interface EscapyCloneable<T> extends Cloneable {
	T copy();
}