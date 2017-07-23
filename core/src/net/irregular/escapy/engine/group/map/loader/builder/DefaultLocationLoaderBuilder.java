package net.irregular.escapy.engine.group.map.loader.builder;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.engine.group.map.core.layer.shift.LayerShiftLogic;
import net.irregular.escapy.engine.group.map.core.location.EscapyLocation;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.engine.group.map.loader.GameObjectLoader;
import net.irregular.escapy.engine.group.map.loader.LocationLoader;
import net.irregular.escapy.engine.group.map.loader.SubLocationLoader;
import net.irregular.escapy.engine.group.map.loader.imp.DefaultGameObjectLoader;
import net.irregular.escapy.engine.group.map.loader.imp.DefaultLocationLoader;
import net.irregular.escapy.engine.group.map.loader.imp.DefaultSubLocationLoader;


/**
 * @author Henry on 15/07/17.
 */
public class DefaultLocationLoaderBuilder implements LocationLoaderBuilder {

	private EscapyInstanceLoader<EscapyGameObject> gameObjectInstanceAttributeLoader;
	private EscapyInstanceLoader<LayerShiftLogic> subLocationLayerShiftLogicInstanceLoader;
	private EscapyInstanceLoader<EscapyLayer> subLocationLayerInstanceAttributeLoader;
	private EscapyInstanceLoader<EscapyLocation> locationInstanceAttributeLoader;
	private EscapyInstanceLoader<EscapySubLocation> subLocationInstanceAttributeLoader;



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
			(EscapyInstanceLoader<EscapyLayer> subLocationLayerInstanceAttributeLoader) {
		this.subLocationLayerInstanceAttributeLoader = subLocationLayerInstanceAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setGameObjectInstanceAttributeLoader
			(EscapyInstanceLoader<EscapyGameObject> gameObjectInstanceAttributeLoader) {
		this.gameObjectInstanceAttributeLoader = gameObjectInstanceAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setLocationInstanceAttributeLoader
			(EscapyInstanceLoader<EscapyLocation> locationInstanceAttributeLoader) {
		this.locationInstanceAttributeLoader = locationInstanceAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setSubLocationInstanceAttributeLoader
			(EscapyInstanceLoader<EscapySubLocation> subLocationInstanceAttributeLoader) {
		this.subLocationInstanceAttributeLoader = subLocationInstanceAttributeLoader;
		return this;
	}
}
