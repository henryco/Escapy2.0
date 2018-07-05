package net.tindersamurai.escapy.group.container.imp;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.tindersamurai.escapy.context.game.Escapy;
import net.tindersamurai.escapy.group.container.EscapyGroupContainer;
import net.tindersamurai.escapy.group.container.EscapyLocationContainer;
import net.tindersamurai.escapy.group.container.EscapyRendererContainer;
import net.tindersamurai.escapy.group.container.serial.SerializedGroup;
import net.tindersamurai.escapy.group.map.loader.imp.DefaultLocationLoader;
import net.tindersamurai.escapy.group.render.loader.imp.DefaultRendererLoader;
import net.tindersamurai.escapy.utils.EscapyLogger;
import net.tindersamurai.escapy.utils.files.EscapyFiles;
import net.tindersamurai.escapy.utils.proxy.EscapyProxyListener;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import static java.io.File.separator;
import static net.tindersamurai.escapy.group.container.serial.SerializedGroup.SerializedPath;

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

			String path = Escapy.getGameContext().getConfigsFilePath() + separator + configFile;
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




	private DefaultRendererContainer initRendererContainer(List<SerializedPath> group) {

		Collection<DefaultRendererContainer.TargetGroup> targetGroups = new LinkedList<>();
		for (SerializedPath serializedPath: group) {

			final String path = Escapy.getGameContext().getConfigsFilePath() + EscapyFiles.safetyPath(serializedPath.path);
			final String[] names = serializedPath.name.split(":");

			if (names.length != 2) throw new RuntimeException();
			targetGroups.add(new DefaultRendererContainer.TargetGroup(names[0], names[1], path));
		}

		return new DefaultRendererContainer(rendererLoader, targetGroups);
	}




	private DefaultLocationContainer initLocationContainer(List<SerializedPath> group,
														   EscapyProxyListener listener) {

		Collection<Map.Entry<String, String>> locations = new LinkedList<>();
		for (SerializedPath serializedPath: group) {

			final String path = Escapy.getGameContext().getConfigsFilePath() + serializedPath.path;
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