package net.tindersamurai.escapy.physics;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public interface IEscapyPhysics extends Disposable {
	
	void updateWorld(float delta);

	World getWorld();

	float getPixelScale();
}