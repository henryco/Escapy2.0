package net.tindersamurai.escapy.components.control.plain.phys;

import com.badlogic.gdx.math.Vector2;
import lombok.val;
import net.tindersamurai.escapy.components.control.plain.CoreCharacterListener;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;


public class PhysCharacterListener extends
		CoreCharacterListener<IEscapyPhysObject>
		implements IPhysListener {

	private Vector2[] lastPosition;

	private final float speed;
	private final float run;
	private final float sit;

	private float mv_speed;
	private boolean moved;

	public PhysCharacterListener(
			float speed,
			float run,
			float sit
	) {
		this.speed = speed;
		this.run = run;
		this.sit = sit;
	}

	@Override
	protected Class<IEscapyPhysObject> getDataType() {
		return IEscapyPhysObject.class;
	}

	@Override
	public void onUpdate(float delta) {
		if (moved) {
			val objects = getUserData();
			for (int i = 0; i < objects.length; i++) {
				val object = objects[i];
				if (!object.isGrounded()) continue;

				val body = object.getMainFixture().getBody();
				body.setLinearVelocity(0, 0);
				body.getPosition().set(lastPosition[i]);
				body.setAwake(true);
			}
		}
		moved = false;
	}

	@Override
	public void onInteract() {
		System.out.println("interact");

 	}

	@Override
	public void onMoveLeft() {
		applyForceLR(-1);
		mv_speed = speed;

	}

	@Override
	public void onMoveRight() {
		applyForceLR(1);
		mv_speed = speed;

	}

	@Override
	public void onRun() {
		mv_speed = run;

	}

	@Override
	public void onSit() {
		mv_speed = sit;

	}

	private void applyForceLR(int sign) {
		val objects = getUserData();
		lastPosition = new Vector2[objects.length];
		for (int i = 0; i < objects.length; i++) {
			val d = objects[i];
			if (!d.isGrounded()) continue;

			val body = d.getMainFixture().getBody();
			body.setLinearVelocity(Math.signum(sign) * mv_speed, 0);
			body.setAwake(true);

			lastPosition[i] = body.getPosition();
		}
		moved = true;
	}
}