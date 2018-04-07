package net.irregular.escapy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.irregular.escapy.MainEnvironment;
import net.irregular.escapy.context.EscapyApplicationAdapter;
import net.irregular.escapy.desktop.builder.EscapyDesktopConfigLoaderBuilder;
import net.irregular.escapy.desktop.serial.SerializedDesktopConfig;
import net.irregular.escapy.modules.MainModule;

import static java.io.File.separator;

public final class DesktopLauncher {

	private static final String CONFIG = System.getProperty("user.dir") + separator + "res" + separator + "Configuration.json";

	public static void main(String[] arg) {

		LwjglApplicationConfiguration config = EscapyDesktopConfigLoaderBuilder.Default()
				.setLoadedClass(LwjglApplicationConfiguration.class)
				.setSerializedClass(SerializedDesktopConfig.class)
				.setPath(CONFIG)
				.setName("MainConfiguration")
				.build()
		.loadDesktopConfig();

		new LwjglApplication(new EscapyApplicationAdapter(MainEnvironment.class, new MainModule()), config);
	}
}