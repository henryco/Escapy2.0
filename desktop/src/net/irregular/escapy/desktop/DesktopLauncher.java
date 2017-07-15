package net.irregular.escapy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.irregular.escapy.desktop.loader.DefaultDesktopConfigLoaderBuilder;
import net.irregular.escapy.desktop.loader.SerializedDesktopConfig;
import net.irregular.escapy.engine.env.EscapyApplicationAdapter;
import net.irregular.escapy.environment.MainEnvironment;
import net.irregular.escapy.environment.main.MainModule;

import static java.io.File.separator;

public class DesktopLauncher {
	public static void main (String[] arg) throws Exception {

		LwjglApplicationConfiguration config = new DefaultDesktopConfigLoaderBuilder()
				.setLoadedClass(LwjglApplicationConfiguration.class)
				.setSerializedClass(SerializedDesktopConfig.class)
				.setPath(System.getProperty("user.dir") + separator + "Configuration.json")
				.setName("MainConfiguration")
				.build()
		.loadDesktopConfig();

		new LwjglApplication(new EscapyApplicationAdapter(MainEnvironment.class, new MainModule()), config);

	}
}
