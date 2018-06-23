package net.irregular.escapy.utils;

/**
 * @author Henry on 27/06/17.
 */
public interface SimpleObserver<T> {

	void stateUpdated(T state);
}
