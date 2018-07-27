package net.tindersamurai.escapy.physics;

import com.badlogic.gdx.utils.Disposable;

public interface IEscapyPhysics extends Disposable {
	
	void updateWorld(float delta);
}