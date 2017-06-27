package net.irregular.escapy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.irregular.escapy.EscapyMainEnvironment;
import net.irregular.escapy.MainEnvironmentModule;
import net.irregular.escapy.dagger.DaggerAdapter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.vSyncEnabled = true;
		config.fullscreen = true;
//		config.width = 1280;
//		config.height = 720;
		config.width = 1920;
		config.height = 1080;
		config.forceExit = true;

		DaggerAdapter daggerAdapter = new DaggerAdapter(EscapyMainEnvironment.class, new MainEnvironmentModule());
		new LwjglApplication(daggerAdapter, config);

	}
}
