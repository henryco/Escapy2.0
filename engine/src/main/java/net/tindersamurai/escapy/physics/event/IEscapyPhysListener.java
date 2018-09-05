package net.tindersamurai.escapy.physics.event;

public interface IEscapyPhysListener {

	default void onPhysPositionUpdate(float x, float y) {}
	default void onPhysAngleUpdate(float angle) {}

}