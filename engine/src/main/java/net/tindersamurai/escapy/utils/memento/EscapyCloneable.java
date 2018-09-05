package net.tindersamurai.escapy.utils.memento;

public interface EscapyCloneable<T> extends Cloneable {
	T copy();
}