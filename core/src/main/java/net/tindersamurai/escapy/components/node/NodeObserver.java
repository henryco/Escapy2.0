package net.tindersamurai.escapy.components.node;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.model.plain.EmptyModel;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteProvider;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

import javax.inject.Singleton;

@Provide @Singleton @Log
public class NodeObserver implements IEscapyNodeObserver {


	@Override @SuppressWarnings("unchecked")
	public void nodeAdded(IEscapyNode parent, IEscapyNode node) {

		log.info("NODE ADDED: " + parent.getId() + " -> " + node.getId());
		fillMissingParents(parent);

		val nodeData = ((IEscapyNode<NodeData>) node).get();

		addChildModel(parent, nodeData);
		mergeModelWithPhys(nodeData);

	}

	@Override @SuppressWarnings("unchecked")
	public void nodeRemoved(IEscapyNode parent, IEscapyNode node) {

		log.info("NODE REMOVED: " + parent.get() + " -> " + node.getId());
		val nodeData = (NodeData) node.get();
		nodeData.dispose();

		val phys = nodeData.getPhys();
		if (phys == null) return;

		phys.setPhysListener(null);

		log.info("REMOVE FIXTURE FROM WORLD: " + phys.getFixture());
		val world = phys.getFixture().getBody().getWorld();
		world.destroyBody(phys.getFixture().getBody());
	}


	private static void fillMissingParents(IEscapyNode<NodeData> parent) {
		while (parent != null) {
			if (parent.get().getModel() != null)
				break;
			log.info("FILLING MISSING MODELS: " + parent);
			parent.get().setModel(new EmptyModel());
			parent = parent.getParent();
			log.info("MODEL FILLED: " + parent);
		}
	}

	private static void addChildModel(IEscapyNode<NodeData> parent, NodeData nodeData) {
		log.info("" + nodeData.getModel());
		parent.get().getModel().getNestedModels().add(nodeData.getModel());
	}

	private static void mergeModelWithPhys(NodeData nodeData) {
		val model = nodeData.getModel();
		val phys = nodeData.getPhys();

		log.info(model + " : " + phys);

		if (phys == null) return;
		if (model instanceof IEscapySpriteProvider) {
			log.info("SETTING UP PHYS LISTENER");
			phys.setPhysListener(new IEscapyPhysListener() {

				@Override
				public void onPhysPositionUpdate(final float x, final float y) {
					val m = (IEscapySpriteProvider) model;
					val padding = m.getBindPadding();
					m.apply(s -> {
						final float px = x - (s.getWidth() * 0.5f) + padding[0];
						final float py = y - (s.getHeight() * 0.5f) + padding[1];
						s.setPosition(px, py);
					});
				}

				@Override
				public void onPhysAngleUpdate(final float angle) {
					((IEscapySpriteProvider) model).apply(s -> s.setRotation(angle));
				}

			});
		}
	}
}