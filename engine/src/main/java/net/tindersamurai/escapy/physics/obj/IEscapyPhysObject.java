package net.tindersamurai.escapy.physics.obj;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Disposable;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

public interface IEscapyPhysObject extends Disposable {

	void setPhysListener(IEscapyPhysListener listener);
	void setPhysicsManager(IEscapyPhysics physicsManager);

	Fixture getMainFixture();

	IEscapyPhysics getPhysicsManager();
	String getId();
}