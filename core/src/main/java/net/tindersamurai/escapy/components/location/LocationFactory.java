package net.tindersamurai.escapy.components.location;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.Getter;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentFactoryListener;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.node.EscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;
import net.tindersamurai.escapy.plain.node.NodeData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("location")
public final class LocationFactory implements EscapyComponentFactoryListener {

	private @Getter IEscapyNode<NodeData> virtualModel;
	private final IEscapyNodeObserver nodeObserver;

	@Inject
	public LocationFactory(IEscapyNodeObserver nodeObserver) {
		this.nodeObserver = nodeObserver;
	}

	@SafeVarargs @EscapyComponent("root")
	public final IEscapyNode<NodeData> root(
			@Arg("nested") IEscapyNode<NodeData>... models
	) {

		return null;
	}

	@SafeVarargs @EscapyComponent("layer")
	public final IEscapyNode<NodeData> layer (
			@Arg("id") String id,
			@Arg("nested") IEscapyNode<NodeData> ... models
	) {
		return new EscapyNode<>(null, id);
	}



	@Override
	public boolean enterComponent(String name) {
		return true;
	}

	@Override
	public Object leaveComponent(String name, Object instance) {
		return instance;
	}
}