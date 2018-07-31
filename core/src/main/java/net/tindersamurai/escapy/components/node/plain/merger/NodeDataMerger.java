package net.tindersamurai.escapy.components.node.plain.merger;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.node.plain.data.INodeData;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteProvider;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

@Provide @Log
public class NodeDataMerger implements INodeDataMerger {

	@Override
	public void mergeNodeData(INodeData nodeData) {

		if (!(nodeData instanceof NodeData))
			throw new RuntimeException("Merger data wrong implementation, data type required: " + NodeData.class);

		val model = ((NodeData) nodeData).getModel();
		val phys = ((NodeData) nodeData).getPhys();

		log.info(model + " : " + phys);

		if (phys == null) return;
		if (model instanceof IEscapySpriteProvider) {
			log.info("SETTING UP PHYS LISTENER");
			val pixelScale = phys.getPhysicsManager().getPixelScale();
			val listener = new IEscapyPhysListener() {

				private Float lastX = null, lastY = null;
				private Float lastAngle = null;

				@Override
				public void onPhysPositionUpdate(final float x, final float y) {

					if (lastX != null && lastY != null)
						if (x == lastX && y == lastY)
							return;

					val m = (IEscapySpriteProvider) model;
					val padding = m.getBindPadding();
					m.apply(s -> {
						final float px = (x * pixelScale) - (s.getWidth() * 0.5f) + padding[0];
						final float py = (y * pixelScale) - (s.getHeight() * 0.5f) + padding[1];
						s.setPosition(px, py);
					});

					lastX = x;
					lastY = y;
				}

				@Override
				public void onPhysAngleUpdate(final float angle) {

					if (lastAngle != null && angle == lastAngle)
						return;
					((IEscapySpriteProvider) model).apply(s -> s.setRotation(angle));
					lastAngle = angle;
				}

			};
			phys.setPhysListener(listener);
			val position = phys.getFixture().getBody().getPosition();
			val angle = phys.getFixture().getBody().getAngle();
			listener.onPhysPositionUpdate(position.x, position.y);
			listener.onPhysAngleUpdate(angle);
		}
	}
}