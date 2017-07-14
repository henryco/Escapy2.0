package net.irregular.escapy.engine.map.zloader.imp;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.irregular.escapy.engine.map.location.Location;
import net.irregular.escapy.engine.map.zloader.LocationLoader;
import net.irregular.escapy.engine.map.zloader.SubLocationLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedLocation;
import net.irregular.escapy.engine.map.zloader.serial.SerializedSubLocation;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;


/**
 * @author Henry on 13/07/17.
 */
public class DefaultLocationLoader implements LocationLoader {

	private final SubLocationLoader subLocationLoader;
	public DefaultLocationLoader(SubLocationLoader subLocationLoader) {
		this.subLocationLoader = subLocationLoader;
	}


	@Override
	public Location loadLocation(String path) {

		final SerializedLocation serialized;

		try {
			Reader reader = new InputStreamReader(new FileInputStream(path));
			serialized = new Gson().fromJson(reader, SerializedLocation.class);
		} catch (Exception ignored) {return null;}


		Collection<Map.Entry<String, String>> subLocations = new LinkedList<>();
		if (serialized.subLocations != null) {
			String folder = path.substring(0, path.lastIndexOf("/") + 1);


			for (String file: serialized.subLocations) try {

				String subFile = folder + file;
				JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream(subFile)));


				String type = null;
				String name = null;
				String loc = null;


				jsonReader.beginObject();
				for (int i = 0; i < 3; i++)
					switch (jsonReader.nextName()) {
						case "type":
							type = jsonReader.nextString();
							break;
						case "name":
							name = jsonReader.nextString();
							break;
						case "location":
							loc = jsonReader.nextString();
							break;
					}
				jsonReader.endObject();


				if (loc.equals(serialized.name) && type.equals(SerializedSubLocation.VALID_TYPE)) {
					subLocations.add(new AbstractMap.SimpleEntry<>(name, subFile));
				}


			} catch (Exception ignored) {return null;}
		}


		Location location = new Location(serialized.name, subLocations, subLocationLoader);
		location.switchSubLocation(serialized.enter);

		return location;
	}


}
