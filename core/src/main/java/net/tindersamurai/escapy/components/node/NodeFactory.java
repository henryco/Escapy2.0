package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.control.plain.animation.IAnimationListener;
import net.tindersamurai.escapy.components.control.plain.phys.IPhysListener;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.components.node.plain.merger.INodeDataMerger;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

import javax.inject.Inject;
import javax.inject.Singleton;

@Provide @Singleton @Log
@EscapyComponentFactory("node")
public class NodeFactory {

	private final INodeDataMerger merger;

	@Inject
	public NodeFactory(INodeDataMerger merger) {
		this.merger = merger;
		log.info("NodeFactory new instance");
	}

	@EscapyComponent("data")
	public final NodeData data(@Arg("args") Object ... args) {

		log.info("New NodeData");
		val data = NodeData.newInstance(merger);
		for (Object arg : args) {

			if (arg instanceof IEscapyModel)
				data.setModel((IEscapyModel) arg);

			if (arg instanceof IEscapyPhysObject)
				data.setPhys((IEscapyPhysObject) arg);

			if (arg instanceof IPhysListener)
				data.setPhysListener((IPhysListener) arg);

			if (arg instanceof IAnimationListener)
				data.setAnimationListener((IAnimationListener) arg);

		}

		return data;
	}

}