package net.irregular.escapy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.irregular.escapy.engine.env.EscapyApplicationAdapter;
import net.irregular.escapy.environment.MainEnvironment;
import net.irregular.escapy.environment.main.MainModule;

public class DesktopLauncher {
	public static void main (String[] arg) throws Exception {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.vSyncEnabled = true;
		config.fullscreen = true;
//		config.width = 1280;
//		config.height = 720;
		config.width = 1920;
		config.height = 1080;
		config.forceExit = true;
		config.foregroundFPS = 25;


//		LwjglApplicationConfiguration config = new DefaultDesktopConfigLoaderBuilder()
//				.setLoadedClass(LwjglApplicationConfiguration.class)
//				.setSerializedClass(SerializedDesktopConfig.class)
//				.setPath("")
//				.setName("")
//				.build()
//		.loadDesktopConfig();


		new LwjglApplication(new EscapyApplicationAdapter(MainEnvironment.class, new MainModule()), config);

	}
}
