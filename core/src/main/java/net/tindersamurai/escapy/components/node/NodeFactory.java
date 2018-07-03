package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("node")
public class NodeFactory {

	@EscapyComponent("data")
	public final NodeData data(@Arg("args") Object ... args) {

		val data = new NodeData();
		for (Object arg : args) {

			if (arg instanceof IEscapyModel)
				data.setModel((IEscapyModel) arg);

			// todo
		}

		return data;
	}

}