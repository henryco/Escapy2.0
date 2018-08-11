package net.tindersamurai.escapy.components.control.plain.phys;

import lombok.val;
import net.tindersamurai.escapy.components.control.plain.CoreCharacterListener;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;


public class PhysCharacterListener extends
		CoreCharacterListener<IEscapyPhysObject>
		implements IPhysListener {

	private final float speed;
	private final float run;
	private final float sit;

	private long timestamp;
	private float mv_speed;

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
		this.timestamp = System.nanoTime();
	}

	@Override
	public void onInteract() {
		System.out.println("interact");

 	}

	@Override
	public void onMoveLeft() {
		applyForceLR(-1);
		mv_speed = speed;
		System.out.println("left");
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
		for (val d : getUserData()) {
			if (!d.isGrounded()) continue;
			val body = d.getMainFixture().getBody();
			body.setLinearVelocity(Math.signum(sign) * mv_speed, 0);
			body.setAwake(true);
		}
	}
}