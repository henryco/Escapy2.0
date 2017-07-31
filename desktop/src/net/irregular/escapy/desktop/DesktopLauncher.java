package net.irregular.escapy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.irregular.escapy.MainEnvironment;
import net.irregular.escapy.desktop.builder.DesktopConfigLoaderBuilder;
import net.irregular.escapy.desktop.serial.SerializedDesktopConfig;
import net.irregular.escapy.environment.EscapyApplicationAdapter;
import net.irregular.escapy.modules.MainModule;

import static java.io.File.separator;

public class DesktopLauncher {

	public static void main (String[] arg) throws Exception {

		LwjglApplicationConfiguration config = DesktopConfigLoaderBuilder.Default()
				.setLoadedClass(LwjglApplicationConfiguration.class)
				.setSerializedClass(SerializedDesktopConfig.class)
				.setPath(System.getProperty("user.dir") + separator + "Configuration.json")
				.setName("MainConfiguration")
				.build()
		.loadDesktopConfig();

		new LwjglApplication(new EscapyApplicationAdapter(MainEnvironment.class, new MainModule()), config);
	}
}