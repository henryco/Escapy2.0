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
import net.tindersamurai.escapy.map.node.IEscapyNodeObserver;
import net.tindersamurai.escapy.plain.model.LayerModel;

import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("location")
public final class LocationFactory implements EscapyComponentFactoryListener {


	private @Getter IEscapyNode<IEscapyModel> virtualModel;

	private int depthCounter = 0;

	@Override
	public boolean enterComponent(String name) {
		System.out.println("ENTER: " + depthCounter + " : " + name);
		depthCounter++;
		return true;
	}

	@Override
	public Object leaveComponent(String name, Object instance) {
		depthCounter--;
		System.out.println("LEAVE: " + depthCounter + " : " + name);
		return instance;
	}


	@EscapyComponent("root")
	public IEscapyNode<IEscapyModel> root (
			@Arg("nested") IEscapyNode<IEscapyModel> ... models
	) {
		this.virtualModel = new EscapyNode<IEscapyModel>(null, "root") {{
			setObserver(new IEscapyNodeObserver() {
				@Override
				public void nodeAdded(IEscapyNode parent, IEscapyNode node) {

				}

				@Override
				public void nodeRemoved(IEscapyNode parent, IEscapyNode node) {

				}
			});
			for (IEscapyNode<IEscapyModel> model : models) {
				addNode(model);
			}
		}};
		return virtualModel;
	}

	@EscapyComponent("layer")
	public IEscapyNode<IEscapyModel> layer (
			@Arg("id") String id,
			@Arg("nested") IEscapyNode<IEscapyModel> ... models
	) {
		return new EscapyNode<>(null, id);
	}

}