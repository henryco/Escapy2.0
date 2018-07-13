package net.tindersamurai.escapy.graphic.camera;

public interface IEscapyMemoCam extends IEscapyCamera {

	void save();

	void revert();

	default void safety(Runnable runnable) {
		save();
		runnable.run();
		revert();
	}
}