package net.irregular.escapy.engine.group.map.loader.imp;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.group.map.core.location.EscapyLocation;
import net.irregular.escapy.engine.group.map.core.location.Location;
import net.irregular.escapy.engine.group.map.loader.LocationLoader;
import net.irregular.escapy.engine.group.map.loader.SubLocationLoader;
import net.irregular.escapy.engine.group.map.loader.serial.SerializedLocation;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import static java.io.File.separator;
import static net.irregular.escapy.engine.group.map.loader.serial.SerializedLocation.SerializedSubLocationUnit;


/**
 * @author Henry on 13/07/17.
 */
public class DefaultLocationLoader implements LocationLoader {

	private final SubLocationLoader subLocationLoader;
	private final EscapyInstanceLoader<EscapyLocation> locationInstanceAttributeLoader;


	public DefaultLocationLoader(SubLocationLoader subLocationLoader,
								 EscapyInstanceLoader<EscapyLocation> locationInstanceAttributeLoader) {
		this.subLocationLoader = subLocationLoader;
		this.locationInstanceAttributeLoader = locationInstanceAttributeLoader;
	}



	@Override
	public EscapyLocation loadLocation(String path) {

		final SerializedLocation serialized;

		try {
			Reader reader = new InputStreamReader(Gdx.files.internal(path).read());
			serialized = new Gson().fromJson(reader, SerializedLocation.class);
		} catch (Exception ignored) {return null;}


		Collection<Map.Entry<String, String>> subLocations = new LinkedList<>();
		if (serialized.subLocations != null) {
			String folder = path.substring(0, path.lastIndexOf(separator));
			for (SerializedSubLocationUnit subLocationUnit: serialized.subLocations)
				subLocations.add(new AbstractMap.SimpleEntry<>(subLocationUnit.name, folder + subLocationUnit.path));
		}


		EscapyLocation location = new Location(serialized.name, subLocations, subLocationLoader);
		if (locationInstanceAttributeLoader != null)
			location = locationInstanceAttributeLoader.loadInstanceAttributes(location, serialized.attributes);

		return location;
	}





}
