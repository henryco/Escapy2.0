package net.irregular.escapy.engine.map.zloader.builder;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.map.zloader.GameObjectLoader;
import net.irregular.escapy.engine.map.zloader.LocationLoader;
import net.irregular.escapy.engine.map.zloader.RenderContainerLoader;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultLocationLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultSubLocationLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject;

import java.util.Collection;
import java.util.Comparator;

/**
 * @author Henry on 15/07/17.
 */
public class DefaultLocationLoaderBuilder implements LocationLoaderBuilder{

	private Comparator<Layer> layerComparator;
	private GameObjectLoader<SerializedGameObject> gameObjectLoader;
	private EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstanceLoader;
	private RenderContainerLoader<Collection<Layer>> renderContainerLoader;


	@Override
	public LocationLoader build() {

		SubLocationLoader subLocationLoader = new DefaultSubLocationLoader(
				layerComparator,
				shiftLogicInstanceLoader,
				gameObjectLoader,
				renderContainerLoader
		);

		return new DefaultLocationLoader(subLocationLoader);
	}


	public DefaultLocationLoaderBuilder setRenderContainerLoader(RenderContainerLoader<Collection<Layer>> renderContainerLoader) {
		this.renderContainerLoader = renderContainerLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setGameObjectLoader(GameObjectLoader<SerializedGameObject> gameObjectLoader) {
		this.gameObjectLoader = gameObjectLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setShiftLogicInstanceLoader(EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstanceLoader) {
		this.shiftLogicInstanceLoader = shiftLogicInstanceLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setLayerComparator(Comparator<Layer> layerComparator) {
		this.layerComparator = layerComparator;
		return this;
	}

}
