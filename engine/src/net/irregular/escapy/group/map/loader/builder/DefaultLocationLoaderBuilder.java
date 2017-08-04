package net.irregular.escapy.group.map.loader.builder;

import net.irregular.escapy.group.map.core.layer.EscapyLayer;
import net.irregular.escapy.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.group.map.core.location.EscapyLocation;
import net.irregular.escapy.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.group.map.core.object.EscapyGameObject;
import net.irregular.escapy.group.map.loader.GameObjectLoader;
import net.irregular.escapy.group.map.loader.SubLocationLoader;
import net.irregular.escapy.group.map.loader.imp.DefaultGameObjectLoader;
import net.irregular.escapy.group.map.loader.imp.DefaultLocationLoader;
import net.irregular.escapy.group.map.loader.imp.DefaultSubLocationLoader;
import net.irregular.escapy.utils.loader.EscapyInstanceLoader;


/**
 * @author Henry on 15/07/17.
 */
public class DefaultLocationLoaderBuilder implements LocationLoaderBuilder {

	private EscapyInstanceLoader<EscapyGameObject> gameObjectAttributeLoader;
	private EscapyInstanceLoader<LayerShift> subLocationLayerShiftAttributeLoader;
	private EscapyInstanceLoader<EscapyLayer> subLocationLayerAttributeLoader;
	private EscapyInstanceLoader<EscapyLocation> locationAttributeLoader;
	private EscapyInstanceLoader<EscapySubLocation> subLocationAttributeLoader;



	@Override
	public DefaultLocationLoader build() {

		GameObjectLoader gameObjectLoader = new DefaultGameObjectLoader(
				gameObjectAttributeLoader
		);

		SubLocationLoader subLocationLoader = new DefaultSubLocationLoader(
				subLocationLayerShiftAttributeLoader,
				subLocationLayerAttributeLoader,
				subLocationAttributeLoader,
				gameObjectLoader
		);

		return new DefaultLocationLoader(subLocationLoader, locationAttributeLoader);
	}





	public DefaultLocationLoaderBuilder setSubLocationLayerShiftAttributeLoader
			(EscapyInstanceLoader<LayerShift> subLocationLayerShiftAttributeLoader) {
		this.subLocationLayerShiftAttributeLoader = subLocationLayerShiftAttributeLoader;
		return this;
	}


	public DefaultLocationLoaderBuilder setSubLocationLayerAttributeLoader
			(EscapyInstanceLoader<EscapyLayer> subLocationLayerAttributeLoader) {
		this.subLocationLayerAttributeLoader = subLocationLayerAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setGameObjectAttributeLoader
			(EscapyInstanceLoader<EscapyGameObject> gameObjectAttributeLoader) {
		this.gameObjectAttributeLoader = gameObjectAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setLocationAttributeLoader
			(EscapyInstanceLoader<EscapyLocation> locationAttributeLoader) {
		this.locationAttributeLoader = locationAttributeLoader;
		return this;
	}

	public DefaultLocationLoaderBuilder setSubLocationAttributeLoader
			(EscapyInstanceLoader<EscapySubLocation> subLocationAttributeLoader) {
		this.subLocationAttributeLoader = subLocationAttributeLoader;
		return this;
	}
}
