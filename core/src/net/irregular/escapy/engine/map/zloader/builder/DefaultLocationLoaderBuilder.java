package net.irregular.escapy.engine.map.zloader.builder;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.map.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.map.zloader.GameObjectLoader;
import net.irregular.escapy.engine.map.zloader.LocationLoader;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultLocationLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultSubLocationLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedGameObject;


/**
 * @author Henry on 15/07/17.
 */
public class DefaultLocationLoaderBuilder implements LocationLoaderBuilder{

	private GameObjectLoader<SerializedGameObject> gameObjectLoader;
	private EscapyInstanceLoader<LayerShiftLogic> shiftLogicInstanceLoader;



	@Override
	public LocationLoader build() {

		SubLocationLoader subLocationLoader = new DefaultSubLocationLoader(
				shiftLogicInstanceLoader,
				gameObjectLoader
		);

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


}
