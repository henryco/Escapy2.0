package net.irregular.escapy.engine.map.zloader.builder;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.map.location.Location;
import net.irregular.escapy.engine.map.location.SubLocation;
import net.irregular.escapy.engine.map.object.GameObject;
import net.irregular.escapy.engine.map.zloader.GameObjectLoader;
import net.irregular.escapy.engine.map.zloader.LocationLoader;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultGameObjectLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultLocationLoader;
import net.irregular.escapy.engine.map.zloader.imp.DefaultSubLocationLoader;


/**
 * @author Henry on 15/07/17.
 */
public class DefaultLocationLoaderBuilder implements LocationLoaderBuilder {

	private EscapyInstanceLoader<GameObject> gameObjectInstanceAttributeLoader;
	private EscapyInstanceLoader<LayerShiftLogic> subLocationLayerShiftLogicInstanceLoader;
	private EscapyInstanceLoader<Layer> subLocationLayerInstanceAttributeLoader;
	private EscapyInstanceLoader<Location> locationInstanceAttributeLoader;
	private EscapyInstanceLoader<SubLocation> subLocationInstanceAttributeLoader;



	@Override
	public LocationLoader build() {

		GameObjectLoader gameObjectLoader = new DefaultGameObjectLoader(
				gameObjectInstanceAttributeLoader
		);

		SubLocationLoader subLocationLoader = new DefaultSubLocationLoader(
				subLocationLayerShiftLogicInstanceLoader,
				subLocationLayerInstanceAttributeLoader,
				subLocationInstanceAttributeLoader,
				gameObjectLoader
		);

		return new DefaultLocationLoader(subLocationLoader, locationInstanceAttributeLoader);
	}





	public DefaultLocationLoaderBuilder setSubLocationLayerShiftLogicInstanceLoader
			(EscapyInstanceLoader<LayerShiftLogic> subLocationLayerShiftLogicInstanceLoader) {
		this.subLocationLayerShiftLogicInstanceLoader = subLocationLayerShiftLogicInstanceLoader;
		return this;
	}


	public DefaultLocationLoaderBuilder setSubLocationLayerInstanceAttributeLoader
			(EscapyInstanceLoader<Layer> subLocationLayerInstanceAttributeLoader) {
		this.subLocationLayerInstanceAttributeLoader = subLocationLayerInstanceAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setGameObjectInstanceAttributeLoader
			(EscapyInstanceLoader<GameObject> gameObjectInstanceAttributeLoader) {
		this.gameObjectInstanceAttributeLoader = gameObjectInstanceAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setLocationInstanceAttributeLoader
			(EscapyInstanceLoader<Location> locationInstanceAttributeLoader) {
		this.locationInstanceAttributeLoader = locationInstanceAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setSubLocationInstanceAttributeLoader
			(EscapyInstanceLoader<SubLocation> subLocationInstanceAttributeLoader) {
		this.subLocationInstanceAttributeLoader = subLocationInstanceAttributeLoader;
		return this;
	}
}
