package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.Getter;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentFactoryListener;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.node.EscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.plain.model.LayerModel;

import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("location")
public final class LocationFactory implements EscapyComponentFactoryListener {


	private @Getter IEscapyNode<IEscapyModel> virtualModel;
	private @Getter IEscapyModel locationModel;

	private IEscapyNode<IEscapyModel> lastNode;


	@Override
	public boolean enterComponent(String name) {
		return true;
	}

	@Override
	public Object leaveComponent(String name, Object instance) {
		return instance;
	}


	@EscapyComponent("root")
	public IEscapyModel root (
			@Arg("nested") IEscapyModel ... models
	) {
		this.locationModel = new LayerModel(models);
		this.virtualModel = new EscapyNode<>(locationModel, "root");

		this.lastNode = virtualModel;

		return locationModel;
	}

	@EscapyComponent("layer")
	public IEscapyModel layer (
			@Arg("id") String id,
			@Arg("nested") IEscapyModel ... models
	) {
		val nodeId= id;
		val layer = new LayerModel(models);


		return layer;
	}

}