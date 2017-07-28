package net.irregular.escapy.engine.group.container.core;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.irregular.escapy.engine.env.context.game.Escapy;
import net.irregular.escapy.engine.env.utils.EscapyFiles;
import net.irregular.escapy.engine.env.utils.EscapyLogger;
import net.irregular.escapy.engine.env.utils.proxy.EscapyProxyListener;
import net.irregular.escapy.engine.group.container.EscapyGroupContainer;
import net.irregular.escapy.engine.group.container.serial.SerializedGroup;
import net.irregular.escapy.engine.group.map.core.location.EscapyLocationContainer;
import net.irregular.escapy.engine.group.map.loader.imp.DefaultLocationLoader;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;
import net.irregular.escapy.engine.group.render.loader.imp.DefaultRendererLoader;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import static java.io.File.separator;
import static net.irregular.escapy.engine.group.container.core.DefaultRendererContainer.TargetGroup;
import static net.irregular.escapy.engine.group.container.serial.SerializedGroup.SerializedPath;

/**
 * @author Henry on 21/07/17.
 */
public class DefaultGroupContainer implements EscapyGroupContainer {


	private final DefaultRendererLoader rendererLoader;
	private final DefaultLocationLoader locationLoader;
	private final String configFile;

	private EscapyLocationContainer locationContainer;
	private EscapyRenderer renderer;



	public DefaultGroupContainer(String configFile,
								 DefaultLocationLoader locationLoader,
								 DefaultRendererLoader rendererLoader) {
		this.rendererLoader = rendererLoader;
		this.locationLoader = locationLoader;
		this.configFile = configFile;
	}




	public boolean initialize() {

		try {

			String path = Escapy.getConfigsFilePath() + separator + configFile;
			Reader reader = new InputStreamReader(Gdx.files.internal(EscapyFiles.safetyPath(path)).read());

			SerializedGroup serialized = new Gson().fromJson(reader, SerializedGroup.class);
			DefaultRendererContainer ren = initRendererContainer(serialized.renderers);

			this.locationContainer = initLocationContainer(serialized.locations, ren.getProxyListener());
			this.renderer = ren;

			return true;

		} catch (Exception e) {
			new EscapyLogger("GroupInitialization").fileJava().log(e, true);
			return false;
		}
	}




	private DefaultRendererContainer initRendererContainer(List<SerializedPath> group) {

		Collection<TargetGroup> targetGroups = new LinkedList<>();
		for (SerializedPath serializedPath: group) {

			final String path = Escapy.getConfigsFilePath() + EscapyFiles.safetyPath(serializedPath.path);
			final String[] names = serializedPath.name.split(":");

			if (names.length != 2) throw new RuntimeException();
			targetGroups.add(new TargetGroup(names[0], names[1], path));
		}

		return new DefaultRendererContainer(rendererLoader, targetGroups);
	}




	private DefaultLocationContainer initLocationContainer(List<SerializedPath> group,
														   EscapyProxyListener listener) {

		Collection<Map.Entry<String, String>> locations = new LinkedList<>();
		for (SerializedPath serializedPath: group) {

			final String path = Escapy.getConfigsFilePath() + serializedPath.path;
			locations.add(new AbstractMap.SimpleEntry<>(serializedPath.name, EscapyFiles.safetyPath(path)));
		}

		return new DefaultLocationContainer(locationLoader, locations, listener);
	}



	public EscapyLocationContainer getLocationContainer() {
		return locationContainer;
	}

	public EscapyRenderer getRenderer() {
		return renderer;
	}


}