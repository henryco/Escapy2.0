package net.irregular.escapy.engine.map.zloader;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.map.zloader.imp.DefaultLocationLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultSubLocationLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject;

import java.util.Comparator;

/**
 * @author Henry on 15/07/17.
 */
public class DefaultLocationLoaderBuilder {

	private Comparator<Layer> layerComparator;
	private GameObjectLoader<SerializedGameObject> gameObjectLoader;
	private EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstanceLoader;



	public LocationLoader build() {

		SubLocationLoader subLocationLoader = new DefaultSubLocationLoader
				(layerComparator, shiftLogicInstanceLoader, gameObjectLoader);

		return new DefaultLocationLoader(subLocationLoader);
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
