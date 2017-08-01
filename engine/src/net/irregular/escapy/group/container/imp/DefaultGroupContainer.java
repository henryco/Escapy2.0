package net.irregular.escapy.group.container.imp;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.irregular.escapy.context.game.Escapy;
import net.irregular.escapy.group.container.EscapyGroupContainer;
import net.irregular.escapy.group.container.EscapyLocationContainer;
import net.irregular.escapy.group.container.EscapyRendererContainer;
import net.irregular.escapy.group.container.serial.SerializedGroup;
import net.irregular.escapy.group.map.loader.imp.DefaultLocationLoader;
import net.irregular.escapy.group.render.loader.imp.DefaultRendererLoader;
import net.irregular.escapy.utils.EscapyLogger;
import net.irregular.escapy.utils.files.EscapyFiles;
import net.irregular.escapy.utils.proxy.EscapyProxyListener;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import static java.io.File.separator;

/**
 * @author Henry on 21/07/17.
 */
public class DefaultGroupContainer implements EscapyGroupContainer {


	private final DefaultRendererLoader rendererLoader;
	private final DefaultLocationLoader locationLoader;
	private final String configFile;

	private EscapyLocationContainer locationContainer;
	private EscapyRendererContainer rendererContainer;



	public DefaultGroupContainer(String configFile,
								 DefaultLocationLoader locationLoader,
								 DefaultRendererLoader rendererLoader) {
		this.rendererLoader = rendererLoader;
		this.locationLoader = locationLoader;
		this.configFile = configFile;
	}




	@Override
	public boolean initialize() {

		try {

			String path = Escapy.getConfigsFilePath() + separator + configFile;
			Reader reader = new InputStreamReader(Gdx.files.internal(EscapyFiles.safetyPath(path)).read());

			SerializedGroup serialized = new Gson().fromJson(reader, SerializedGroup.class);
			DefaultRendererContainer ren = initRendererContainer(serialized.renderers);

			this.locationContainer = initLocationContainer(serialized.locations, ren.getProxyListener());
			this.rendererContainer = ren;

			return true;

		} catch (Exception e) {
			new EscapyLogger("GroupInitialization").fileJava().log(e, true);
			return false;
		}
	}




	private DefaultRendererContainer initRendererContainer(List<SerializedGroup.SerializedPath> group) {

		Collection<DefaultRendererContainer.TargetGroup> targetGroups = new LinkedList<>();
		for (SerializedGroup.SerializedPath serializedPath: group) {

			final String path = Escapy.getConfigsFilePath() + EscapyFiles.safetyPath(serializedPath.path);
			final String[] names = serializedPath.name.split(":");

			if (names.length != 2) throw new RuntimeException();
			targetGroups.add(new DefaultRendererContainer.TargetGroup(names[0], names[1], path));
		}

		return new DefaultRendererContainer(rendererLoader, targetGroups);
	}




	private DefaultLocationContainer initLocationContainer(List<SerializedGroup.SerializedPath> group,
														   EscapyProxyListener listener) {

		Collection<Map.Entry<String, String>> locations = new LinkedList<>();
		for (SerializedGroup.SerializedPath serializedPath: group) {

			final String path = Escapy.getConfigsFilePath() + serializedPath.path;
			locations.add(new AbstractMap.SimpleEntry<>(serializedPath.name, EscapyFiles.safetyPath(path)));
		}

		return new DefaultLocationContainer(locationLoader, locations, listener);
	}



	@Override
	public EscapyLocationContainer getLocationContainer() {
		return locationContainer;
	}

	@Override
	public EscapyRendererContainer getRendererContainer() {
		return rendererContainer;
	}


}