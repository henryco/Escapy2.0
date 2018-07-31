package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("node")
public class NodeFactory {

	@EscapyComponent("data")
	public final NodeData data(@Arg("args") Object ... args) {

		val data = NodeData.newInstance();
		for (Object arg : args) {

			if (arg instanceof IEscapyModel)
				data.setModel((IEscapyModel) arg);
			if (arg instanceof IEscapyPhysObject)
				data.setPhys((IEscapyPhysObject) arg);
		}

		return data;
	}

}