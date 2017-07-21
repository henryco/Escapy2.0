package net.irregular.escapy.engine.group.container;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.irregular.escapy.engine.env.context.game.Escapy;
import net.irregular.escapy.engine.env.utils.proxy.EscapyProxyListener;
import net.irregular.escapy.engine.group.container.serial.SerializedGroup;
import net.irregular.escapy.engine.group.map.MapContainer;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.map.loader.LocationLoader;
import net.irregular.escapy.engine.group.render.RenderContainer;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import static java.io.File.separator;
import static net.irregular.escapy.engine.group.container.serial.SerializedGroup.SerializedPath;
import static net.irregular.escapy.engine.group.render.RenderContainer.TargetGroup;

/**
 * @author Henry on 21/07/17.
 */
public class EscapyGroupContainer {


	private final RendererLoader<EscapySubLocation> rendererLoader;
	private final LocationLoader locationLoader;
	private final String configFile;

	private MapContainer mapContainer;
	private RenderContainer renderContainer;



	public EscapyGroupContainer(String configFile,
								LocationLoader locationLoader,
								RendererLoader<EscapySubLocation> rendererLoader) {
		this.rendererLoader = rendererLoader;
		this.locationLoader = locationLoader;
		this.configFile = configFile;
	}




	public Boolean initialize() {

		try {

			String path = Escapy.getConfigsFilePath() + separator + configFile;
			Reader reader = new InputStreamReader(Gdx.files.external(path).read());

			SerializedGroup serialized = new Gson().fromJson(reader, SerializedGroup.class);

			this.renderContainer = initRenderContainer(serialized.renderers);
			this.mapContainer = initMapContainer(serialized.locations, renderContainer.getProxyListener());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}




	private RenderContainer initRenderContainer(List<SerializedPath> group) {

		Collection<TargetGroup> targetGroups = new LinkedList<>();
		for (SerializedPath serializedPath: group) {

			final String path = Escapy.getConfigsFilePath() + serializedPath.path;
			final String[] names = serializedPath.name.split(".");

			if (names.length != 2) throw new RuntimeException();
			targetGroups.add(new TargetGroup(names[0], names[1], path));
		}

		return new RenderContainer(rendererLoader, targetGroups);
	}




	private MapContainer initMapContainer(List<SerializedPath> group,
										  EscapyProxyListener listener) {

		Collection<Map.Entry<String, String>> locations = new LinkedList<>();
		for (SerializedPath serializedPath: group) {

			final String path = Escapy.getConfigsFilePath() + serializedPath.path;
			locations.add(new AbstractMap.SimpleEntry<>(serializedPath.name, path));
		}

		return new MapContainer(locationLoader, locations, listener);
	}




	public MapContainer getMapContainer() {
		return mapContainer;
	}

	public RenderContainer getRenderContainer() {
		return renderContainer;
	}


}