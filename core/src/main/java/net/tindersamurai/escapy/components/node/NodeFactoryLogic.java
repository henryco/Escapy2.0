package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.GrInjector;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import javax.inject.Singleton;

@Provide @Singleton
public class NodeFactoryLogic {

	public final NodeData data(Object ... args) {

		val data = GrInjector.getComponent(NodeData.class);

		for (Object arg : args) {

			if (arg instanceof IEscapyModel)
				data.setModel((IEscapyModel) arg);

			// todo
		}

		return data;
	}

}