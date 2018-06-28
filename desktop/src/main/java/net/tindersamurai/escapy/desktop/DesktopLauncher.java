package net.tindersamurai.escapy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.tindersamurai.escapy.EscapyApplication;
import net.tindersamurai.escapy.old.MainEnvironment;
import net.tindersamurai.escapy.context.EscapyApplicationAdapter;
import net.tindersamurai.escapy.desktop.builder.EscapyDesktopConfigLoaderBuilder;
import net.tindersamurai.escapy.desktop.serial.SerializedDesktopConfig;
import net.tindersamurai.escapy.old.modules.MainModule;

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

//		new LwjglApplication(new EscapyApplicationAdapter(MainEnvironment.class, new MainModule()), config);

		new LwjglApplication(new EscapyApplication(), config);
	}
}